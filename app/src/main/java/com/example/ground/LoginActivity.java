package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText et_id, et_pass;
    private Button btn_login, btn_register, btn_gomain;
    private CheckBox auto_login; // 자동로그인


    SharedPreferences sendId; //아이디값 전달하기
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_gomain = findViewById(R.id.btn_gomain);
        auto_login = findViewById(R.id.checkBox);


        sendId = getSharedPreferences("sendId", MODE_PRIVATE);
        editor = sendId.edit();
        String temp1 = sendId.getString("et_id", ""); //아이디값을 가져와야하는데 그걸 어떻게..?
        String temp2 = sendId.getString("et_pass", "");

        et_id.setText(temp1);
        et_pass.setText(temp2);


        Log.d("TEST1234", "Ground 실행");


        btn_gomain.setOnClickListener(new View.OnClickListener() {//로그인 버튼을 클릭시 수행
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {//회원가입 버튼을 클릭시 수행
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jasonObject = new JSONObject(response);
                            boolean success = jasonObject.getBoolean("success");
                            if (success) {//로그인에 성공한 경우
                                String userID = jasonObject.getString("userID");
                                String userPass = jasonObject.getString("userPassword");
                                Log.d("TEST1234", "쓰레드확인1:" + Thread.currentThread());
                                Toast.makeText(getApplicationContext(), "로그인 성공!!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                Log.d("TEST1234", "로그인성공:" + Thread.currentThread());
                                intent.putExtra("log", "User");
                                intent.putExtra("userID", userID);

                                startActivity(intent);
                            } else {//로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(), "로그인 실패..", Toast.LENGTH_SHORT).show();
                                Log.d("TEST1234", "로그인실패 :" + Thread.currentThread());
                                return;

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                if (auto_login.isChecked()) { //512 추가 근데 이거 안스 재실행 했을때도 기존에 로그인하던 정보 남아있음..
                    //체크박스가 눌렸을때의 동작
                    editor.putString("et_id", userID);
                    editor.putString("et_pass", userPass);
                    editor.commit();

                    Log.d("TEST1234", "성공?:" + userID);
                } else {
                    return;
                }



                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }


        });
    }
}