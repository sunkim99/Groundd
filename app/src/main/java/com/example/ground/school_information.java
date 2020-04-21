package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class school_information extends AppCompatActivity implements View.OnClickListener{

    Button ok;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_information);

        ok = (Button) findViewById(R.id.go_school_food_lineup);
        cancel = (Button) findViewById(R.id.btn_school_information_cancel);

        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.go_school_food_lineup) {
            Intent intent01 = new Intent( school_information.this, school_food_lineup.class);
            startActivity(intent01);
        }
        if(v.getId() == R.id.btn_school_information_cancel) {
            Intent intent02 = new Intent( school_information.this, MainActivity.class);
            startActivity(intent02);
        }
    }
}
