package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//그림게시판
public class game_main extends AppCompatActivity implements View.OnClickListener{

    Button go_other_menu;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        go_other_menu = (Button) findViewById(R.id.go_game_ranking);

        go_other_menu.setOnClickListener(this);

    }

    public void onClick(View v) {
        if(v.getId() == R.id.go_game_ranking) {
            Intent intent01 = new Intent( game_main.this, game_ranking.class);
            startActivity(intent01);
        }
        if(v.getId() == R.id.btn_school_information_cancel) {
            finish();
        }
    }
}
