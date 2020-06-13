package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class char_inven_face extends AppCompatActivity implements View.OnClickListener {
    TextView show_nick;
    ImageView I_char_hair, I_char_face, I_char_cloth, I_char_acce;
    Button top_navi, btn_setting;
    Button btn_hair, btn_face, btn_acce, btn_cloth;
    Button face_default, face_default_black;

    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_inven_face);
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
        allround Char_hair = (allround) getApplicationContext();
        allround Char_face = (allround) getApplicationContext();
        allround Char_cloth = (allround) getApplicationContext();
        allround Char_acce = (allround) getApplicationContext();
        int MY_Char_hair = Char_hair.getChar_hair();
        int MY_Char_face = Char_face.getChar_face();
        int MY_Char_cloth = Char_cloth.getChar_cloth();
        int MY_Char_acce = Char_acce.getChar_acce();

        String temp01 = NICKNAME.getNICKNAME();
        show_nick = findViewById(R.id.nickname_view);
        show_nick.setText(temp01);

        I_char_hair = findViewById(R.id.MY_char_hair);
        I_char_face = findViewById(R.id.MY_char_face);
        I_char_cloth = findViewById(R.id.MY_char_cloth);
        I_char_acce = findViewById(R.id.MY_char_acce);

        face_default = findViewById(R.id.face_default);
        face_default_black = findViewById(R.id.face_black);

        face_default.setOnClickListener(this);
        face_default_black.setOnClickListener(this);

        if (MY_Char_acce == 0) { // 악세
            I_char_acce.setImageResource(R.drawable.char_blind);
        } else if (MY_Char_acce == 1) {
            I_char_acce.setImageResource(R.drawable.char_acce_gom);
        } else if (MY_Char_acce == 2) {
            I_char_acce.setImageResource(R.drawable.char_acce_cat_ear);
        }

        if (MY_Char_face == 0) { // 얼굴
            I_char_face.setImageResource(R.drawable.face_default);
            face_default.setBackgroundResource(R.drawable.char_blind_decide);
            face_default_black.setBackgroundResource(R.drawable.char_blind_none_decide);
        } else if (MY_Char_face == 1) {
            I_char_face.setImageResource(R.drawable.face_default_black);
            face_default.setBackgroundResource(R.drawable.char_blind_none_decide);
            face_default_black.setBackgroundResource(R.drawable.char_blind_decide);
        }
        if (MY_Char_cloth == 0) { // 옷
            I_char_cloth.setImageResource(R.drawable.cloth_default);
        } else if (MY_Char_cloth == 1) {
            I_char_cloth.setImageResource(R.drawable.char_cloth_gom);
        }
        else if (MY_Char_cloth == 2) {
            I_char_cloth.setImageResource(R.drawable.char_cloth_daram);
        }

        if (MY_Char_hair == 0) { // 머리
            I_char_hair.setImageResource(R.drawable.hair_default);
        } else if (MY_Char_hair == 1) {
            I_char_hair.setImageResource(R.drawable.char_blind);
        }
    }


    @Override
    public void onClick(View v) {
        allround Char_face = (allround) getApplicationContext();////////
        int MY_Char_face = Char_face.getChar_face();

        if (v.getId() == R.id.face_default) {
            Char_face.setChar_face(0);
            MY_Char_face = Char_face.getChar_face();
            if (MY_Char_face == 0) {
                I_char_face.setImageResource(R.drawable.face_default);
                face_default.setBackgroundResource(R.drawable.char_blind_decide);
                face_default_black.setBackgroundResource(R.drawable.char_blind_none_decide);
            } else if (MY_Char_face == 1) {
                I_char_face.setImageResource(R.drawable.face_default_black);
                face_default.setBackgroundResource(R.drawable.char_blind_none_decide);
                face_default_black.setBackgroundResource(R.drawable.char_blind_decide);
            }
        }
        if (v.getId() == R.id.face_black) {
            Char_face.setChar_face(1);
            MY_Char_face = Char_face.getChar_face();
            if (MY_Char_face == 0) {
                I_char_face.setImageResource(R.drawable.face_default);
                face_default.setBackgroundResource(R.drawable.char_blind_decide);
                face_default_black.setBackgroundResource(R.drawable.char_blind_none_decide);
            } else if (MY_Char_face == 1) {
                I_char_face.setImageResource(R.drawable.face_default_black);
                face_default.setBackgroundResource(R.drawable.char_blind_none_decide);
                face_default_black.setBackgroundResource(R.drawable.char_blind_decide);
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

        if (v.getId() == R.id.btn_hair) {
            Intent intent = new Intent(this, char_inven_hair.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.btn_acce) {
            Intent intent = new Intent(this, char_inven_acce.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.btn_cloth) {
            Intent intent = new Intent(this, char_inven_cloth.class);
            startActivity(intent);
        }
    }

}
