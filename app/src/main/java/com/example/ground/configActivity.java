package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.app.usage.NetworkStats;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

//설정화면
public class configActivity extends AppCompatActivity implements View.OnClickListener{

    TextView show_id; //아이디 받아오기
    TextView show_school;
    TextView show_nick;
    TextView ADMIN;
    ImageView I_char_hair, I_char_face, I_char_cloth, I_char_acce;
    Button top_navi, btn_ADMIN, btn_change, btn_my_forum;
    ImageView Profile_image;


    int admin_s;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        allround ID = (allround) getApplicationContext();
        allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        allround ADMIN = (allround) getApplicationContext();
        final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환
        allround Char_hair = (allround) getApplicationContext();
        allround Char_face = (allround) getApplicationContext();
        allround Char_cloth = (allround) getApplicationContext();
        allround Char_acce = (allround) getApplicationContext();
        int MY_Char_hair = Char_hair.getChar_hair();
        int MY_Char_face = Char_face.getChar_face();
        int MY_Char_cloth = Char_cloth.getChar_cloth();
        int MY_Char_acce = Char_acce.getChar_acce();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        btn_change = findViewById(R.id.btn_config_change);
        btn_my_forum = findViewById(R.id.btn_config_my_forum);
        top_navi = findViewById(R.id.top_navi);

        I_char_hair = findViewById(R.id.MY_char_hair);
        I_char_face = findViewById(R.id.MY_char_face);
        I_char_cloth = findViewById(R.id.MY_char_cloth);
        I_char_acce = findViewById(R.id.MY_char_acce);

        top_navi.setOnClickListener(this);
        btn_change.setOnClickListener(this);
        btn_my_forum.setOnClickListener(this);

        String temp01 = ID.getID();
        show_id = findViewById(R.id.show_id);
        show_id.setText(temp01);

        String temp02 = SCHOOL.getSCHOOL();
        show_school = findViewById(R.id.config_school);
        show_school.setText(temp02);

        String temp03 = NICKNAME.getNICKNAME();
        show_nick = findViewById(R.id.show_nick);
        show_nick.setText(temp03);

        Log.d("TEST1234", "유저 아이디 : " + temp01);
        Log.d("TEST1234", "학교 이름 : " + temp02);
        Log.d("TEST1234", "유저 닉네임 : " + temp03);


        admin_s = ADMIN.getADMIN();

        Log.d("TEST1234", "관리자 번호 : " + admin_s);

        btn_ADMIN = findViewById(R.id.btn_admin);

        if (admin_s == 0) {
            btn_ADMIN.setVisibility(Button.GONE);

        }
        if (admin_s == 1) {
            btn_ADMIN.setVisibility(Button.VISIBLE);
            btn_change.setVisibility(Button.GONE);//추가
            btn_my_forum.setVisibility(Button.GONE);//
            btn_change.setEnabled(false);
            btn_my_forum.setEnabled(false);

        }
        if (admin_s == 2) {
            btn_ADMIN.setVisibility(Button.VISIBLE);
            btn_change.setVisibility(Button.GONE);//추가
            btn_change.setEnabled(false);
        }

        Profile_image = findViewById(R.id.profile_image);
        if (MY_Char_acce == 0) { // 악세
            I_char_acce.setImageResource(R.drawable.char_blind);
        } else if (MY_Char_acce == 1) {
            I_char_acce.setImageResource(R.drawable.char_acce_gom);
        }

        if (MY_Char_face == 0) { // 얼굴
            I_char_face.setImageResource(R.drawable.face_default);
        } else if (MY_Char_face == 1) {
            I_char_face.setImageResource(R.drawable.face_default_black);
        }
        if (MY_Char_cloth == 0) { // 옷
            I_char_cloth.setImageResource(R.drawable.cloth_default);
        } else if (MY_Char_cloth == 1) {
            I_char_cloth.setImageResource(R.drawable.char_cloth_gom);
        }
        if (MY_Char_hair == 0) { // 머리
            I_char_hair.setImageResource(R.drawable.hair_default);
        } else if (MY_Char_hair == 1) {
            I_char_hair.setImageResource(R.drawable.char_blind);
        }

    }

    public void onClick(View v) {
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
        }
        if (v.getId() == R.id.btn_config_my_forum) { // 설정
            Intent intent6 = new Intent(this, config_my_forum_forum.class);
            startActivity(intent6);
        }
        if (v.getId() == R.id.btn_config_change) { // 설정
            Intent intent6 = new Intent(this, config_change.class);
            startActivity(intent6);
        }
    }

    /*여기에 이제 받은 userID가 DB에 user T에 userId랑 같을때,
     * 저장된 학교이름이랑 소유하고있는 아이템코드 가져와서 캐릭터보여주는 .php를 연동하는 코드 작성하기
     *
     *
     * 스토리보드에있는 회원이 작성한 글이나 댓글 알림설정 등은 큰 기능들이 완성되고 추후에 보완하기*/

}

