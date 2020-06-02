package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

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

    private static String TAG = "phptest_school_information";
    private static final String TAG_JSON = "webnautes";
    private static final String TAG_schnotNum = "scnotNum";
    private static final String TAG_schTi = "schTi";
    private static final String TAG_schName = "schName";

    ListView list;
    ArrayList<HashMap<String, String>> mArrrayList;
    String mJsonString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_information);
        allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        allround ADMIN = (allround) getApplicationContext(); //관리자번호
        allround SCHADD = (allround) getApplicationContext(); //학교 주소
        allround SCHPH = (allround) getApplicationContext(); //학교 전화번호

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


        //리스트뷰 정의
        list = (ListView) findViewById(R.id.list);
        mArrrayList = new ArrayList<>();

        school_information.GetData task = new school_information.GetData();
        task.execute("http://olivia7626.dothome.co.kr/SchoolForumlist.php");

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), forum_forum_in.class); // 넘어가게될 화면 만들기
                startActivity(intent);
            }
        });


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


                        SCHOOL.setSCHADD(schAdd1); //가져온 학교 주소 전역변수로 설정하기
                        SCHOOL.setSCHPH(schPh1); //가져온 학교 연락처 전역변수로 설정하기


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
        } else if (admin_s == 1) { //학교관리자일때 버튼 보이기
            school_write.setVisibility(Button.VISIBLE);
            // school_write.callOnClick();
        } else if (admin_s == 2) { //어플관리자
            school_write.setVisibility(Button.VISIBLE);
        }
    }


    //서버 연결해서 데이터가져오기
    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;



        //String schName11 = schName.getText().toString();



        @Override // 백그라운드 실행 전 변수 초기화 및 통신셋팅
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(school_information.this, "Please wait", null, true, true);
        }

        @Override // 백그라운드에 저장된 데이터 뿌려주기
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mJsonString = result;
            showResult();
        }

        @Override // 서벼연결해서 데이터 저장
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response cod -" + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                Log.d(TAG, "InsertData : Error", e);
                errorString = e.toString();
            }
            return null;
        }
    }

    ///
    private void showResult() {///////////////////////이부분이랑 해당하는 php 보고 수정해야함
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);

              //  String schName11 = schName.getText().toString();
                String schTi = item.getString(TAG_schTi);
                String schnotNum = item.getString(TAG_schnotNum);
                String schName= item.getString(TAG_schName);

                HashMap<String, String> hashMap = new HashMap<>();

              //  hashMap.put("schName11", schName11);
                hashMap.put(TAG_schnotNum, schnotNum);
                hashMap.put(TAG_schTi, schTi);
                hashMap.put(TAG_schName, schName);

                mArrrayList.add(hashMap);
            }
            ListAdapter adapter = new SimpleAdapter(
                    school_information.this, mArrrayList, R.layout.item_list,
                    new String[]{ TAG_schnotNum, TAG_schTi, TAG_schName}, ///////////////////////////
                    new int[]{R.id.textView_list_notNum, R.id.textView_list_notTi, R.id.textView_list_notDate}
            );
            list.setAdapter(adapter);
        } catch (JSONException e) {
            Log.d(TAG, "showResult :", e);
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
