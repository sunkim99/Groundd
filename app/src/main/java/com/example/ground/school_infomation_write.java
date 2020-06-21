package com.example.ground;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
//학교 게시판에서 학교관리자가 게시물을 작성하는 자바파일

public class school_infomation_write extends AppCompatActivity implements View.OnClickListener {


    Button btn_save, btn_back, go_forum_image;
    Button top_navi, btn_setting;
    Button btn_image_add;
    TextView school_name, school_add, school_tel, sch_user_nickname, sch_user_id;
    private EditText text_write, title_name;
    ImageView I_char_hair, I_char_face, I_char_cloth, I_char_acce;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_information_write);

        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        final allround ADMIN = (allround) getApplicationContext();
        final allround SCHADD = (allround) getApplicationContext(); //학교 주소
        final allround SCHPH = (allround) getApplicationContext(); //학교 전화번호
        final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환

        btn_save = findViewById(R.id.btn_save); //글쓰기 버튼 = 저장 버튼
        btn_back = findViewById(R.id.btn_back); //되돌아가기 버튼
        text_write = findViewById(R.id.text_wirte); //게시판 내용
        title_name = findViewById(R.id.title_name); //게시판 제목
        //go_forum_image = findViewById(R.id.go_forum_image);
        top_navi = findViewById(R.id.top_navi); //상단바
        btn_setting = findViewById(R.id.btn_setting); //설정 버튼
        btn_image_add = findViewById(R.id.btn_forum_write_add); //파일첨부 버튼
        school_name = findViewById(R.id.school_name); //학교 이름
        school_add = findViewById(R.id.school_add); //학교 주소
        school_tel = findViewById(R.id.school_tel); //학교 연락처
        sch_user_nickname = findViewById(R.id.sch_user_nickname); //학교 관리자 닉네임
        sch_user_id = findViewById(R.id.sch_user_id); //학교 관리자 아이디


        btn_save.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        //go_forum_image.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
        btn_image_add.setOnClickListener(this);


        String schName = SCHOOL.getSCHOOL();
        String schAdd = SCHOOL.getSCHADD();
        String schPh = SCHOOL.getSCHPH();
        String schAdminID = ID.getID();
        String schAdminNickName = NICKNAME.getNICKNAME();

        school_name.setText(schName);
        school_add.setText(schAdd);
        school_tel.setText(schPh);
        sch_user_id.setText(schAdminID);
        sch_user_nickname.setText(schAdminNickName);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_save) { //게시판작성에서 완료하기를 눌렀을때
            Log.d("TEST1234", "[School] 저장하기 버튼 눌림");
            AlertDialog.Builder builder = new AlertDialog.Builder(school_infomation_write.this);
            builder.setTitle("저장");
            builder.setMessage("게시글을 올릴까요?");
            builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String schTi = title_name.getText().toString();
                    String schCon = text_write.getText().toString();
                    String schName = school_name.getText().toString(); //textview에 저장된 학교이름 읽어오기

                    schTi = schTi.replace("'", "''");
                    schCon = schCon.replace("'", "''");

                    Response.Listener<String> responseListener = new Response.Listener<String>() {//volley
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.d("TEST1234", "[School] 쓰레드확인1" + Thread.currentThread());
                                JSONObject jasonObject = new JSONObject(response);//SchoolForum.php에 response
                                boolean success = jasonObject.getBoolean("success");//SchoolForum.php에 sucess

                                //String ssss = jasonObject.getString("id");
                                String sta = jasonObject.getString("str");
                                Log.d("TEST1234", "[School] 1" + success);
                                //Log.d("TEST1234", "정상성공?:" + ssss);
                                Log.d("TEST1234", "[School] 2" + sta);

                                Toast.makeText(getApplicationContext(), "글이 작성되었습니다", Toast.LENGTH_SHORT).show();
                                Log.d("TEST1234", "[School] 쓰레드확인2" + Thread.currentThread());

                                finish(); // 위에 intent사용하면 intent 됐을때, 학교메인에서 학교정보를 보여주는칸이 오류가난다.. 그래서 finish 사용
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };


                    school_infomation_write_request sfr = new school_infomation_write_request(schName, schTi, schCon, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(school_infomation_write.this);
                    queue.add(sfr);
                }
            });
            builder.setNegativeButton("아니요", null);
          /*  builder.setNeutralButton("게시판으로 돌아가기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });*/
            builder.create().show();

        }



        if (v.getId() == R.id.btn_back) { //게시판 작성화면에서 빨간버튼을 눌렀을때
            finish();
        }

        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(school_infomation_write.this, MainActivity.class);
            startActivity(intent1);
        }


        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(school_infomation_write.this, configActivity.class);
            startActivity(intent6);
        }


    }
}
