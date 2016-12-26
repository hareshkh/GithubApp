package com.sdsmdg.hareshkh.githubapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashScreenActivity extends AppCompatActivity {

    Intent intent;
    SharedPreferences sharedPreferences;
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences("MY_PREFERENCES", MODE_PRIVATE);
        LoginActivity.oAuthToken = sharedPreferences.getString("OAUTH", "");
        LoginActivity.CODE = sharedPreferences.getString("CODE", "");

        Log.e(TAG, LoginActivity.oAuthToken);

        if (LoginActivity.oAuthToken == "") {
            intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        } else {
            intent = new Intent(SplashScreenActivity.this, ContentActivity.class);
        }

        int SPLASH_TIME_OUT = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over.
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
