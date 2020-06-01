package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.app.usage.NetworkStats;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

//설정화면
public class configActivity extends AppCompatActivity {

    TextView show_id; //아이디 받아오기
    TextView show_school;
    TextView show_nick;
    TextView ADMIN;
    Button btn_ADMIN;
    ImageView Profile_image;

    int admin_s;
    int my_char;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        allround ID = (allround) getApplicationContext();
        allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        allround ADMIN = (allround) getApplicationContext();
        final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환
        final allround Char_head = (allround) getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);



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
        }
        if (admin_s == 2) {
            btn_ADMIN.setVisibility(Button.VISIBLE);
        }

        Profile_image = findViewById(R.id.profile_image);
        my_char = Char_head.getChar_head();
        if (my_char == 0){
            Profile_image.setImageResource(R.drawable.ex_char1);
        }
        else if (my_char == 1){
            Profile_image.setImageResource(R.drawable.ex_char2);
        }

    }
    /*여기에 이제 받은 userID가 DB에 user T에 userId랑 같을때,
     * 저장된 학교이름이랑 소유하고있는 아이템코드 가져와서 캐릭터보여주는 .php를 연동하는 코드 작성하기
     *
     *
     * 스토리보드에있는 회원이 작성한 글이나 댓글 알림설정 등은 큰 기능들이 완성되고 추후에 보완하기*/

}

