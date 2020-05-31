package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class notice_notice extends AppCompatActivity implements View.OnClickListener{

    Button go_notice_event;
    Button top_navi, btn_setting;
    Button cancel, write;


    int admin_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_notice);
        allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        allround ADMIN = (allround) getApplicationContext();

        go_notice_event= findViewById(R.id.go_notice_event);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);
        cancel = findViewById(R.id.btn_go_notice_notice_cancel);
        write = findViewById(R.id.go_notice_notice_write);

        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
        go_notice_event.setOnClickListener(this);
        cancel.setOnClickListener(this);
        write.setOnClickListener(this);

        admin_s = ADMIN.getADMIN();
        if(admin_s == 0){
            write.setVisibility(Button.GONE);
        }
        if(admin_s == 1){
            write.setVisibility(Button.VISIBLE);
        }
        if(admin_s == 2){
            write.setVisibility(Button.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.go_notice_event) {
            Intent intent01 = new Intent(notice_notice.this, notice_event.class);
            startActivity(intent01);
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(notice_notice.this, MainActivity.class);
            startActivity(intent1);
        }
        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(notice_notice.this, configActivity.class);
            startActivity(intent6);
        }
        if (v.getId() == R.id.btn_go_notice_notice_cancel) { // 종료
            finish();
        }
        if (v.getId() == R.id.go_notice_notice_write) {
            Intent intent6 = new Intent(notice_notice.this, schoolforumwrite.class);
            startActivity(intent6);
        }
    }
}
