package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
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
//로그인 (앱 기동시 첫 화면)
public class LoginActivity extends AppCompatActivity {
    private EditText et_id, et_pass;
    private Button btn_login, btn_register, btn_gomain;
    private CheckBox auto_login; // 자동로그인


    SharedPreferences sendId; //아이디값 전달하기
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        final allround ADMIN = (allround) getApplicationContext(); // 관리자 소환
        final allround SCHADD = (allround) getApplicationContext(); //학교 주소
        final allround SCHPH = (allround) getApplicationContext(); //학교 전화번호
        final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환
        final allround USERNUM = (allround) getApplicationContext(); // 전역변수 USERNUM
        final allround USERNAME = (allround) getApplicationContext(); // 전역변수 USERNUM

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_gomain = findViewById(R.id.btn_gomain);
        auto_login = findViewById(R.id.checkBox);

        et_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        et_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        //위에 두줄 -> 로그인할때 비밀번호 안보이게 동그라미로 표시

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

                                String schName = jasonObject.getString("schName");
                                Log.d("TEST1234", "학교이름 : " + schName);

                                String userNick = jasonObject.getString("userNick"); //사용자 닉네임 가져오기
                                Log.d("TEST1234", "닉네임 : " + userNick);

                                int userAdmin= jasonObject.getInt("userAdmin");
                                Log.d("TEST1234", "관리자 여부 : " + userAdmin);

                                String userName = jasonObject.getString("userName");
                                Log.d("TEST1234", "사용자 이름 : " + userName);

                                int userNum = jasonObject.getInt("userNum");
                                Log.d("TEST1234", "유저번호 : " + userNum);


                                Log.d("TEST1234", "쓰레드확인1 " + Thread.currentThread());

                                if(userAdmin == 0) {
                                    Toast.makeText(getApplicationContext(), "로그인 성공!!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "관리자입니다.", Toast.LENGTH_SHORT).show();
                                }
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                Log.d("TEST1234", "로그인 성공" );
                               /* intent.putExtra("log", "User"); //?????? 추후에 확인작업 거친후 삭제 조치예정
                                intent.putExtra("userID", userID);*/


                                ID.setID(userID);// 로그인시 전역변수 바로 설정
                                ADMIN.setADMIN(userAdmin);
                                SCHOOL.setSCHOOL(schName);
                                NICKNAME.setNICKNAME(userNick);
                                USERNUM.setUSERNUM(userNum);
                                USERNAME.setUSERNAME(userName);

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

                    Log.d("TEST1234", "로그인 아이디 : " + userID);
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