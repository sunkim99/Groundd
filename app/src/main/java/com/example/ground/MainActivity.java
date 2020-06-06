package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//메인
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_school, btn_board, btn_character, btn_event, btn_game, btn_setting;
    TextView et;


    int admin_s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        final allround ADMIN = (allround) getApplicationContext(); // 관리자 소환
        final allround SCHADD = (allround) getApplicationContext(); //학교 주소
        final allround SCHPH = (allround) getApplicationContext(); //학교 전화번호
        final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환

        ///////////////////////////////////////////////////////

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_school = (Button) findViewById(R.id.btn_school);
        btn_board = findViewById(R.id.btn_board);
        btn_character = findViewById(R.id.btn_character);
        btn_event = findViewById(R.id.btn_event);
        btn_game = findViewById(R.id.btn_game);
        btn_setting = findViewById(R.id.btn_setting);


        btn_school.setOnClickListener(this);
        btn_board.setOnClickListener(this);
        btn_character.setOnClickListener(this);
        btn_event.setOnClickListener(this);
        btn_game.setOnClickListener(this);
        btn_setting.setOnClickListener(this);

        Intent intent2 = getIntent();
        String userID1 = intent2.getStringExtra("userID");
        et = findViewById(R.id.editText11);
        et.setText(userID1);

        ID.setID(userID1); // 전역변수는 userID1의 값을 가짐


        admin_s = ADMIN.getADMIN();
        //학교 관리자는 학교메뉴 이외에 다른 버튼은 누르지 못함.
        if (admin_s == 1) { //학교 관리자
            btn_board.setEnabled(false);
            btn_character.setEnabled(false);
            btn_event.setEnabled(false);
            btn_event.setEnabled(false);
            btn_game.setEnabled(false);
        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_school) { //학교 게시판
            Intent intent1 = new Intent(MainActivity.this, school_information.class);
            Intent intent2 = getIntent();
            String userID1 = intent2.getStringExtra("userID");
            intent1.putExtra("userID", userID1);
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
        if (v.getId() == R.id.btn_game) { // 게임
            Intent intent5 = new Intent(MainActivity.this, game_main.class);
            startActivity(intent5);

            //Log.d("TEST1234", "게임버튼 눌림" + Thread.currentThread());
        }
        if (v.getId() == R.id.btn_setting) { // 설정

            Intent intent6 = new Intent(MainActivity.this, configActivity.class);

            startActivity(intent6);
        }


    }
}
