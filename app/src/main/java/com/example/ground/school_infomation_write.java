package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class school_infomation_write extends AppCompatActivity implements View.OnClickListener {


    Button btn_save, btn_back, go_forum_image;
    Button top_navi, btn_setting;
    Button btn_image_add;


    private EditText text_write, title_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_information_write);

        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        final allround ADMIN = (allround) getApplicationContext();

        btn_save = findViewById(R.id.btn_save); //글쓰기 버튼 = 저장 버튼
        btn_back = findViewById(R.id.btn_back); //되돌아가기 버튼
        text_write = findViewById(R.id.text_wirte); //게시판 내용
        title_name = findViewById(R.id.title_name); //게시판 제목
        //go_forum_image = findViewById(R.id.go_forum_image);
        top_navi = findViewById(R.id.top_navi); //상단바
        btn_setting = findViewById(R.id.btn_setting); //설정 버튼
        btn_image_add = findViewById(R.id.btn_forum_write_add); //파일첨부 버튼


        btn_save.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        //go_forum_image.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
        btn_image_add.setOnClickListener(this);


        String schName = SCHOOL.getSCHOOL();

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_save) { //게시판작성에서 완료하기를 눌렀을때
            Log.d("TEST1234", "저장하기 버튼 눌림");
            String schTi = title_name.getText().toString();
            String schCon = text_write.getText().toString();



            schTi = schTi.replace("'", "''");
            schCon = schCon.replace("'", "''");

            Response.Listener<String> responseListener = new Response.Listener<String>() {//volley
                @Override
                public void onResponse(String response) {
                    try {
                        Log.d("TEST1234", "쓰레드확인1:" + Thread.currentThread());
                        JSONObject jasonObject = new JSONObject(response);//SchoolForum.php에 response
                        boolean success = jasonObject.getBoolean("success");//SchoolForum.php에 sucess

                        //String ssss = jasonObject.getString("id");
                        String sta = jasonObject.getString("str");
                        Log.d("TEST1234", "success:" + success);
                        //Log.d("TEST1234", "정상성공?:" + ssss);
                        Log.d("TEST1234", "php->안스 값:" + sta);

                        Toast.makeText(getApplicationContext(), "글이 작성되었습니다", Toast.LENGTH_SHORT).show();
                        Log.d("TEST1234", "쓰레드확인2:" + Thread.currentThread());


                        Intent intent = new Intent(school_infomation_write.this, school_information.class);
                        startActivity(intent);
                        Log.d("TEST1234", "쓰레드확인3:" + Thread.currentThread());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };


            school_infomation_write_request sfr = new school_infomation_write_request(schName, schTi, schCon, responseListener);  //ㅏㅇ이씨..
            RequestQueue queue = Volley.newRequestQueue(school_infomation_write.this);
            queue.add(sfr);
        }

       /* if (v.getId() == R.id.go_forum_forum) { //포럼 버튼을 눌렀을때
            Intent intent1 = new Intent(schoolforumwrite.this, forum_image.class);
            startActivity(intent1);
        }*/

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
