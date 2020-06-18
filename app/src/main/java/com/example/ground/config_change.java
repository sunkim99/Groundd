package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class config_change extends AppCompatActivity implements View.OnClickListener{

    Button go_forum_image;
    Button top_navi, btn_setting;
    TextView tv_id, tv_name, tv_school;
    EditText tv_pw, tv_pw1, tv_nick, tv_ph, tv_pph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_change);

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);

        tv_id = findViewById(R.id.tv_id);
        tv_name = findViewById(R.id.tv_name);
        tv_school = findViewById(R.id.tv_school);
        tv_pw = findViewById(R.id.tv_pw);
        tv_pw1 = findViewById(R.id.tv_pw1);
        tv_nick = findViewById(R.id.tv_nick);
        tv_ph = findViewById(R.id.tv_ph);
        tv_pph = findViewById(R.id.tv_pph);



        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        final allround ADMIN = (allround) getApplicationContext(); // 관리자 소환
        final allround SCHADD = (allround) getApplicationContext(); //학교 주소
        final allround SCHPH = (allround) getApplicationContext(); //학교 전화번호
        final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환
        final allround USERNUM = (allround) getApplicationContext(); // 전역변수 USERNUM
        final allround USERNAME = (allround) getApplicationContext(); // 전역변수 USERNUM

        ID.getID();
        SCHOOL.getSCHOOL();
        SCHADD.getSCHADD();
        SCHPH.getSCHPH();
        NICKNAME.getSCHPH();
        USERNUM.getUSERNUM();
        USERNAME.getUSERNAME();

        tv_id.setText(ID.getID());
        tv_name.setText(USERNAME.getUSERNAME());
        tv_school.setText(SCHADD.getSCHOOL());

        /**
         * 방학동안 여기서 해야할 작업
         *
         * 1. 버튼 하나 만들기(변경하기버튼)
         * 2. 새롭게 정보를 입력했을때, 빈칸이 있는경우 다이얼로그메시지 창 뜨게하기
         * 3. 변경하기 버튼을 누르면 php-> DB UPDATE 시키기
         *
         * **/

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
