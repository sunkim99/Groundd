package com.example.ground;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

//공지에서 글쓰기 _ 어플 관리자만 접속가능
public class notice_notice_write extends AppCompatActivity implements View.OnClickListener {
    TextView admin_id;
    EditText notice_title_name, notice_content;
    Button btn_save, btn_back, btn_forum_write_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        final allround ADMIN = (allround) getApplicationContext(); // 관리자 소환
        final allround SCHADD = (allround) getApplicationContext(); //학교 주소
        final allround SCHPH = (allround) getApplicationContext(); //학교 전화번호
        final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_notice_write);

        admin_id = findViewById(R.id.admin_id);
        notice_title_name = findViewById(R.id.notice_title_name);
        notice_content = findViewById(R.id.notice_content);
        btn_save = findViewById(R.id.btn_save);
        btn_back = findViewById(R.id.btn_back);

        btn_save.setOnClickListener(this);
        btn_back.setOnClickListener(this);

        String adminID = ID.getID();
        admin_id.setText(adminID);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) { //게시판작성에서 완료하기를 눌렀을때
            Log.d("TEST1234", "저장하기 버튼 눌림");

            String annTi = notice_title_name.getText().toString();
            String annCon = notice_content.getText().toString();

            String userID = admin_id.getText().toString();

            annTi = annTi.replace("'", "''");
            annCon = annCon.replace("'", "''");

            Response.Listener<String> responseListener = new Response.Listener<String>() {//volley
                @Override
                public void onResponse(String response) {
                    try {
                        Log.d("TEST1234", "쓰레드확인1:" + Thread.currentThread());
                        JSONObject jasonObject = new JSONObject(response);          //NoticeWrite.php에 response
                        boolean success = jasonObject.getBoolean("success"); //NoticeWrite.php에 sucess


                        String sta = jasonObject.getString("str");
                        Log.d("TEST1234", "success:" + success);
                        Log.d("TEST1234", "php->안스 값:" + sta);

                        Toast.makeText(getApplicationContext(), "공지사항이 작성되었습니다", Toast.LENGTH_LONG).show();
                        Log.d("TEST1234", "쓰레드확인2:" + Thread.currentThread());


                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };


            notice_write_request nwr = new notice_write_request(userID, annTi, annCon, responseListener);
            RequestQueue queue = Volley.newRequestQueue(notice_notice_write.this);
            queue.add(nwr);
        }
        else if (v.getId() == R.id.btn_back) { //게시판 작성화면에서 빨간버튼을 눌렀을때
            finish();
        }
    }
}
