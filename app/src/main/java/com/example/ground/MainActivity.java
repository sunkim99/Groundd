package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_school, btn_board, btn_character, btn_event, btn_game, btn_setting;
    TextView et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_school = (Button) findViewById(R.id.btn_school);
        btn_board = findViewById(R.id.btn_board);
        btn_character = findViewById(R.id.btn_character);
        btn_event =  findViewById(R.id.btn_event);
        btn_game = findViewById(R.id.btn_game);
        btn_setting = findViewById(R.id.btn_setting);




        btn_school.setOnClickListener(this);
        btn_board.setOnClickListener(this);
        btn_character.setOnClickListener(this);
        btn_event.setOnClickListener(this);
        btn_game.setOnClickListener(this);
        btn_setting.setOnClickListener(this);

        Intent intent2 = getIntent();
        String userID = intent2.getStringExtra("userID");
        et = findViewById(R.id.editText11);
        et.setText(userID);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_school) { //학교 게시판
            Intent intent1 = new Intent(MainActivity.this, school_information.class);
            startActivity(intent1);
        }
        if (v.getId() == R.id.btn_board) { // 게시판
            Intent intent2 = new Intent(MainActivity.this, forum_forum.class);
            startActivity(intent2);
        }
        if (v.getId() == R.id.btn_character) { // 내 캐릭터
            Intent intent3 = new Intent(MainActivity.this, char_inven.class);
            startActivity(intent3);
        }
        if (v.getId() == R.id.btn_event) { // 공지 이벤트
            Intent intent4 = new Intent(MainActivity.this, notice_notice.class);
            startActivity(intent4);
        }
        /*if (v.getId() == R.id.btn_game) { // 게임
            Intent intent5 = new Intent(MainActivity.this, NoticeEvent.class);
            startActivity(intent5);
        }*/
        if (v.getId() == R.id.btn_setting) { // 설정
            String userID= et.getText().toString();
            Intent intent6 = new Intent(MainActivity.this, configActivity.class);

            intent6.putExtra("userID", userID);
            startActivity(intent6);
        }



    }
}
