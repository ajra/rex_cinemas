package com.rexcinemas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rexcinemas.R;
import com.rexcinemas.ui.navigation.NavigationActivity;
import com.rexcinemas.ui.navigation.NavigationHomeActivity;

public class SplashActivity extends BasicActivity {

    Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeFirstActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
