package com.sdsmdg.hareshkh.githubapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    Intent intent;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences("MY_PREFERENCES", MODE_PRIVATE);
        Login.oAuthToken = sharedPreferences.getString("OAUTH", "");
        Login.CODE = sharedPreferences.getString("CODE", "");

        if (Login.oAuthToken == "") {
            intent = new Intent(SplashScreen.this, Login.class);
        } else {
            intent = new Intent(SplashScreen.this, Content.class);
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
