package com.example.ground;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class char_inven extends AppCompatActivity implements View.OnClickListener {
    Button ex_char1, ex_char2;
    TextView show_nick;
    ImageView MY_char;
    Button top_navi, btn_setting;
    Button btn_head, btn_hat, btn_face, btn_acce, btn_bg, btn_cloth;

    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_inven);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);
        btn_head = findViewById(R.id.btn_head);
        btn_head.setOnClickListener(this);
        btn_hat = findViewById(R.id.btn_hat);
        btn_hat.setOnClickListener(this);
        btn_face = findViewById(R.id.btn_face);
        btn_face.setOnClickListener(this);
        btn_acce = findViewById(R.id.btn_acce);
        btn_acce.setOnClickListener(this);
        btn_bg = findViewById(R.id.btn_bg);
        btn_bg.setOnClickListener(this);
        btn_cloth = findViewById(R.id.btn_cloth);
        btn_cloth.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);

        allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환
        allround Char_head = (allround) getApplicationContext();
        int MY_Char_head = Char_head.getChar_head();

        String temp01 = NICKNAME.getNICKNAME();
        show_nick = findViewById(R.id.nickname_view);
        show_nick.setText(temp01);

        ex_char1 = findViewById(R.id.ex_char1);
        ex_char2 = findViewById(R.id.ex_char2);

        ex_char1.setOnClickListener(this);
        ex_char2.setOnClickListener(this);

        MY_char = findViewById(R.id.MY_char);
        if (MY_Char_head == 0) {
            MY_char.setImageResource(R.drawable.ex_char1);
        } else if (MY_Char_head == 1) {
            MY_char.setImageResource(R.drawable.ex_char2);
        }

    }


    @Override
    public void onClick(View v) {
        allround Char_head = (allround) getApplicationContext();////////
        int MY_Char_head = Char_head.getChar_head();

        if (v.getId() == R.id.ex_char1) {
            Char_head.setChar_head(0);
            if (MY_Char_head == 0) {
                MY_char.setImageResource(R.drawable.ex_char1);
            } else if (MY_Char_head == 1) {
                MY_char.setImageResource(R.drawable.ex_char2);
            }
        }
        if (v.getId() == R.id.ex_char2) {
            Char_head.setChar_head(1);
            if (MY_Char_head == 0) {
                MY_char.setImageResource(R.drawable.ex_char1);
            } else if (MY_Char_head == 1) {
                MY_char.setImageResource(R.drawable.ex_char2);
            }
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent02 = new Intent(char_inven.this, MainActivity.class);
            startActivity(intent02);
        }


        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent03 = new Intent(char_inven.this, configActivity.class);
            startActivity(intent03);
        }

        if (v.getId() == R.id.btn_head) { // 설정
            Intent intent = new Intent(this, char_inven_head.class);
            intent.putExtra("data", "Test Popup");
            startActivityForResult(intent, 1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("result");
                txtResult.setText(result);
            }
        }
    }
}
