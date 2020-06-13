package com.example.ground;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

//학교게시판에서 게시물 눌렀을때 상세보여지는화면
public class school_infomation_list extends AppCompatActivity implements View.OnClickListener {

    Button btn_image, delete, cancel;
    Button top_navi, btn_setting;
    TextView id_notTi, id_notCon, id_notDate, tv_userNick, tv_notNum;
    TextView school_name, school_add, school_tel;
    int admin_s;
    ImageView I_char_hair, I_char_face, I_char_cloth, I_char_acce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_information_in);
        final allround ADMIN = (allround) getApplicationContext(); // 관리자 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환

        allround SCHADD = (allround) getApplicationContext(); //학교 주소
        allround SCHPH = (allround) getApplicationContext(); //학교 전화번호

        admin_s = ADMIN.getADMIN();

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


        school_name = findViewById(R.id.school_name);
        school_add = findViewById(R.id.school_add);
        school_tel = findViewById(R.id.school_tel);

        String schName, schAdd,schTel;
        schName = SCHOOL.getSCHOOL();
        schAdd = SCHADD.getSCHADD();
        schTel = SCHPH.getSCHPH();

        school_name.setText(schName);
        school_add.setText(schAdd);
        school_tel.setText(schTel);

        btn_image = findViewById(R.id.go_forum_image);
        delete = findViewById(R.id.delete); //삭제하기버튼
        cancel = findViewById(R.id.btn_forum_forum_in_cancel);

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        btn_image.setOnClickListener(this);
        delete.setOnClickListener(this); //삭제하기

        cancel.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);

        id_notTi = findViewById(R.id.id_notTi);
        id_notCon = findViewById(R.id.id_notCon);
        id_notDate = findViewById(R.id.id_notDate);
        tv_userNick = findViewById(R.id.tv_userNick);

        if (admin_s == 0) { //일반사용자는 볼 수 없음
            delete.setVisibility(Button.GONE);
        }
        if (admin_s == 1 || admin_s == 2) { //관리자 1번이나 관리자 2번은 접근가능
            delete.setVisibility(Button.VISIBLE);
        }

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

        Intent intent1 = getIntent();
        String schnotNum1 = intent1.getStringExtra("itsreal");
        Integer schnotNum = Integer.valueOf(schnotNum1);
        tv_notNum = findViewById(R.id.tv_notNum);
        tv_notNum.setText(schnotNum1);
        Log.d("TEST1234", String.valueOf(schnotNum));


    /*   Intent intent = getIntent();
        HashMap<String, String> hashMap = (HashMap<String, String>) intent.getSerializableExtra("itsreal");
       Log.d("TEST1234",hashMap.toString());
*/

        //
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jasonObject = new JSONObject(response);
                    boolean success = jasonObject.getBoolean("success");
                    if (success) {
                        Log.d("TEST1234", "[SChool Forum] 쓰레드확인11111");

                        String number = jasonObject.getString("schnotNum");
                        Log.d("TEST1234", "[SChool Forum] 쓰레드확인1:");
                        Log.d("TEST1234", "[SChool Forum] 가져온 글번호 :" + number);

                        String schName = jasonObject.getString("schName");
                        Log.d("TEST1234", "[SChool Forum] 학교이름 " + schName);

                        String title = jasonObject.getString("schTi");
                        Log.d("TEST1234", "[SChool Forum]  제목 " + title);
                        String notCon = jasonObject.getString("schCon");

                        String notDate = jasonObject.getString("schDate");

                        Log.d("TEST1234", "[SChool Forum] 쓰레드확인2:");


                        tv_userNick.setText(schName);
                        id_notTi.setText(title);
                        id_notDate.setText(notDate);
                        id_notCon.setText(notCon);


                    } else {
                        Log.d("TEST1234", "[SChool Forum] 학교 정보 가져오기 실패");
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };
        school_infomation_list_request sflr = new school_infomation_list_request(schnotNum, responseListener);
        RequestQueue queue = Volley.newRequestQueue(school_infomation_list.this);
        queue.add(sflr);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() ==R.id.delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(school_infomation_list.this);
            builder.setTitle("게시글 삭제");
            builder.setMessage("게시글을 삭제하시겠습니까?");
            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent1 = getIntent();
                    String schnotNum1 = intent1.getStringExtra("itsreal");
                    Integer schnotNum = Integer.valueOf(schnotNum1);
                    tv_notNum = findViewById(R.id.tv_notNum);
                    tv_notNum.setText(schnotNum1);


                    Log.d("TEST1234", String.valueOf(schnotNum));




                    //게시글 삭제하기
                    Response.Listener<String> school_forum_delete = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jasonObject = new JSONObject(response);
                                boolean success = jasonObject.getBoolean("success");
                                if (success) {
                                    Log.d("TEST1234", "[학교_게시글삭제] 확인");
                                    String ssss = jasonObject.getString("str");
                                    Log.d("TEST1234", "[학교_게시글삭제] 수행될 쿼리문 :" + ssss);
                           /* String number = jasonObject.getString("notNum");
                            Log.d("TEST1234", "[Config2] 쓰레드확인1: 지우게될 글번호 " +notNum);
                            Log.d("TEST1234", "[Config2]  가져온 글번호 :" + number);

                            String userID = jasonObject.getString("userID");
                            Log.d("TEST1234", "[Config2] 아이디 " + userID);

                            String title = jasonObject.getString("notTi");
                            Log.d("TEST1234", "[Config2] 제목 " + title);
                            String notCon = jasonObject.getString("notCon");

                            String notDate = jasonObject.getString("notDate");
*/
                                    Log.d("TEST1234", "[학교_게시글삭제] 확인2");

                                } else {
                                    Log.d("TEST1234", "[학교_게시글삭제]  게시글 정보오류 ");
                                    return;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    };
                    Intent intent = new Intent(school_infomation_list.this, school_information.class);
                    Toast.makeText(getApplicationContext(), "해당 게시물이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    Log.d("TEST1234", "[학교_게시글삭제] 해당 게시물 삭제");
                    startActivity(intent);

                    school_forum_delete school_delete = new school_forum_delete(schnotNum, school_forum_delete);
                    RequestQueue comment_queue = Volley.newRequestQueue(school_infomation_list.this);
                    comment_queue.add(school_delete);


                }
            });
            builder.setNegativeButton("아니요", null);
            builder.setNeutralButton("목록으로 돌아가기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.create().show();
        }
        if (v.getId() == R.id.btn_forum_forum_in_cancel) {
            finish();
        }

    }
}