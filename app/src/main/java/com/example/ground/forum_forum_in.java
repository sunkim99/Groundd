package com.example.ground;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class forum_forum_in extends AppCompatActivity implements View.OnClickListener {

    Button btn_image, write, cancel;
    Button top_navi, btn_setting;

    TextView id_notTi, id_notCon, id_notDate;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_forum_in);
/*
        btn_image = findViewById(R.id.go_forum_image);
        //write = findViewById(R.id.) 댓글쓰기버튼
        cancel = findViewById(R.id.go_forum_forum);

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        btn_image.setOnClickListener(this);
        //write.setOnClickListener(this); 댓글쓰기 이벤트
        cancel.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
*/
       // Intent intent = getIntent();
       // HashMap<String, String> hashMap = (HashMap<String, String>) intent.getSerializableExtra("itsreal");
       // Log.d("TEST1234",hashMap.toString());

    }

    @Override
    public void onClick(View v) {

    }
}