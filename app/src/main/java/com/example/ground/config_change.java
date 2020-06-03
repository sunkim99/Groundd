package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class config_change extends AppCompatActivity implements View.OnClickListener{

    Button go_forum_image;
    Button top_navi, btn_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_change);

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(config_change.this, MainActivity.class);
            startActivity(intent1);
        }
        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(config_change.this, configActivity.class);
            startActivity(intent6);
        }
    }
}
