package com.iprismtech.studentvarsity.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.ui.activity.FirstScreenActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, FirstScreenActivity.class));
                finish();
            }
        }, 2000);
    }
}
