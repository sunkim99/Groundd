package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class config_my_forum_image extends AppCompatActivity implements View.OnClickListener{

    Button go_forum_forum;
    Button top_navi, btn_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_my_forum_image);

        go_forum_forum = findViewById(R.id.go_config_forum_forum);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
        go_forum_forum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.go_notice_notice) {
            Intent intent01 = new Intent(config_my_forum_image.this, notice_notice.class);
            startActivity(intent01);
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(config_my_forum_image.this, MainActivity.class);
            startActivity(intent1);
        }
        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(config_my_forum_image.this, configActivity.class);
            startActivity(intent6);
        }
    }
}
