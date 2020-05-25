package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class notice_notice extends AppCompatActivity implements View.OnClickListener{

    Button go_notice_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_notice);

        go_notice_event= findViewById(R.id.go_notice_event);


        go_notice_event.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.go_notice_event) {
            Intent intent01 = new Intent(notice_notice.this, notice_event.class);
            startActivity(intent01);
        }
    }
}
