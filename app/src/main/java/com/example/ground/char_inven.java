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

public class char_inven extends AppCompatActivity implements View.OnClickListener{
    Button ex_char1, ex_char2;
    TextView show_nick;
    ImageView MY_char;
    Button top_navi, btn_setting;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_inven);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);

        allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환
        allround Char_head = (allround) getApplicationContext();
        int MY_Char_head = Char_head.getChar_head();

        String temp01 = NICKNAME.getNICKNAME();
        show_nick = findViewById(R.id.nickname_view);
        show_nick.setText(temp01);

        ex_char1 =  findViewById(R.id.ex_char1);
        ex_char2 =  findViewById(R.id.ex_char2);

        ex_char1.setOnClickListener(this);
        ex_char2.setOnClickListener(this);

        MY_char = findViewById(R.id.MY_char);
        if (MY_Char_head == 0){
            MY_char.setImageResource(R.drawable.ex_char1);
        }
        else if (MY_Char_head == 1){
            MY_char.setImageResource(R.drawable.ex_char2);
        }

}


    @Override
    public void onClick(View v) {
        allround Char_head = (allround) getApplicationContext();////////
        int MY_Char_head = Char_head.getChar_head();

        if (v.getId() == R.id.ex_char1) {
            Char_head.setChar_head(0);
            if (MY_Char_head == 0){
                MY_char.setImageResource(R.drawable.ex_char1);
            }
            else if (MY_Char_head == 1){
                MY_char.setImageResource(R.drawable.ex_char2);
            }
        }
        if (v.getId() == R.id.ex_char2) {
            Char_head.setChar_head(1);
            if (MY_Char_head == 0){
                MY_char.setImageResource(R.drawable.ex_char1);
            }
            else if (MY_Char_head == 1){
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
    }
}
