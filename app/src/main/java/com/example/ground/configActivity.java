package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
//설정화면
public class configActivity extends AppCompatActivity {
    TextView show_id; //아이디 가져오기



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        show_id =findViewById(R.id.show_id);





    }
}
