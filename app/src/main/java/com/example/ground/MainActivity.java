package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_school, btn_board, btn_character, btn_event, btn_game; //btn_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_school = (Button) findViewById(R.id.btn_school);
        btn_school.setOnClickListener(this);



        btn_board = findViewById(R.id.btn_board);
        btn_character = findViewById(R.id.btn_character);
        btn_event =  findViewById(R.id.btn_event);
        btn_game = findViewById(R.id.btn_game);
        //btn_setting = findViewById(R.id.btn_setting);


        btn_board.setOnClickListener(this);
        btn_character.setOnClickListener(this);
        btn_event.setOnClickListener(this);
        btn_game.setOnClickListener(this);
        // btn_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_school) {
            Intent intent1 = new Intent(MainActivity.this, school_information.class);
            startActivity(intent1);
        }


    }
}
