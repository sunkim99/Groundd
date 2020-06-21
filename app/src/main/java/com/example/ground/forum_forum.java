package com.example.ground;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

//게시판
public class forum_forum extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static String TAG = "phptest_forum_forum";
    private static final String TAG_JSON = "webnautes";
    private static final String TAG_notNum = "notNum";
    private static final String TAG_notTi = "notTi";
    private static final String TAG_userID = "userID";


    Button btn_image, write, cancel;
    Button top_navi, btn_setting;
    int admin_s;
    String userID;

    private ListView list;
    private ArrayList<HashMap<String, String>> mArrrayList;
    private String mJsonString;
    private ListAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private forum_forum.GetData task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_forum);

        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        final allround ADMIN = (allround) getApplicationContext();
        final allround SCHADD = (allround) getApplicationContext(); //학교 주소
        final allround SCHPH = (allround) getApplicationContext(); //학교 전화번호
        final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환

        userID = ID.getID();
        SCHOOL.getSCHOOL();
        SCHADD.getSCHADD();
        SCHPH.getSCHPH();
        NICKNAME.getSCHPH();
        ////////////////////////////////////////////////////////////////////////
        btn_image = findViewById(R.id.go_forum_image);
        write = findViewById(R.id.go_forum_write);
        cancel = findViewById(R.id.btn_forum_forum_cancel);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        btn_image.setOnClickListener(this);
        write.setOnClickListener(this);
        cancel.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

//리스트뷰 정의
        list = (ListView) findViewById(R.id.list);
        mArrrayList = new ArrayList<>();

        GetData task = new GetData();
        task.execute("http://olivia7626.dothome.co.kr/Forumlist.php");
        /*
         * 리스트에 listener 추가 X / Adapter에서 추가. ㅇㄷ?
         */


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), forum_forum_in.class);
                HashMap check_position = mArrrayList.get(position);   //리스트뷰의 포지션에대한 객체를 가져옴.

                Log.d("TEST1234", "[게시판] 게시판 글번호 : " + check_position.get(TAG_notNum)); //글번호 찍히기

                String i = (String) check_position.get(TAG_notNum); //글번호 스트링 i에 넣어주기
                intent.putExtra("check_position1", i); //글번호 값 저장해 전달하기

                list.invalidateViews();
                startActivity(intent);

            }
        });

    }

    @Override
    public void onRefresh() {
//새로고침 코드
        mSwipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //새로고침 될 것들
                final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
                String schoolName;
                schoolName = SCHOOL.getSCHOOL();
                //adapter.init(); 이거 왜안되는지 모르겠음 이거 두개 대신 mArrayList.clear()해줌
                //adapter.notifyDataSetChanged();
                mArrrayList.clear();
                list.invalidateViews();
                forum_forum.GetData task = new forum_forum.GetData();
                task.execute("http://olivia7626.dothome.co.kr/Forumlist.php");
                list.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false); //새로고침 완료
            }
        }, 1000);
    }

    //서버 연결해서 데이터가져오기
    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override // 백그라운드 실행 전 변수 초기화 및 통신셋팅
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(forum_forum.this, "Please wait", null, true, true);
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
    private void showResult() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);


                String notTi = item.getString(TAG_notTi);
                String notNum = item.getString(TAG_notNum);
                String userID = item.getString(TAG_userID);

                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_notNum, notNum);
                hashMap.put(TAG_userID, userID);
                hashMap.put(TAG_notTi, notTi);


                mArrrayList.add(hashMap);
            }


            ListAdapter adapter = new SimpleAdapter(
                    forum_forum.this, mArrrayList, R.layout.item_list,
                    new String[]{TAG_notNum, TAG_userID, TAG_notTi},

                    new int[]{R.id.textView_list_notNum, R.id.textView_list_notTi, R.id.textView_list_notDate}
            );
            list.setAdapter(adapter);


        } catch (JSONException e) {
            Log.d(TAG, "showResult :", e);
        }

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.go_forum_image) { //그림게시판이동
            Intent intent01 = new Intent(forum_forum.this, forum_image.class);
            startActivity(intent01);
        }
        if (v.getId() == R.id.go_forum_write) { //글쓰기 버튼 누름
            Log.d("TEST1234", "글작성 버튼 눌림");
            Intent intent02 = new Intent(forum_forum.this, forum_forum_write.class);
            startActivity(intent02);
        }
        if (v.getId() == R.id.btn_forum_forum_cancel) { //빨간 버튼 눌렀을때, 메인으로 돌아가기
            Intent intent03 = new Intent(forum_forum.this, MainActivity.class);
            startActivity(intent03);
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(forum_forum.this, MainActivity.class);
            startActivity(intent1);
        }

        if (v.getId() == R.id.btn_setting) { // 설정

            Intent intent6 = new Intent(forum_forum.this, configActivity.class);
            startActivity(intent6);
        }
    }
}
