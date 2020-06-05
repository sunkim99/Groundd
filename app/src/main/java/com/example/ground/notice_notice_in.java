package com.example.ground;

import android.content.Intent;
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
//게시판에서 게시물을 눌렀을때 해당게시물 보여지는화면

public class notice_notice_in extends AppCompatActivity implements View.OnClickListener {

    Button btn_image, write, cancel;
    Button top_navi, btn_setting;

    TextView id_notTi, id_notCon, id_notNum, id_notDate, id_userNick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_forum_in);


        btn_image = findViewById(R.id.go_forum_image);
        //write = findViewById(R.id.) 댓글쓰기버튼
        cancel = findViewById(R.id.go_forum_forum);

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        btn_image.setOnClickListener(this);
        //write.setOnClickListener(this); 댓글쓰기 이벤트
        //cancel.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);


        Intent intent1 = getIntent();
        String notNum1 = intent1.getStringExtra("check_position1");
        Integer annNum = Integer.valueOf(notNum1);
        id_notNum = findViewById(R.id.id_notNum);
        id_notNum.setText(notNum1);
        Log.d("TEST1234", String.valueOf(annNum));


        /*String userNick = NICKNAME.getNICKNAME();


        id_userNick.setText(userNick);*/ //닉네임은 이렇게말고 쿼리문에서 가져와야하는데 작성하는거 모르겠음..
        id_userNick = findViewById(R.id.id_userNick);
        id_notTi = findViewById(R.id.id_notTi);
        id_notDate = findViewById(R.id.id_notDate);
        id_notCon = findViewById(R.id.id_notCon);

        Log.d("TEST1234", "[공지] ");
        //학교 정보 가져오기
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jasonObject = new JSONObject(response);
                    boolean success = jasonObject.getBoolean("success");
                    if (success) {
                        Log.d("TEST1234", "[공지] 쓰레드확인");

                        String number = jasonObject.getString("annNum");
                        Log.d("TEST1234", "[공지] 쓰레드확인1:");
                        Log.d("TEST1234", "[공지]  가져온 글번호 :" + number);
                        //String schName11 = schName1;
                        //Log.d("TEST1234", "학교이름 : " + schName1);


                        // SCHOOL.setSCHOOL(schName11);  // 전역변수는 schName11의 값을 가짐


                        String userID = jasonObject.getString("userID");
                        Log.d("TEST1234", "[공지] 아이디 " + userID);

                        String annTi = jasonObject.getString("annTi");
                        Log.d("TEST1234", "[공지] 제목 " + annTi);
                        String annCon = jasonObject.getString("annCon");

                        String annDate = jasonObject.getString("annDate");

                        Log.d("TEST1234", "[공지] 쓰레드확인2:");


                        id_userNick.setText(userID);
                        id_notTi.setText(annTi);
                        id_notCon.setText(annCon);
                        id_notDate.setText(annDate);


                    } else {
                        Log.d("TEST1234", "[공지] 학교공지 가져오기 실패");
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };
        notice_notice_detail_request nndr = new notice_notice_detail_request(annNum, responseListener);
        RequestQueue queue = Volley.newRequestQueue(notice_notice_in.this);
        queue.add(nndr);

    }


    @Override
    public void onClick(View v) {


    }
}