package com.example.ground;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

//게시판에서 글쓰기버튼 눌렀을때 게시글 작성하는 화면
public class forum_forum_write extends AppCompatActivity implements View.OnClickListener {
    Button btn_save, btn_back, go_forum_image;
    Button top_navi, btn_setting;

    private EditText text_write, title_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_forum_write);

        btn_save = findViewById(R.id.btn_save);
        btn_back = findViewById(R.id.btn_back);
        text_write = findViewById(R.id.text_wirte);
        title_name = findViewById(R.id.title_name);
        go_forum_image = findViewById(R.id.go_forum_image);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);



        btn_save.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        go_forum_image.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_save) { //게시판작성에서 완료하기를 눌렀을때
            Log.d("TEST1234", "저장하기 버튼 눌림");
            String contentsTitle = title_name.getText().toString(); //작성된 제목을 가져온다..?
            String contents = text_write.getText().toString(); //작성된 내용을 가져온다..?

            contentsTitle = contentsTitle.replace("'", "''");
            contents = contents.replace("'", "''");

            Response.Listener<String> responseListener = new Response.Listener<String>() {//volley
                @Override
                public void onResponse(String response) {
                    try {
                        Log.d("TEST1234", "쓰레드확인1:" + Thread.currentThread());
                        JSONObject jasonObject = new JSONObject(response);//Forum.php에 response
                        boolean success = jasonObject.getBoolean("success");//Forum.php에 sucess

                        //String ssss = jasonObject.getString("id");
                        String sta = jasonObject.getString("str");
                        Log.d("TEST1234", "success:" + success);
                        //Log.d("TEST1234", "정상성공?:" + ssss);
                        Log.d("TEST1234","php->안스 값:"+sta);

                        Toast.makeText(getApplicationContext(), "글이 작성되었습니다", Toast.LENGTH_SHORT).show();
                        Log.d("TEST1234", "쓰레드확인2:" + Thread.currentThread());
                        Intent intent = new Intent(forum_forum_write.this, forum_forum.class);
                        startActivity(intent);
                        Log.d("TEST1234", "쓰레드확인3:" + Thread.currentThread());





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
            forumRequest forumRequest = new forumRequest(contentsTitle,contents,responseListener);
            Log.d("TEST1234", "쓰레드확인4:" + Thread.currentThread());
            RequestQueue queue = Volley.newRequestQueue(forum_forum_write.this);
            Log.d("TEST1234", "쓰레드확인5:" + Thread.currentThread());
            queue.add(forumRequest);
            Log.d("TEST1234", "쓰레드확인6:" + Thread.currentThread());
        }

        if (v.getId() == R.id.go_forum_forum) { //포럼 버튼을 눌렀을때
            Intent intent1 = new Intent(forum_forum_write.this, forum_image.class);
            startActivity(intent1);
        }

        if (v.getId() == R.id.btn_back) { //게시판 작성화면에서 빨간버튼을 눌렀을때
            finish();
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(forum_forum_write.this, MainActivity.class);
            startActivity(intent1);
        }


        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(forum_forum_write.this, configActivity.class);
            startActivity(intent6);
        }


    }
}
