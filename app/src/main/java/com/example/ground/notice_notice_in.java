package com.example.ground;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

//공지 게시판에서 게시물을 눌렀을때 해당게시물 보여지는화면

public class notice_notice_in extends AppCompatActivity implements View.OnClickListener {

    Button btn_image, delete, cancel;
    Button top_navi, btn_setting;

    TextView id_notTi, id_notCon, id_notNum, id_notDate, id_userNick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_notice_in);


        btn_image = findViewById(R.id.go_forum_image);
        delete = findViewById(R.id.delete); //삭제하기 버튼
        cancel = findViewById(R.id.cancel);//빨간버튼

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        btn_image.setOnClickListener(this);
        delete.setOnClickListener(this); //삭제하기
        cancel.setOnClickListener(this); //빨간버튼
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

        Log.d("TEST1234", "[공지]");
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
                        Log.d("TEST1234", "[공지] 쓰레드확인1 ");
                        Log.d("TEST1234", "[공지] 가져온 글번호 : " + number);


                        String userID = jasonObject.getString("userID");
                        Log.d("TEST1234", "[공지] 아이디 : " + userID);

                        String annTi = jasonObject.getString("annTi");
                        Log.d("TEST1234", "[공지] 제목 : " + annTi);
                        String annCon = jasonObject.getString("annCon");

                        String annDate = jasonObject.getString("annDate");

                        Log.d("TEST1234", "[공지] 쓰레드확인2");


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
        if (v.getId() == R.id.delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(notice_notice_in.this);
            builder.setTitle("삭제");
            builder.setMessage("삭제하시겠습니까?");
            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent1 = getIntent();
                    String notNum1 = intent1.getStringExtra("check_position1");
                    final Integer annNum = Integer.valueOf(notNum1);
                    id_notNum = findViewById(R.id.id_notNum);
                    id_notNum.setText(notNum1);

                    //공지 삭제하기
                    Response.Listener<String> admin_notice1 = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jasonObject = new JSONObject(response);
                                boolean success = jasonObject.getBoolean("success");
                                if (success) {
                                    Log.d("TEST1234", "[공지 삭제하기] 쓰레드확인");
                                    String ssss = jasonObject.getString("str");
                                    Log.d("TEST1234", "[공지 삭제하기] 수행될 쿼리문 :" + ssss);
                           /* String number = jasonObject.getString("notNum");
                            Log.d("TEST1234", "[Config2] 쓰레드확인1: 지우게될 글번호 " +notNum);
                            Log.d("TEST1234", "[Config2]  가져온 글번호 :" + number);

                            String userID = jasonObject.getString("userID");
                            Log.d("TEST1234", "[Config2] 아이디 " + userID);

                            String title = jasonObject.getString("notTi");
                            Log.d("TEST1234", "[Config2] 제목 " + title);
                            String notCon = jasonObject.getString("notCon");

                            String notDate = jasonObject.getString("notDate");
*/
                                    Log.d("TEST1234", "[공지 삭제하기] 쓰레드확인2:");

                                } else {
                                    Log.d("TEST1234", "[공지 삭제하기] 게시글 정보오류 ");
                                    return;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    };
                    Intent intent = new Intent(notice_notice_in.this, notice_notice.class); //화면 어디로 넘어가는지 확인하기
                    Toast.makeText(getApplicationContext(), "해당 게시물이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    Log.d("TEST1234", "[공지 삭제하기] 해당 게시물 삭제");
                    startActivity(intent);

                    config_admin_delete_notice admin_notice = new config_admin_delete_notice(annNum, admin_notice1);
                    RequestQueue comment_queue = Volley.newRequestQueue(notice_notice_in.this);
                    comment_queue.add(admin_notice);

                }
            });
            builder.setNegativeButton("아니요", null);
            builder.setNeutralButton("목록으로 돌아가기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.create().show();


        }

        if (v.getId() == R.id.cancel) { //보여진 상세 게시물 내역에서 빨간버튼 누를때 동작
            finish();
        }
        if (v.getId() == R.id.top_navi) { //상단바 클릭
            Intent intent1 = new Intent(notice_notice_in.this, MainActivity.class);
            startActivity(intent1);
        }

    }
}