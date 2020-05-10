package com.example.ground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        try {
            Thread.sleep(3000); //대기 초 설정
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        } catch (Exception e) {
        }
    }
}