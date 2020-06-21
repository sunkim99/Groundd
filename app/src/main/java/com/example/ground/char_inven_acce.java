package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class char_inven_acce extends AppCompatActivity implements View.OnClickListener {
    Button acce_gom, acce_default, acce_cat_ear;
    TextView show_nick;
    ImageView I_char_hair, I_char_face, I_char_cloth, I_char_acce;
    Button top_navi, btn_setting;
    Button btn_hair, btn_face, btn_acce, btn_cloth;


    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_inven_acce);
        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        final allround ADMIN = (allround) getApplicationContext(); // 관리자 소환
        final allround SCHADD = (allround) getApplicationContext(); //학교 주소
        final allround SCHPH = (allround) getApplicationContext(); //학교 전화번호
        final allround USERNUM = (allround) getApplicationContext(); // 전역변수 USERNUM
        final allround USERNAME = (allround) getApplicationContext(); // 전역변수 USERNUM

        ID.getID();
        SCHOOL.getSCHOOL();
        SCHADD.getSCHADD();
        SCHPH.getSCHPH();
        USERNUM.getUSERNUM();
        USERNAME.getUSERNAME();

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

        acce_gom = findViewById(R.id.acce_gom);
        acce_default = findViewById(R.id.acce_default);
        acce_cat_ear = findViewById(R.id.acce_cat_ear);

        acce_cat_ear.setOnClickListener(this);
        acce_gom.setOnClickListener(this);
        acce_default.setOnClickListener(this);

        I_char_hair = findViewById(R.id.MY_char_hair);
        I_char_face = findViewById(R.id.MY_char_face);
        I_char_cloth = findViewById(R.id.MY_char_cloth);
        I_char_acce = findViewById(R.id.MY_char_acce);

        if (MY_Char_acce == 0) {
            I_char_acce.setImageResource(R.drawable.char_blind);
            acce_default.setBackgroundResource(R.drawable.char_blind_decide);
            acce_gom.setBackgroundResource(R.drawable.char_blind_none_decide);
            acce_cat_ear.setBackgroundResource(R.drawable.char_blind_none_decide);
        } else if (MY_Char_acce == 1) {
            I_char_acce.setImageResource(R.drawable.char_acce_gom);
            acce_default.setBackgroundResource(R.drawable.char_blind_none_decide);
            acce_gom.setBackgroundResource(R.drawable.char_blind_decide);
            acce_cat_ear.setBackgroundResource(R.drawable.char_blind_none_decide);
        } else if (MY_Char_acce == 2) {
            I_char_acce.setImageResource(R.drawable.char_acce_cat_ear);
            acce_default.setBackgroundResource(R.drawable.char_blind_none_decide);
            acce_gom.setBackgroundResource(R.drawable.char_blind_none_decide);
            acce_cat_ear.setBackgroundResource(R.drawable.char_blind_decide);
        }

        if (MY_Char_face == 0) { // 얼굴
            I_char_face.setImageResource(R.drawable.face_default);
        } else if (MY_Char_face == 1) {
            I_char_face.setImageResource(R.drawable.face_default_black);
        } else if (MY_Char_face == 2) {
            I_char_face.setImageResource(R.drawable.face_blame);
        } else if (MY_Char_face == 3) {
            I_char_face.setImageResource(R.drawable.face_pretty);
        }

        if (MY_Char_cloth == 0) { // 옷
            I_char_cloth.setImageResource(R.drawable.cloth_default);
        } else if (MY_Char_cloth == 1) {
            I_char_cloth.setImageResource(R.drawable.char_cloth_gom);
        } else if (MY_Char_cloth == 2) {
            I_char_cloth.setImageResource(R.drawable.char_cloth_daram);
        }

        if (MY_Char_hair == 0) { // 머리
            I_char_hair.setImageResource(R.drawable.hair_default);
        } else if (MY_Char_hair == 1) {
            I_char_hair.setImageResource(R.drawable.char_blind);
        } else if (MY_Char_hair == 2) {
            I_char_hair.setImageResource(R.drawable.hair_long);
        }
    }


    @Override
    public void onClick(View v) {
        allround Char_acce = (allround) getApplicationContext();////////
        int MY_Char_acce = Char_acce.getChar_acce();

        if (v.getId() == R.id.acce_default) {
            Char_acce.setChar_acce(0);
            MY_Char_acce = Char_acce.getChar_acce();
            if (MY_Char_acce == 0) {
                I_char_acce.setImageResource(R.drawable.char_blind);
                acce_default.setBackgroundResource(R.drawable.char_blind_decide);
                acce_gom.setBackgroundResource(R.drawable.char_blind_none_decide);
                acce_cat_ear.setBackgroundResource(R.drawable.char_blind_none_decide);
            } else if (MY_Char_acce == 1) {
                I_char_acce.setImageResource(R.drawable.char_acce_gom);
                acce_default.setBackgroundResource(R.drawable.char_blind_none_decide);
                acce_gom.setBackgroundResource(R.drawable.char_blind_decide);
                acce_cat_ear.setBackgroundResource(R.drawable.char_blind_none_decide);
            } else if (MY_Char_acce == 2) {
                I_char_acce.setImageResource(R.drawable.char_acce_cat_ear);
                acce_default.setBackgroundResource(R.drawable.char_blind_none_decide);
                acce_gom.setBackgroundResource(R.drawable.char_blind_none_decide);
                acce_cat_ear.setBackgroundResource(R.drawable.char_blind_decide);
            }
        }
        if (v.getId() == R.id.acce_gom) {
            Char_acce.setChar_acce(1);
            MY_Char_acce = Char_acce.getChar_acce();
            if (MY_Char_acce == 0) {
                I_char_acce.setImageResource(R.drawable.char_blind);
                acce_default.setBackgroundResource(R.drawable.char_blind_decide);
                acce_gom.setBackgroundResource(R.drawable.char_blind_none_decide);
                acce_cat_ear.setBackgroundResource(R.drawable.char_blind_none_decide);
            } else if (MY_Char_acce == 1) {
                I_char_acce.setImageResource(R.drawable.char_acce_gom);
                acce_default.setBackgroundResource(R.drawable.char_blind_none_decide);
                acce_gom.setBackgroundResource(R.drawable.char_blind_decide);
                acce_cat_ear.setBackgroundResource(R.drawable.char_blind_none_decide);
            } else if (MY_Char_acce == 2) {
                I_char_acce.setImageResource(R.drawable.char_acce_cat_ear);
                acce_default.setBackgroundResource(R.drawable.char_blind_none_decide);
                acce_gom.setBackgroundResource(R.drawable.char_blind_none_decide);
                acce_cat_ear.setBackgroundResource(R.drawable.char_blind_decide);
            }
        }
        if (v.getId() == R.id.acce_cat_ear) {
            Char_acce.setChar_acce(2);
            MY_Char_acce = Char_acce.getChar_acce();
            if (MY_Char_acce == 0) {
                I_char_acce.setImageResource(R.drawable.char_blind);
                acce_default.setBackgroundResource(R.drawable.char_blind_decide);
                acce_gom.setBackgroundResource(R.drawable.char_blind_none_decide);
                acce_cat_ear.setBackgroundResource(R.drawable.char_blind_none_decide);
            } else if (MY_Char_acce == 1) {
                I_char_acce.setImageResource(R.drawable.char_acce_gom);
                acce_default.setBackgroundResource(R.drawable.char_blind_none_decide);
                acce_gom.setBackgroundResource(R.drawable.char_blind_decide);
                acce_cat_ear.setBackgroundResource(R.drawable.char_blind_none_decide);
            } else if (MY_Char_acce == 2) {
                I_char_acce.setImageResource(R.drawable.char_acce_cat_ear);
                acce_default.setBackgroundResource(R.drawable.char_blind_none_decide);
                acce_gom.setBackgroundResource(R.drawable.char_blind_none_decide);
                acce_cat_ear.setBackgroundResource(R.drawable.char_blind_decide);
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

        if (v.getId() == R.id.btn_face) {
            Intent intent = new Intent(this, char_inven_face.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.btn_cloth) {
            Intent intent = new Intent(this, char_inven_cloth.class);
            startActivity(intent);
        }
    }

}
