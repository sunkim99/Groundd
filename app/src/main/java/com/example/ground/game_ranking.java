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

        go_other_menu = findViewById(R.id.go_game_main);
        go_ranking = findViewById(R.id.btn_gugu);

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
        if(v.getId() == R.id.btn_gugu) {
            Intent intent01 = new Intent( game_ranking.this, game_ranking_in.class);
            startActivity(intent01);
        }
        if(v.getId() == R.id.btn_school_information_cancel) {
            finish();
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
        }


        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(this, configActivity.class);
            startActivity(intent6);
        }
    }
}
