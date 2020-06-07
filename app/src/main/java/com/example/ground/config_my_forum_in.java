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
//설정에서 내가 작성한게시글 클릭하고 목록보여진뒤 상세내역으로 넘어가는화면

public class config_my_forum_in extends AppCompatActivity implements View.OnClickListener {

    Button school_forum, delete, cancel;
    Button top_navi, btn_setting;

    TextView id_notTi, id_notCon, id_notNum, id_notDate, id_userNick;

    int admin_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_my_forum_in);
        final allround ADMIN = (allround) getApplicationContext(); // 관리자 소환
        admin_s = ADMIN.getADMIN();

        delete = findViewById(R.id.delete); //삭제하기 버튼
        cancel = findViewById(R.id.btn_forum_forum_in_cancel);

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        school_forum = findViewById(R.id.school_forum);
        school_forum.setOnClickListener(this);//학교 게시판 보여주기

        cancel.setOnClickListener(this);

        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
        delete.setOnClickListener(this); //삭제하기 버튼


        Intent intent1 = getIntent();
        String notNum1 = intent1.getStringExtra("check_position1");
        Integer notNum = Integer.valueOf(notNum1);
        id_notNum = findViewById(R.id.id_notNum);
        id_notNum.setText(notNum1);
        Log.d("TEST1234","[설정_나의 게시글] 가져오게될 글 번호 : " + String.valueOf(notNum));


        id_userNick = findViewById(R.id.id_userNick);
        id_notTi = findViewById(R.id.id_notTi);
        id_notDate = findViewById(R.id.id_notDate);
        id_notCon = findViewById(R.id.id_notCon);


        if (admin_s == 0 || admin_s == 1) { //일반사용자 or 학교 관리자는 볼수 없음
            delete.setVisibility(Button.GONE);
        }
        if (admin_s == 2) { //관리자 2번(어플관리자)라면 삭제하기 버튼 보이기
            delete.setVisibility(Button.VISIBLE);
        }


        Log.d("TEST1234", "[설정_나의 게시글] 확인1!!!");
        //학교 정보 가져오기
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jasonObject = new JSONObject(response);
                    boolean success = jasonObject.getBoolean("success");
                    if (success) {
                        Log.d("TEST1234", "[설정_나의 게시글] 확인2");

                        String number = jasonObject.getString("notNum");
                        Log.d("TEST1234", "[설정_나의 게시글]  가져온 글번호 : " + number);

                        String userID = jasonObject.getString("userID");
                        Log.d("TEST1234", "[설정_나의 게시글]  아이디 : " + userID);

                        String title = jasonObject.getString("notTi");
                        Log.d("TEST1234", "[설정_나의 게시글]  제목 : " + title);
                        String notCon = jasonObject.getString("notCon");
                        String notDate = jasonObject.getString("notDate");

                        Log.d("TEST1234", "[설정_나의 게시글] 확인3");


                        id_userNick.setText(userID);
                        id_notTi.setText(title);
                        id_notCon.setText(notCon);
                        id_notDate.setText(notDate);


                    } else {
                        Log.d("TEST1234", "[설정_나의 게시글] 게시글 정보 오류");
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };
        config_my_forum_request cfmr = new config_my_forum_request(notNum, responseListener);
        RequestQueue queue = Volley.newRequestQueue(config_my_forum_in.this);
        queue.add(cfmr);

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(config_my_forum_in.this);
            builder.setTitle("게시글 삭제");
            builder.setMessage("게시글을 삭제하시겠습니까?");
            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent1 = getIntent();
                    String notNum1 = intent1.getStringExtra("check_position1");
                    final Integer notNum = Integer.valueOf(notNum1);
                    id_notNum = findViewById(R.id.id_notNum);
                    id_notNum.setText(notNum1);

                    //게시글 삭제하기
                    Response.Listener<String> admin_delete = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jasonObject = new JSONObject(response);
                                boolean success = jasonObject.getBoolean("success");
                                if (success) {
                                    Log.d("TEST1234", "[설정_나의 게시글] 확인");
                                    String ssss = jasonObject.getString("str");
                                    Log.d("TEST1234", "[설정_나의 게시글] 수행될 쿼리문 :" + ssss);
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
                                    Log.d("TEST1234", "[설정_나의 게시글] 확인2");

                                } else {
                                    Log.d("TEST1234", "[설정_나의 게시글]  게시글 정보오류 ");
                                    return;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    };
                    Intent intent = new Intent(config_my_forum_in.this, config_my_forum_forum.class);
                    Toast.makeText(getApplicationContext(), "해당 게시물이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    Log.d("TEST1234", "[설정_나의 게시글] 해당 게시물 삭제");
                    startActivity(intent);

                    config_admin_delete admin = new config_admin_delete(notNum, admin_delete);
                    RequestQueue comment_queue = Volley.newRequestQueue(config_my_forum_in.this);
                    comment_queue.add(admin);

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
        if (v.getId() == R.id.school_forum) { //학교게시판 버튼 눌렸을때
           // Intent intent1 = new Intent(config_my_forum_in.this, config_my_school_forum_in.class);
           // startActivity(intent1);



        }
        if (v.getId() == R.id.btn_forum_forum_in_cancel) { //보여진 상세 게시물 내역에서 빨간버튼 누를때 동작
            finish();
        }

    }
}