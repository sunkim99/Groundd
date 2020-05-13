package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
//설정화면
public class configActivity extends AppCompatActivity {
    TextView show_id; //아이디 가져오기

    Intent data_receive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);




        data_receive = getIntent();
        String temp01 = data_receive.getStringExtra("userID");

        show_id =findViewById(R.id.show_id);
        show_id.setText(temp01);


    }
}
