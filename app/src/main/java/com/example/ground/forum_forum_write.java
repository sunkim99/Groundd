package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        String contents = text_write.getText().toString();
        contents = contents.replace("'","''");
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_save) { //게시판작성에서 완료하기를 눌렀을때
            Toast.makeText(getApplicationContext(), "글이 작성되었습니다", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(forum_forum_write.this, forum_forum.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.btn_back) { //게시판 작성화면에서 빨간버튼을 눌렀을때
           finish();
        }

    }
}
