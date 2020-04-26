package com.example.ground;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_id, et_pass, et_name, et_nickname,et_phone,et_parphone,et_passck;
    private Button btn_register,validateButton;
    private AlertDialog dialog;
    private boolean validate=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //아이디 값 찾아주기
        et_id=findViewById(R.id.et_id);
        et_pass=findViewById(R.id.et_pass);
        et_name=findViewById(R.id.et_name);
        et_nickname=findViewById(R.id.et_nickname);
        et_phone=findViewById(R.id.et_phone);
        et_parphone=findViewById(R.id.et_parentphone);
        et_passck=findViewById(R.id.et_passck);


        Log.d("TEST1234","userID:화면시작됨");
        validateButton=findViewById(R.id.validateButton);
        validateButton.setOnClickListener(new View.OnClickListener() {//id중복체크
            @Override
            public void onClick(View view) {

                String userID=et_id.getText().toString();
                if(validate)
                {
                    return;
                }
                if(userID.equals("")){
                    AlertDialog.Builder builder=new AlertDialog.Builder( RegisterActivity.this );
                    dialog=builder.setMessage("아이디는 빈 칸일 수 없습니다")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder=new AlertDialog.Builder( RegisterActivity.this );
                                dialog=builder.setMessage("사용할 수 있는 아이디입니다.")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();
                                et_id.setEnabled(false);
                                validate=true;
                                validateButton.setText("확인");
                            }
                            else{
                                AlertDialog.Builder builder=new AlertDialog.Builder( RegisterActivity.this );
                                dialog=builder.setMessage("사용할 수 없는 아이디입니다.")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest=new ValidateRequest(userID,responseListener);
                RequestQueue queue= Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);

            }
        });


        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstAction();
            }
        });
    }
    private void firstAction(){
        (new AsyncTask<RegisterActivity, Void, RegisterActivity>(){
            @Override
            protected RegisterActivity doInBackground(RegisterActivity... params) {
                return params[0];
            }

            @Override
            protected void onPostExecute(RegisterActivity result) {
                //super.onPostExecute(result);
                result.click();
            }

        }).execute(this);
    }

    void click(){
        Log.d("TEST1234","userID:버튼클릭됨");
        //editText에 입력되어있는 값을 get(가져온다)해온다
        String userID = et_id.getText().toString();
        final String userPass = et_pass.getText().toString();
        String userName = et_name.getText().toString();
        String userNick = et_nickname.getText().toString();
        int userPh = Integer.parseInt(et_phone.getText().toString());
        int userParPh = Integer.parseInt(et_parphone.getText().toString());
        final String PassCk = et_passck.getText().toString();

        Log.d("TEST1234","userID:"+userID);


        Response.Listener<String> responseListener=new Response.Listener<String>() {//volley
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("TEST1234","쓰레드확인1:"+Thread.currentThread());
                    JSONObject jasonObject=new JSONObject(response);//Register2 php에 response
                    boolean success =jasonObject.getBoolean("success");//Register2 php에 sucess
                    String ssss = jasonObject.getString("id");
                    String sta = jasonObject.getString("str");
                    Log.d("TEST1234","success:"+success);
                    Log.d("TEST1234","정상성공?:"+ssss);
                    Log.d("TEST1234","php->안스 값:"+sta);
                    if(userPass.equals(PassCk)) {
                        Log.d("TEST1234","쓰레드확인2:"+Thread.currentThread());
                        if (success) { //회원등록 성공한 경우
                            Log.d("TEST1234","쓰레드확인3:"+Thread.currentThread());
                            Toast.makeText(getApplicationContext(), "회원 등록 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Log.d("TEST1234","쓰레드확인4:"+Thread.currentThread());
                        }
                    }
                    else{//회원등록 실패한 경우
                        Toast.makeText(getApplicationContext(),"회원 등록 실패",Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        //서버로 volley를 이용해서 요청을 함
        RegisterRequest registerRequest=new RegisterRequest(userID,userPass, userName, userNick,userPh,userParPh,responseListener);
        Log.d("TEST1234","쓰레드확인5:"+Thread.currentThread());
        RequestQueue queue= Volley.newRequestQueue(RegisterActivity.this);
        Log.d("TEST1234","쓰레드확인6:"+Thread.currentThread());
        queue.add(registerRequest);
        Log.d("TEST1234","쓰레드확인7:"+Thread.currentThread());
    }

}