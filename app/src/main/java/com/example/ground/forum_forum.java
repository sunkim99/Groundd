package com.example.ground;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//게시판
public class forum_forum extends AppCompatActivity implements View.OnClickListener{

    Button btn_image, write, cancel;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_forum);

        btn_image = findViewById(R.id.go_forum_image);
        write = findViewById(R.id.go_forum_write);
        cancel = findViewById(R.id.btn_forum_forum_cancel);


        //리스트뷰
        list = findViewById(R.id.list);
        List<String> data = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);
        data.add("넣고싶은 데이터 값");
        data.add("여러개도상관없음");
        adapter.notifyDataSetChanged();//이걸써야 저장이됨


        btn_image.setOnClickListener(this);
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
