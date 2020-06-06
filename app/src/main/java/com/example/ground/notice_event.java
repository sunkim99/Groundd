package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//공지 - 이벤트 (미진행)
public class notice_event extends AppCompatActivity implements View.OnClickListener{

    Button go_notice_notice;
    Button top_navi, btn_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_event);

        go_notice_notice = findViewById(R.id.go_notice_notice);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
        go_notice_notice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.go_notice_notice) {
            Intent intent01 = new Intent(notice_event.this, notice_notice.class);
            startActivity(intent01);
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(notice_event.this, MainActivity.class);
            startActivity(intent1);
        }
        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(notice_event.this, configActivity.class);
            startActivity(intent6);
        }
    }
}
