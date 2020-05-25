package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class school_food_lineup extends AppCompatActivity implements View.OnClickListener{
    Button ok;
    Button cancel;
    Button top_navi, btn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_food_lineup);

        ok = (Button) findViewById(R.id.go_school_information);
        cancel = (Button) findViewById(R.id.btn_school_food_lineup_cancel);

        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.go_school_information) {
            Intent intent01 = new Intent(school_food_lineup.this, school_information.class);
            startActivity(intent01);
        }
        if(v.getId() == R.id.btn_school_food_lineup_cancel) {
           finish();
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(school_food_lineup.this, MainActivity.class);
            startActivity(intent1);
        }


        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(school_food_lineup.this, configActivity.class);
            startActivity(intent6);
        }
    }
}
