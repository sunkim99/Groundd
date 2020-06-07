package com.example.ground;

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

import androidx.appcompat.app.AlertDialog;
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
    TextView user_nickname, show_id;
    private EditText text_write, title_name;
    ImageView MY_char;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_forum_write);
        allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        allround NICKNAME = (allround) getApplicationContext(); // 전역변수 NICKNAME 소환
        allround Char_head = (allround) getApplicationContext();
        int MY_Char_head = Char_head.getChar_head();

        btn_save = findViewById(R.id.btn_save);
        btn_back = findViewById(R.id.btn_back);
        text_write = findViewById(R.id.text_wirte);
        title_name = findViewById(R.id.title_name);
        go_forum_image = findViewById(R.id.go_forum_image);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);
        user_nickname = findViewById(R.id.user_nickname);
        show_id = findViewById(R.id.show_id);

        btn_save.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        go_forum_image.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);

        String userID = ID.getID();

        show_id.setText(userID);

        String nickName = NICKNAME.getNICKNAME();
        user_nickname.setText(nickName);

        MY_char = findViewById(R.id.MY_char);
        if (MY_Char_head == 0) {
            MY_char.setImageResource(R.drawable.ex_char1);
        } else if (MY_Char_head == 1) {
            MY_char.setImageResource(R.drawable.ex_char2);
        }

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_save) { //게시판작성에서 완료하기를 눌렀을때
            Log.d("TEST1234", "[게시판] 저장하기 버튼 눌림");

            AlertDialog.Builder builder = new AlertDialog.Builder(forum_forum_write.this);
            builder.setTitle("저장");
            builder.setMessage("게시글을 올릴까요?");
            builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String contentsTitle = title_name.getText().toString(); //작성된 제목을 가져온다..?
                    String contents = text_write.getText().toString(); //작성된 내용을 가져온다..?
                    String userID = show_id.getText().toString();


                    contentsTitle = contentsTitle.replace("'", "''");
                    contents = contents.replace("'", "''");

                    Response.Listener<String> responseListener = new Response.Listener<String>() {//volley
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.d("TEST1234", "[게시판] 확인1" + Thread.currentThread());
                                JSONObject jasonObject = new JSONObject(response);//Forum.php에 response
                                boolean success = jasonObject.getBoolean("success");//Forum.php에 sucess

                                //String ssss = jasonObject.getString("id");
                                String sta = jasonObject.getString("str");
                                Log.d("TEST1234", "[게시판] : " + success);
                                //Log.d("TEST1234", "정상성공?:" + ssss);
                                Log.d("TEST1234", "[게시판]  : " + sta);

                                Toast.makeText(getApplicationContext(), "글이 작성되었습니다", Toast.LENGTH_SHORT).show();

                                finish(); //위 intent 코드 말고 그냥 finish가 나은듯!


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    forumRequest forumRequest = new forumRequest(userID, contentsTitle, contents, responseListener);
                    Log.d("TEST1234", "[게시판] 확인4:" + Thread.currentThread());
                    RequestQueue queue = Volley.newRequestQueue(forum_forum_write.this);
                    Log.d("TEST1234", "[게시판] 확인5:" + Thread.currentThread());
                    queue.add(forumRequest);
                    Log.d("TEST1234", "[게시판] 확인6:" + Thread.currentThread());
                }
            });
            builder.setNegativeButton("아니요", null);
            builder.setNeutralButton("게시판으로 돌아가기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.create().show();


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
