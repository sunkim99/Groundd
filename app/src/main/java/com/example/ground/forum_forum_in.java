package com.example.ground;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
//게시판에서 게시물을 눌렀을때 해당게시물 보여지는화면

public class forum_forum_in extends AppCompatActivity implements View.OnClickListener {

    Button btn_image, write, cancel;
    Button top_navi, btn_setting;

    TextView id_notTi, id_notCon, id_notNum, id_notDate, id_userNick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_forum_in);
        //final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환

        btn_image = findViewById(R.id.go_forum_image);
        write = findViewById(R.id.go_forum_write); //댓글쓰기버튼
        cancel = findViewById(R.id.go_forum_forum);

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        btn_image.setOnClickListener(this);
        write.setOnClickListener(this); //댓글쓰기 이벤트
        //cancel.setOnClickListener(this); <--------------------------- 활성화시 오류
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);


        Intent intent1 = getIntent();
        String notNum1 = intent1.getStringExtra("check_position1");
        Integer notNum= Integer.valueOf(notNum1);
        id_notNum = findViewById(R.id.id_notNum);
        id_notNum.setText(notNum1);
        Log.d("TEST1234", String.valueOf(notNum));


        /*String userNick = NICKNAME.getNICKNAME();


        id_userNick.setText(userNick);*/ //닉네임은 이렇게말고 쿼리문에서 가져와야하는데 작성하는거 모르겠음..
        id_userNick = findViewById(R.id.id_userNick);
        id_notTi = findViewById(R.id.id_notTi);
        id_notDate = findViewById(R.id.id_notDate);
        id_notCon = findViewById(R.id.id_notCon);

        Log.d("TEST1234", "[School Info] 쓰레드확인!!!");
        //학교 정보 가져오기
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jasonObject = new JSONObject(response);
                    boolean success = jasonObject.getBoolean("success");
                    if (success) {
                        Log.d("TEST1234", "[Forum] 쓰레드확인");

                        String number = jasonObject.getString("notNum");
                        Log.d("TEST1234", "[Forum] 쓰레드확인1:");
                        Log.d("TEST1234", "[Forum]  가져온 글번호 :" + number);
                        //String schName11 = schName1;
                        //Log.d("TEST1234", "학교이름 : " + schName1);


                        // SCHOOL.setSCHOOL(schName11);  // 전역변수는 schName11의 값을 가짐


                        String userID = jasonObject.getString("userID");
                        Log.d("TEST1234", "[Forum] 아이디 " + userID);

                        String title = jasonObject.getString("notTi");
                        Log.d("TEST1234", "[Forum]  제목 " + title);
                        String notCon = jasonObject.getString("notCon");

                        String notDate = jasonObject.getString("notDate");

                        Log.d("TEST1234", "[Forum] 쓰레드확인2:");


                        id_userNick.setText(userID);
                        id_notTi.setText(title);
                        id_notCon.setText(notCon);
                        id_notDate.setText(notDate);



                    } else {
                        Log.d("TEST1234", "[Forum] 게시글 정보");
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };
        forum_froum_detail_request ffd = new forum_froum_detail_request(notNum, responseListener);
        RequestQueue queue = Volley.newRequestQueue(forum_forum_in.this);
        queue.add(ffd);

        //댓글 불러오기
        Response.Listener<String> comment_response= new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jasonObject = new JSONObject(response);
                    boolean success = jasonObject.getBoolean("success");
                    if (success) {
                        Log.d("TEST1234", "[Forum] 쓰레드확인");

                        String number = jasonObject.getString("notNum");
                        Log.d("TEST1234", "[Forum] 쓰레드확인1:");
                        Log.d("TEST1234", "[Forum]  가져온 글번호 :" + number);
                        //String schName11 = schName1;
                        //Log.d("TEST1234", "학교이름 : " + schName1);


                        // SCHOOL.setSCHOOL(schName11);  // 전역변수는 schName11의 값을 가짐


                        String userID = jasonObject.getString("userID");
                        Log.d("TEST1234", "[Forum] 아이디 " + userID);

                        String title = jasonObject.getString("notTi");
                        Log.d("TEST1234", "[Forum]  제목 " + title);
                        String notCon = jasonObject.getString("notCon");

                        String notDate = jasonObject.getString("notDate");

                        Log.d("TEST1234", "[Forum] 쓰레드확인2:");


                        id_userNick.setText(userID);
                        id_notTi.setText(title);
                        id_notCon.setText(notDate);
                        id_notDate.setText(notCon);



                    } else {
                        Log.d("TEST1234", "[Forum] 게시글 정보");
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };
        forum_froum_detail_request comment = new forum_froum_detail_request(notNum, comment_response);
        RequestQueue comment_queue = Volley.newRequestQueue(forum_forum_in.this);
        comment_queue.add(comment);



    }



    @Override
    public void onClick(View v) {


    }
}