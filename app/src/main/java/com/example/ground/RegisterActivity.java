package com.example.ground;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//회원가입
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_id, et_pass, et_name, et_nickname, et_phone, et_parphone, et_passck, et_school;
    private Button btn_register, validateButton;
    private AlertDialog dialog;
    private boolean validate = false;
    private Button check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //아이디 값 찾아주기
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);
        et_nickname = findViewById(R.id.et_nickname);
        et_phone = findViewById(R.id.et_phone);
        et_parphone = findViewById(R.id.et_parentphone);
        et_passck = findViewById(R.id.et_passck);
        et_school = findViewById(R.id.et_school);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);

        validateButton = findViewById(R.id.validateButton);
        validateButton.setOnClickListener(this);
     /*
        validateButton.setOnClickListener(new View.OnClickListener() {//id중복체크
            @Override
            public void onClick(View view)
        });
*/

        //btn_register=findViewById(R.id.btn_register);
        /*btn_register.setOnClickListener(new View.OnClickListener() {
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
*/
        check = findViewById(R.id.check);
        check.setOnClickListener(this);

        ////////////check 이미지뷰로 바꿔야하는데ㅠㅠㅠㅠㅠㅠㅠ/////////////////////////


        Log.d("TEST1234", "[회원가입] 화면시작됨");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.validateButton) {

            String userID = et_id.getText().toString();
            if (validate) {
                return;
            }
            if (userID.equals("")) {
                //아이디 빈칸인 상태에서 중복 확인 버튼 눌렀을때의 동작
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("아이디 오류");
                builder.setMessage("아이디는 빈 칸일 수 없습니다.");
                builder.setPositiveButton("확인", null);
                builder.create().show();
                Log.d("TEST1234", "[회원가입] 아이디빈칸!!");
                return;


            }
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try { //아이디 중복 여부 확인하기
                        String userID = et_id.getText().toString();
                        Log.d("TEST1234", "[회원가입] 아이디 확인하기 1 ");
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if (success) {
                            check.setVisibility(Button.VISIBLE); //중복체크했다는 버튼 생성
                            check.setEnabled(false); //버튼 사용불가

                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setTitle("아이디 확인");
                            builder.setMessage("이 아이디는 사용가능합니다.");
                            builder.setPositiveButton("확인", null);
                            builder.create().show();
                            Log.d("TEST1234", "[회원가입] 아이디 사용가능!!");


                        } else {
                            check.setVisibility(Button.INVISIBLE);
                            check.setEnabled(false);

                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setTitle("아이디 중복");
                            builder.setMessage("다른 아이디를 입력해주세요.");
                            builder.setPositiveButton("확인", null);
                            builder.create().show();
                            Log.d("TEST1234", "[회원가입] 아이디 중복!!");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            ValidateRequest validateRequest = new ValidateRequest(userID, responseListener);
            RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
            queue.add(validateRequest);

        }

        if (v.getId() == R.id.btn_register) {
            //회원 가입 버튼 눌렀을 때의 동작
            Log.d("TEST1234", "[회원가입] 버튼 클릭");


            //editText에 입력되어있는 값을 get(가져온다)해온다
            String userID = et_id.getText().toString();
            final String userPass = et_pass.getText().toString();
            String userName = et_name.getText().toString();
            String userNick = et_nickname.getText().toString();
            Integer userPh = Integer.parseInt(et_phone.getText().toString());
            Integer userParPh = Integer.parseInt(et_parphone.getText().toString());
            final String PassCk = et_passck.getText().toString();
            String schName = et_school.getText().toString();


            Log.d("TEST1234", "userID:" + userID);

            //로그인 버튼을 눌렀는데, 입력사항들 중 빈칸이있을때의 동작들

            if (userID.equals("")) {
                /*아이디가 빈칸일 경우*/
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("아이디 오류");
                builder.setMessage("아이디는 빈 칸일 수 없습니다.");
                builder.setPositiveButton("확인", null);
                builder.create().show();
                Log.d("TEST1234", "[회원가입] 아이디 빈칸!!");
                return;
            } else if (userPass.equals("")) {
                /*비밀번호가 빈칸일 경우*/
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("비밀번호 오류");
                builder.setMessage("비밀번호는 빈 칸일 수 없습니다.");
                builder.setPositiveButton("확인", null);
                builder.create().show();
                Log.d("TEST1234", "[회원가입] 비밀번호 빈칸!!");
                return;
            } else if (PassCk.equals("")) {
                /*비밀번호 두번체크하는게 빈칸일 경우*/
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("비밀번호 오류");
                builder.setMessage("비밀번호를 한번 더 입력해주세요.");
                builder.setPositiveButton("확인", null);
                builder.create().show();
                Log.d("TEST1234", "[회원가입] 비밀번호 확인 빈칸!!");
                return;
            } else if (userName.equals("")) {
                /*이름이 빈칸일 경우*/
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("사용자 이름 오류");
                builder.setMessage("이름을 입력해주세요.");
                builder.setPositiveButton("확인", null);
                builder.create().show();
                Log.d("TEST1234", "[회원가입] 이름 빈칸!!");
                return;
            } else if (userNick.equals("")) {
                /*닉네임이 빈칸일 경우*/
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("닉네임 오류");
                builder.setMessage("닉네임은 빈 칸일 수 없습니다.");
                builder.setPositiveButton("확인", null);
                builder.create().show();
                Log.d("TEST1234", "[회원가입] 닉네임 빈칸!!");
                return;
            } else if (userPh.equals("")) {
                /*연락처가 빈칸일 경우*/
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("연락처 오류");
                builder.setMessage("연락처는 빈 칸일 수 없습니다.");
                builder.setPositiveButton("확인", null);
                builder.create().show();
                Log.d("TEST1234", "[회원가입] 연락처 빈칸!!");
                return;
            } else if (userParPh.equals("")) {
                /*부모연락처가 빈칸일 경우*/
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("연락처 오류");
                builder.setMessage("부모 연락처는 빈 칸일 수 없습니다.");
                builder.setPositiveButton("확인", null);
                builder.create().show();
                Log.d("TEST1234", "[회원가입] 부모 연락처 빈칸!!");
                return;
            } else if (schName.equals("")) {
                /*학교이름이 빈칸일 경우*/
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("학교 이름 오류");
                builder.setMessage("학교 이름은 빈 칸일 수 없습니다.");
                builder.setPositiveButton("확인", null);
                builder.create().show();
                Log.d("TEST1234", "[회원가입] 학교 이름 빈칸!!");
                return;
            }


            //////////////////////////////////////////////////////////////////////////////////////

            Response.Listener<String> registerListener = new Response.Listener<String>() {//volley
                @Override
                public void onResponse(String response) {
                    try {
                        Log.d("TEST1234", "[회원가입] 가입 확인 1" + Thread.currentThread());
                        JSONObject jasonObject = new JSONObject(response);//Register2 php에 response
                        boolean success = jasonObject.getBoolean("success");//Register2 php에 success


                        String ssss = jasonObject.getString("id");
                        String sta = jasonObject.getString("str");
                        Log.d("TEST1234", "[회원가입] 1 " + success);
                        Log.d("TEST1234", "[회원가입] 2 " + ssss);
                        Log.d("TEST1234", "[회원가입] 3 " + sta);

                        //중복체크됐다는 이미지뷰가 보일때 회원가입 성공하게해야하는데..


                        if (userPass.equals(PassCk)) {
                            Log.d("TEST1234", "[회원가입] 가입 확인 2 " + Thread.currentThread());
                            if (success) { //회원등록 성공한 경우
                                Log.d("TEST1234", "[회원가입] 회원 등록 성공" + Thread.currentThread());
                                Toast.makeText(getApplicationContext(), "회원 가입 성공!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Log.d("TEST1234", "[회원가입] 가입 확인 4" + Thread.currentThread());
                            }
                        } else {//회원등록 실패한 경우
                            Toast.makeText(getApplicationContext(), "회원 가입 실패..", Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setTitle("비밀번호 오류");
                            builder.setMessage("입력한 비밀번호가 다릅니다.");
                            builder.setPositiveButton("확인", null);
                            builder.create().show();
                            Log.d("TEST1234", "[회원가입] 비밀번호가 다릅니다!!");
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            //서버로 volley를 이용해서 요청을 함
            RegisterRequest registerRequest = new RegisterRequest(userID, userPass, userName, userNick, userPh, userParPh, schName, registerListener);
            Log.d("TEST1234", "[회원가입] 가입 확인 5" + Thread.currentThread());
            RequestQueue queue11 = Volley.newRequestQueue(RegisterActivity.this);
            Log.d("TEST1234", "[회원가입] 가입 확인 6" + Thread.currentThread());
            queue11.add(registerRequest);
            Log.d("TEST1234", "[회원가입] 가입 확인 7" + Thread.currentThread());
        }

    }
}