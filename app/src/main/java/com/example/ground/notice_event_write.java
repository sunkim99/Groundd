package com.example.ground;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

//공지-이벤트!!에서 글쓰기 _ 어플 관리자만 접속가능
public class notice_event_write extends AppCompatActivity implements View.OnClickListener {
    TextView admin_id,show_id;
    EditText notice_title_name, notice_content;
    Button btn_save, btn_back, btn_forum_write_add;
    ImageView I_char_hair, I_char_face, I_char_cloth, I_char_acce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_event_write);

        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        final allround ADMIN = (allround) getApplicationContext(); // 관리자 소환
        final allround SCHADD = (allround) getApplicationContext(); //학교 주소
        final allround SCHPH = (allround) getApplicationContext(); //학교 전화번호
        final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환

        allround Char_hair = (allround) getApplicationContext();
        allround Char_face = (allround) getApplicationContext();
        allround Char_cloth = (allround) getApplicationContext();
        allround Char_acce = (allround) getApplicationContext();

        int MY_Char_hair = Char_hair.getChar_hair();
        int MY_Char_face = Char_face.getChar_face();
        int MY_Char_cloth = Char_cloth.getChar_cloth();
        int MY_Char_acce = Char_acce.getChar_acce();

        I_char_hair = findViewById(R.id.MY_char_hair);
        I_char_face = findViewById(R.id.MY_char_face);
        I_char_cloth = findViewById(R.id.MY_char_cloth);
        I_char_acce = findViewById(R.id.MY_char_acce);


        admin_id = findViewById(R.id.admin_id);
        notice_title_name = findViewById(R.id.notice_title_name);
        notice_content = findViewById(R.id.notice_content);
        btn_save = findViewById(R.id.btn_save);
        btn_back = findViewById(R.id.btn_back);
        show_id = findViewById(R.id.show_id);


        btn_save.setOnClickListener(this);
        btn_back.setOnClickListener(this);


        String userID = ID.getID();
        String nickName = NICKNAME.getNICKNAME();
        show_id.setText(userID);
        admin_id.setText(nickName);

        if (MY_Char_acce == 0) { // 악세
            I_char_acce.setImageResource(R.drawable.char_blind);
        } else if (MY_Char_acce == 1) {
            I_char_acce.setImageResource(R.drawable.char_acce_gom);
        } else if (MY_Char_acce == 2) {
            I_char_acce.setImageResource(R.drawable.char_acce_cat_ear);
        }

        if (MY_Char_face == 0) { // 얼굴
            I_char_face.setImageResource(R.drawable.face_default);
        } else if (MY_Char_face == 1) {
            I_char_face.setImageResource(R.drawable.face_default_black);
        }
        if (MY_Char_cloth == 0) { // 옷
            I_char_cloth.setImageResource(R.drawable.cloth_default);
        } else if (MY_Char_cloth == 1) {
            I_char_cloth.setImageResource(R.drawable.char_cloth_gom);
        } else if (MY_Char_cloth == 2) {
            I_char_cloth.setImageResource(R.drawable.char_cloth_daram);
        }


        if (MY_Char_hair == 0) { // 머리
            I_char_hair.setImageResource(R.drawable.hair_default);
        } else if (MY_Char_hair == 1) {
            I_char_hair.setImageResource(R.drawable.char_blind);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) { //게시판작성에서 완료하기를 눌렀을때
            Log.d("TEST1234", "[공지_이벤트] 저장하기 버튼 눌림");
            AlertDialog.Builder builder = new AlertDialog.Builder(notice_event_write.this);
            builder.setTitle("이벤트 저장");
            builder.setMessage("이베트를 저장할까요?");
            builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String annTi = notice_title_name.getText().toString();
                    String annCon = notice_content.getText().toString();
                   // String userID = admin_id.getText().toString();
                    String userID = show_id.getText().toString();
                    annTi = annTi.replace("'", "''");
                    annCon = annCon.replace("'", "''");

                    Response.Listener<String> responseListener = new Response.Listener<String>() {//volley
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.d("TEST1234", "[공지_이벤트] 쓰레드확인1" + Thread.currentThread());
                                JSONObject jasonObject = new JSONObject(response);          //NoticeWrite.php에 response
                                boolean success = jasonObject.getBoolean("success"); //NoticeWrite.php에 sucess

                                String sta = jasonObject.getString("str");
                                Log.d("TEST1234", "[공지_이벤트] success : " + success);
                                Log.d("TEST1234", "[공지_이벤트] php : " + sta);

                                Toast.makeText(getApplicationContext(), "이벤트가 작성되었습니다", Toast.LENGTH_LONG).show();
                                Log.d("TEST1234", "[공지_이벤트] 쓰레드확인2" + Thread.currentThread());

                                finish();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };


                    notice_event_write_request newr = new notice_event_write_request(userID, annTi, annCon, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(notice_event_write.this);
                    queue.add(newr);
                }
            });
            builder.setNegativeButton("아니요", null);
          /*  builder.setNeutralButton("게시판으로 돌아가기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });*/
            builder.create().show();

        } else if (v.getId() == R.id.btn_back) { //게시판 작성화면에서 빨간버튼을 눌렀을때
            finish();
        }
    }
}
