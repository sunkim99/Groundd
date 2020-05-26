package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
//학교 정보 가져오기
public class school_information extends AppCompatActivity implements View.OnClickListener{

    Button ok;
    Button cancel;
    TextView schName, schAdd, schPh;
    Button top_navi, btn_setting;

    Intent data_receive; //데이터 받기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_information);

        ok = (Button) findViewById(R.id.go_school_food_lineup);
        cancel = (Button) findViewById(R.id.btn_school_information_cancel);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);
        schName= findViewById(R.id.school_name);
        schAdd = findViewById(R.id.school_add);
        schPh = findViewById(R.id.school_tel);


        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);


        data_receive = getIntent();
        String temp01 = data_receive.getStringExtra("userID"); //유저 아이디 값 받아오기
        Log.d("TEST1234", "userID " + temp01);

        String schName = "";
        String schAdd ="";
        int schPh = 0;
       //학교 정보 가져오기
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jasonObject = new JSONObject(response);
                    boolean success = jasonObject.getBoolean("success");
                    if (success) {

                        String schName = jasonObject.getString("schName");
                        String schAdd = jasonObject.getString("schAdd");
                        Integer schPh = jasonObject.getInt("schPh");
                        Log.d("TEST1234", "쓰레드확인1:");


                       // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                       // Log.d("TEST1234", "로그인성공:" + Thread.currentThread());
                       // intent.putExtra("log", "User");
                       // intent.putExtra("userID", userID);

                       // startActivity(intent);
                    } else {
                       // Toast.makeText(getApplicationContext(), "학교 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                        Log.d("TEST1234", "학교 정보 가져오기 실패");
                        return;

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };




        school_information_request sir = new school_information_request(schName,schAdd,schPh, responseListener);
        RequestQueue queue = Volley.newRequestQueue(school_information.this);
        queue.add(sir);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.go_school_food_lineup) {
            Intent intent01 = new Intent( school_information.this, school_food_lineup.class);
            startActivity(intent01);
        }
        if(v.getId() == R.id.btn_school_information_cancel) {
            finish();
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(school_information.this, MainActivity.class);
            startActivity(intent1);
        }


        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(school_information.this, configActivity.class);
            startActivity(intent6);
        }
    }
}
