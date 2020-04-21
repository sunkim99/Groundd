package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class school_food_lineup extends AppCompatActivity implements View.OnClickListener{
    Button ok;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_food_lineup);

        ok = (Button) findViewById(R.id.go_school_information);
        cancel = (Button) findViewById(R.id.btn_school_food_lineup_cancel);

        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.go_school_information) {
            Intent intent01 = new Intent(school_food_lineup.this, school_information.class);
            startActivity(intent01);
        }
        if(v.getId() == R.id.btn_school_food_lineup_cancel) {
            Intent intent02 = new Intent(school_food_lineup.this, MainActivity.class);
            startActivity(intent02);
        }
    }
}
