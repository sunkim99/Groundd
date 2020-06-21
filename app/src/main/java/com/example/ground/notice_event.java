package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

//공지 - 이벤트
public class notice_event extends AppCompatActivity implements View.OnClickListener , SwipeRefreshLayout.OnRefreshListener{

    Button go_notice_notice;
    Button top_navi, btn_setting;
    Button cancel, write;


    int admin_s;


    private static String TAG = "phptest_notice_event";
    private static final String TAG_JSON = "webnautes";
    private static final String TAG_annNum = "annNum";
    private static final String TAG_annTi = "annTi";
    private static final String TAG_userID= "userID";

    private ListView list;
    private ArrayList<HashMap<String, String>> mArrrayList;
    private HashMap<String, String> itsreal;
    private String mJsonString;

    private ListAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout; //새로고침
    private notice_event.GetData task; //새로고침때문에 추가


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_event);
        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        final allround ADMIN = (allround) getApplicationContext();

        String userID;
        userID = ID.getID();

        final allround SCHADD = (allround) getApplicationContext(); //학교 주소
        final allround SCHPH = (allround) getApplicationContext(); //학교 전화번호
        final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환

        SCHOOL.getSCHOOL();
        SCHADD.getSCHADD();
        SCHPH.getSCHPH();
        NICKNAME.getSCHPH();

        ///////////////////////////////////////////////////////////////////////////////////////
        go_notice_notice = findViewById(R.id.go_notice_notice);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);
        cancel = findViewById(R.id.btn_go_notice_notice_cancel);
        write = findViewById(R.id.go_notice_notice_write);

        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
        go_notice_notice.setOnClickListener(this);
        cancel.setOnClickListener(this);
        write.setOnClickListener(this);

        admin_s = ADMIN.getADMIN();
        if (admin_s == 0) {
            //일반 사용자는 접근불가. 1이 없는이유는 메인에서 버튼 비활성화 시켰으니까.
            write.setVisibility(Button.GONE);
        } else if (admin_s == 2) {
            write.setVisibility(Button.VISIBLE);
        }



        //리스트뷰 정의
        list = (ListView) findViewById(R.id.list);
        mArrrayList = new ArrayList<>();

        notice_event.GetData task = new notice_event.GetData();
        task.execute("http://olivia7626.dothome.co.kr/Notice_Event_List.php"); ///php추가하기


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), notice_event_in.class);

                HashMap check_position = mArrrayList.get(position);   //리스트뷰의 포지션에대한 객체를 가져옴.

                Log.d("TEST1234","이벤트 글번호 : "+ check_position.get(TAG_annNum)); //글번호 찍히기

                String i = (String) check_position.get(TAG_annNum); //글번호 스트링 i에 넣어주기
                intent.putExtra("check_position1", i); //글번호 값 저장해 전달하기

                list.invalidateViews();
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

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
                mArrrayList.clear();
                list.invalidateViews();
                notice_event.GetData task = new notice_event.GetData();
                task.execute("http://olivia7626.dothome.co.kr/Notice_Event_List.php"); ///수정하기
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

            progressDialog = ProgressDialog.show(notice_event.this, "Please wait", null, true,true);
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
                if(responseStatusCode == HttpURLConnection.HTTP_OK){
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) !=null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                Log.d(TAG, "InsertData : Error",e);
                errorString = e.toString();
            }
            return null;
        }
    }
    ///
    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i=0;i<jsonArray.length(); i++){
                JSONObject item = jsonArray.getJSONObject(i);


                String annTi  = item.getString(TAG_annTi);
                String userID = item.getString(TAG_userID);
                String annNum  = item.getString(TAG_annNum);


                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_annNum, annNum);
                hashMap.put(TAG_userID,userID);
                hashMap.put(TAG_annTi, annTi);



                mArrrayList.add(hashMap);
            }
            ListAdapter adapter = new SimpleAdapter(
                    notice_event.this, mArrrayList,R.layout.item_list_admin,
                    new String[]{TAG_annNum,TAG_annTi,TAG_userID},

                    new int[]{R.id.textView_list_notNum,R.id.textView_list_notDate,R.id.textView_list_notTi}
            );
            list.setAdapter(adapter);



        } catch (JSONException e){
            Log.d(TAG, "showResult :",e);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.go_notice_notice) { //공지로 돌아가기
            Intent intent01 = new Intent(notice_event.this, notice_notice.class);
            startActivity(intent01);
        }
        if (v.getId() == R.id.top_navi) {//상단바 클릭
            Intent intent1 = new Intent(notice_event.this, MainActivity.class);
            startActivity(intent1);
        }
        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(notice_event.this, configActivity.class);
            startActivity(intent6);
        }
        if (v.getId() == R.id.btn_go_notice_notice_cancel) { // 종료(빨간버튼)
            Intent intent7 = new Intent(notice_event.this, MainActivity.class);
            startActivity(intent7);
        }
        if (v.getId() == R.id.go_notice_notice_write) { //글쓰기 버튼 누르면 글작성 화면으로 넘어감
            Intent intent8 = new Intent(notice_event.this, notice_event_write.class);
            startActivity(intent8);
        }
    }
}
