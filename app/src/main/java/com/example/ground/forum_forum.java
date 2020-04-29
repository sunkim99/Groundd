package com.example.ground;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
//게시판
public class forum_forum extends AppCompatActivity implements View.OnClickListener{

    Button next_tab, write, cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_forum);

        next_tab = (Button) findViewById(R.id.go_forum_image);
        write = (Button) findViewById(R.id.go_forum_write);
        cancel = (Button) findViewById(R.id.btn_forum_forum_cancel);

        next_tab.setOnClickListener(this);
        write.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.go_forum_image) { //그림게시판이동
            Intent intent01 = new Intent( forum_forum.this, forum_image.class);
            startActivity(intent01);
        }
        if(v.getId() == R.id.go_forum_write) { //글쓰기 버튼 누름
            Log.d("TEST1234", "글작성 버튼 눌림");
            Intent intent02 = new Intent( forum_forum.this, forum_forum_write.class);
            startActivity(intent02);
        }
        if(v.getId() == R.id.btn_forum_forum_cancel) {
            finish();
        }
    }
}
