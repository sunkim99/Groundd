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

//게시판에서 글쓰기버튼 눌렀을때 게시글 작성하는 화면
public class forum_forum_write extends AppCompatActivity implements View.OnClickListener {
    Button btn_save, btn_back;
    private EditText text_write, title_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_forum_write);

        btn_save = findViewById(R.id.btn_save);
        btn_back = findViewById(R.id.btn_back);
        text_write = findViewById(R.id.text_wirte);
        title_name = findViewById(R.id.title_name);

        btn_save.setOnClickListener(this);
        btn_back.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Log.d("TESTforumwirte", "글쓰기 버튼 눌림");


        if (v.getId() == R.id.btn_save) { //게시판작성에서 완료하기를 눌렀을때
            String contents = text_write.getText().toString(); //작성된내용을 가져온다..?
            contents = contents.replace("'", "''");
            Response.Listener<String> responseListener = new Response.Listener<String>() {//volley
                @Override
                public void onResponse(String response) {
                    try {
                        Log.d("TEST1234", "쓰레드확인1:" + Thread.currentThread());
                        JSONObject jasonObject = new JSONObject(response);//Register2 php에 response
                        boolean success = jasonObject.getBoolean("success");//Register2 php에 sucess


                        Toast.makeText(getApplicationContext(), "글이 작성되었습니다", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(forum_forum_write.this, forum_forum.class);
                        startActivity(intent);

                       /* String ssss = jasonObject.getString("id");
                        String sta = jasonObject.getString("str");
                        Log.d("TEST1234", "success:" + success);
                        Log.d("TEST1234", "정상성공?:" + ssss);

                        Log.d("TEST1234", "php->안스 값:" + sta);
                        if (userPass.equals(PassCk)) {
                            Log.d("TEST1234", "쓰레드확인2:" + Thread.currentThread());
                            if (success) { //회원등록 성공한 경우
                                Log.d("TEST1234", "쓰레드확인3:" + Thread.currentThread());
                                Toast.makeText(getApplicationContext(), "회원 가입 성공!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Log.d("TEST1234", "쓰레드확인4:" + Thread.currentThread());
                            }
                        } else {//회원등록 실패한 경우
                            Toast.makeText(getApplicationContext(), "회원 가입 실패..", Toast.LENGTH_SHORT).show();
                            return;
                        }*/
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            forumRequest forumRequest = new forumRequest(contents,responseListener);
            RequestQueue queue = Volley.newRequestQueue(forum_forum_write.this);

            queue.add(forumRequest);
        }
        if (v.getId() == R.id.btn_back) { //게시판 작성화면에서 빨간버튼을 눌렀을때
            finish();
        }


    }
}
