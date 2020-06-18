package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//게임 랭킹
public class game_ranking extends AppCompatActivity implements View.OnClickListener{

    Button go_other_menu;
    Button go_ranking;
    Button cancel;
    Button top_navi, btn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ranking);
        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        final allround ADMIN = (allround) getApplicationContext(); // 관리자 소환
        final allround SCHADD = (allround) getApplicationContext(); //학교 주소
        final allround SCHPH = (allround) getApplicationContext(); //학교 전화번호
        final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환
        final allround GUGU_TOTAL = (allround) getApplicationContext(); //구구단 점수

        ID.getID();
        SCHOOL.getSCHOOL();
        SCHADD.getSCHADD();
        SCHPH.getSCHPH();
        NICKNAME.getSCHPH();
        GUGU_TOTAL.getGUGU_TOTAL();

        go_other_menu = findViewById(R.id.go_game_main);
        go_ranking = findViewById(R.id.btn_gugu_list);

        go_other_menu.setOnClickListener(this);
        go_ranking.setOnClickListener(this);


        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.go_game_main) {
            Intent intent01 = new Intent( game_ranking.this, game_main.class);
            startActivity(intent01);
        }
        if(v.getId() == R.id.btn_gugu_list) {
            Intent intent02 = new Intent( game_ranking.this, game_ranking_in.class);
            startActivity(intent02);
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent03 = new Intent(this, MainActivity.class);
            startActivity(intent03);
        }


        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(this, configActivity.class);
            startActivity(intent6);
        }
    }
}
