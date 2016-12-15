package com.sdsmdg.hareshkh.githubapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    WebView webView;
    Button button;
    private final String AUTH_GH_URL = "https://github.com/login/oauth/authorize";
    private final String CLIENT_ID = Config.GITHUB_CLIENT_ID;
    private final String CLIENT_SECRET = Config.GITHUB_CLIENT_SECRET;
    private final String REDIRECT_URL = "https://githubapp-1c486.firebaseapp.com/__/auth/handler";
    private final String SCOPE = "user%20notifications";
    private String CODE = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        webView = (WebView) findViewById(R.id.login_browser);
        button = (Button) findViewById(R.id.login_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                webView.setWebViewClient(new WebViewClient() {
                    @SuppressWarnings("deprecation")
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        Log.e("Login", url);
                        if (url.contains("code")) {
                            CODE = url.substring(61, 81);
                            Toast.makeText(Login.this, "" + CODE, Toast.LENGTH_SHORT).show();
                            Log.e("Login", CODE);
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
