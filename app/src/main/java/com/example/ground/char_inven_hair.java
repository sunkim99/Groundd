package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class char_inven_hair extends AppCompatActivity implements View.OnClickListener {
    Button ex_char1, ex_char2;
    TextView show_nick;
    ImageView MY_char;
    Button top_navi, btn_setting;
    Button btn_hair, btn_face, btn_acce, btn_cloth;

    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_inven_hair);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);
        btn_hair = findViewById(R.id.btn_hair);
        btn_hair.setOnClickListener(this);
        btn_face = findViewById(R.id.btn_face);
        btn_face.setOnClickListener(this);
        btn_acce = findViewById(R.id.btn_acce);
        btn_acce.setOnClickListener(this);
        btn_cloth = findViewById(R.id.btn_cloth);
        btn_cloth.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);

        allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환
        allround Char_head = (allround) getApplicationContext();
        int MY_Char_head = Char_head.getChar_hair();

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
        int MY_Char_head = Char_head.getChar_hair();

        if (v.getId() == R.id.ex_char1) {
            Char_head.setChar_hair(0);
            MY_Char_head = Char_head.getChar_hair();
            if (MY_Char_head == 0) {
                MY_char.setImageResource(R.drawable.ex_char1);
            } else if (MY_Char_head == 1) {
                MY_char.setImageResource(R.drawable.ex_char2);
            }
        }
        if (v.getId() == R.id.ex_char2) {
            Char_head.setChar_hair(1);
            MY_Char_head = Char_head.getChar_hair();
            if (MY_Char_head == 0) {
                MY_char.setImageResource(R.drawable.ex_char1);
            } else if (MY_Char_head == 1) {
                MY_char.setImageResource(R.drawable.ex_char2);
            }
        }


        if (v.getId() == R.id.top_navi) {
            Intent intent02 = new Intent(this, MainActivity.class);
            startActivity(intent02);
        }


        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent03 = new Intent(this, configActivity.class);
            startActivity(intent03);
        }

        if (v.getId() == R.id.btn_face) {
            Intent intent = new Intent(this, char_inven_face.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.btn_cloth) {
            Intent intent = new Intent(this, char_inven_cloth.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.btn_acce) {
            Intent intent = new Intent(this, char_inven_acce.class);
            startActivity(intent);
        }
    }

}
