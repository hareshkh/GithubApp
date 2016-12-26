package com.sdsmdg.hareshkh.githubapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.sdsmdg.hareshkh.githubapp.connections.models.oauth.OauthModel;
import com.sdsmdg.hareshkh.githubapp.connections.remotes.OauthApi;
import com.sdsmdg.hareshkh.githubapp.helpers.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    WebView webView;
    Button button;
    public final static String AUTH_GH_URL = "https://github.com/login/oauth/authorize";
    public final static String TOKEN_GH_URL = "https://github.com/login/oauth/";
    public final static String CLIENT_ID = Config.GITHUB_CLIENT_ID;
    public final static String CLIENT_SECRET = Config.GITHUB_CLIENT_SECRET;
    public final static String REDIRECT_URL = "https://githubapp-1c486.firebaseapp.com/__/auth/handler";
    public final static String SCOPE = "user%20notifications%20repo";
    public static String CODE;
    public static String oAuthToken;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        webView = (WebView) findViewById(R.id.login_browser);
        button = (Button) findViewById(R.id.login_button);
        sharedPreferences = getSharedPreferences("MY_PREFERENCES", MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                webView.setWebViewClient(new WebViewClient() {
                    @SuppressWarnings("deprecation")
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        Log.e(TAG, url);
                        if (url.contains("code")) {
                            CODE = url.substring(61, 81);
                            Toast.makeText(LoginActivity.this, "Code recieved. Acquiring OAuth Creds...", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, CODE);
                            editor = sharedPreferences.edit();
                            editor.putString("CODE", CODE);
                            editor.putString("OAUTH", "");
                            editor.commit();

                            Log.e(TAG, "PostReq");
                            //POST REQUEST HERE.
                            OauthApi.Factory.getInstance().getOauthModel(
                                    CLIENT_ID,
                                    CLIENT_SECRET,
                                    CODE,
                                    REDIRECT_URL,
                                    "random",
                                    "application/json"
                            ).enqueue(new Callback<OauthModel>() {
                                @Override
                                public void onResponse(Call<OauthModel> call, Response<OauthModel> response) {
                                    oAuthToken = response.body().getAccessToken();
                                    Log.e(TAG, oAuthToken);
                                    Toast.makeText(LoginActivity.this, "LoginActivity Successful", Toast.LENGTH_SHORT).show();
                                    editor = sharedPreferences.edit();
                                    editor.putString("OAUTH", oAuthToken);
                                    editor.commit();
                                    Intent intent = new Intent(LoginActivity.this, ContentActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<OauthModel> call, Throwable t) {

                                }
                            });
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
                webView.getSettings().setLoadWithOverviewMode(true);
                webView.getSettings().setUseWideViewPort(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.clearCache(true);
                webView.loadUrl(AUTH_GH_URL +
                        "?client_id=" + CLIENT_ID +
                        "&redirect_uri=" + REDIRECT_URL +
                        "&scope=" + SCOPE +
                        "&state=random" +
                        "&allow_signup=true");
            }
        });
    }
}
