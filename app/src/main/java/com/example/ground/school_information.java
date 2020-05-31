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

//학교메인
public class school_information extends AppCompatActivity implements View.OnClickListener {

    Button ok;
    Button cancel;
    TextView schName, schAdd, schPh;
    Button top_navi, btn_setting, school_write;

    Intent data_receive; //데이터 받기

    String schName1 = "";
    String schAdd1 = "";
    String schPh1 = "";

    int admin_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_information);
        allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        allround ADMIN = (allround) getApplicationContext();


        ok = (Button) findViewById(R.id.go_school_food_lineup);
        cancel = (Button) findViewById(R.id.btn_school_information_cancel);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);
        schName = findViewById(R.id.school_name);
        schAdd = findViewById(R.id.school_add);
        schPh = findViewById(R.id.school_tel);
        school_write = findViewById(R.id.go_school_infomation_write); //관리자일때버튼


        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
        school_write.setOnClickListener(this);


        data_receive = getIntent();
        final String userID1 = data_receive.getStringExtra("userID"); //유저 아이디 값 받아오기
        ID.setID(userID1); // 전역변수는 userID1의 값을 가짐
        Log.d("TEST1234", "[School Info]받아온 userID " + userID1);

        String userID = userID1;


        //학교 정보 가져오기
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jasonObject = new JSONObject(response);
                    boolean success = jasonObject.getBoolean("success");
                    if (success) {
                        Log.d("TEST1234", "[School Info] 쓰레드확인");

                        schName1 = jasonObject.getString("schName");
                        Log.d("TEST1234", "[School Info] 쓰레드확인1:");
                        Log.d("TEST1234", "[School Info] 학교이름 :" + schName1);
                        //String schName11 = schName1;
                        //Log.d("TEST1234", "학교이름 : " + schName1);


                        // SCHOOL.setSCHOOL(schName11);  // 전역변수는 schName11의 값을 가짐


                        schAdd1 = jasonObject.getString("schAdd");
                        Log.d("TEST1234", "[School Info] php->안스 값:" + schAdd1);

                        schPh1 = jasonObject.getString("schPh");
                        Log.d("TEST1234", "[School Info] php->안스 값:" + schPh1);

                        Log.d("TEST1234", "[School Info] 쓰레드확인2:");


                        schName.setText(schName1);
                        schAdd.setText(schAdd1);
                        schPh.setText(schPh1);

                    } else {
                        Log.d("TEST1234", "[School Info] 학교 정보 가져오기 실패");
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };
        school_information_request sir = new school_information_request(userID,/*schName,schAdd,schPh, */responseListener);
        RequestQueue queue = Volley.newRequestQueue(school_information.this);
        queue.add(sir);

        admin_s = ADMIN.getADMIN();
        if (admin_s == 0) { //일반사용자
            school_write.setVisibility(Button.GONE);
        }
        else if(admin_s == 1){ //학교관리자일때 버튼 보이기
            school_write.setVisibility(Button.VISIBLE);
           // school_write.callOnClick();
        }
        else if(admin_s == 2){ //어플관리자
            school_write.setVisibility(Button.VISIBLE);
        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.go_school_food_lineup) {
            Intent intent01 = new Intent(school_information.this, school_food_lineup.class);
            startActivity(intent01);
        }
        if (v.getId() == R.id.btn_school_information_cancel) {
            finish();
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent02 = new Intent(school_information.this, MainActivity.class);
            startActivity(intent02);
        }


        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent03 = new Intent(school_information.this, configActivity.class);
            startActivity(intent03);
        }
        if (v.getId() == R.id.go_school_infomation_write) {
            Intent intent04 = new Intent(school_information.this, school_infomation_write.class);
            startActivity(intent04);

        }
    }
}
