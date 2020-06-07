package com.example.ground;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

//학교게시판에서 게시물 눌렀을때 상세보여지는화면
public class school_forum_list extends AppCompatActivity implements View.OnClickListener {

    Button btn_image, delete, cancel;
    Button top_navi, btn_setting;

    TextView id_notTi, id_notCon, id_notDate,tv_userNick,tv_notNum;

    int admin_s;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_forum_list);
        final allround ADMIN = (allround) getApplicationContext(); // 관리자 소환
        admin_s = ADMIN.getADMIN();

        btn_image = findViewById(R.id.go_forum_image);
        delete = findViewById(R.id.delete); //삭제하기버튼
        cancel = findViewById(R.id.go_forum_forum);

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        btn_image.setOnClickListener(this);
        delete.setOnClickListener(this); //삭제하기

        //cancel.setOnClickListener(this); 0606 - 이거 왜 오류나지 R.id.~~~ 이거 오류였음 필요하면 해당 xml보고 이름다시설정하기
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);

        id_notTi = findViewById(R.id.id_notTi);
        id_notCon = findViewById(R.id.id_notCon);
        id_notDate =findViewById(R.id.id_notDate);
        tv_userNick = findViewById(R.id.tv_userNick);

        if (admin_s == 0) { //일반사용자는 볼 수 없음
            delete.setVisibility(Button.GONE);
        }
        if (admin_s == 1 || admin_s == 2) { //관리자 1번이나 관리자 2번은 접근가능
            delete.setVisibility(Button.VISIBLE);
        }



        Intent intent1 = getIntent();
        String schnotNum1 = intent1.getStringExtra("itsreal");
        Integer schnotNum= Integer.valueOf(schnotNum1);
        tv_notNum = findViewById(R.id.tv_notNum);
        tv_notNum.setText(schnotNum1);
        Log.d("TEST1234", String.valueOf(schnotNum));


    /*   Intent intent = getIntent();
        HashMap<String, String> hashMap = (HashMap<String, String>) intent.getSerializableExtra("itsreal");
       Log.d("TEST1234",hashMap.toString());
*/

        //학교 정보 가져오기
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
                        Log.d("TEST1234", "[SChool Forum]학교이름 " + schName);

                        String title = jasonObject.getString("schTi");
                        Log.d("TEST1234", "[[SChool Forum]  제목 " + title);
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
        school_forum_list_request sflr = new school_forum_list_request(schnotNum, responseListener);
        RequestQueue queue = Volley.newRequestQueue(school_forum_list.this);
        queue.add(sflr);
    }

    @Override
    public void onClick(View v) {

    }
}