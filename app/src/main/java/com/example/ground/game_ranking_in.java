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

//게임 랭킹 내부
public class game_ranking_in extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener{

    Button go_other_menu;
    Button cancel;
    Button top_navi, btn_setting;


    private static String TAG = "phptest_game_ranking_in";
    private static final String TAG_JSON = "webnautes";
    private static final String TAG_userNick = "userNick";
    private static final String TAG_gameScore = "gameScore";


    private ListView list;
    private ArrayList<HashMap<String, String>> mArrrayList;
    private HashMap<String, String> itsreal;
    private String mJsonString;

    private ListAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout; //새로고침
    private game_ranking_in.GetData task; //새로고침때문에 추가



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ranking_in);
        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        final allround ADMIN = (allround) getApplicationContext(); // 관리자 소환
        final allround SCHADD = (allround) getApplicationContext(); //학교 주소
        final allround SCHPH = (allround) getApplicationContext(); //학교 전화번호
        final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환
        final allround GUGU_TOTAL = (allround) getApplicationContext(); //구구단 점수

        ID.getID();
        SCHOOL.getSCHOOL();
        SCHADD.getSCHADD();
        SCHPH.getSCHPH();
        NICKNAME.getSCHPH();
        GUGU_TOTAL.getGUGU_TOTAL();



        go_other_menu = findViewById(R.id.go_game_main);

        go_other_menu.setOnClickListener(this);

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);







        //리스트뷰 정의
        list = (ListView) findViewById(R.id.list);
        mArrrayList = new ArrayList<>();

        game_ranking_in.GetData task = new game_ranking_in.GetData();
        task.execute("http://olivia7626.dothome.co.kr/Game_Rank.php");///////////////////


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
                mArrrayList.clear();
                list.invalidateViews();
                game_ranking_in.GetData task = new game_ranking_in.GetData();
                task.execute("http://olivia7626.dothome.co.kr/Game_Rank.php");
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

            progressDialog = ProgressDialog.show(game_ranking_in.this, "Please wait", null, true,true);
        }

        @Override // 백그라운드에 저장된 데이터 뿌려주기
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "ranking3");
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

                Log.d(TAG, "ranking1");
                String userNick  = item.getString(TAG_userNick);
                String gameScore = item.getString(TAG_gameScore);



                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_userNick, userNick);
                hashMap.put(TAG_gameScore,gameScore);



                mArrrayList.add(hashMap);
            }
            ListAdapter adapter = new SimpleAdapter(
                    game_ranking_in.this, mArrrayList,R.layout.activity_game_rank_list,
                    new String[]{TAG_userNick,TAG_gameScore},

                    new int[]{R.id.game_ranking_nick,R.id.game_ranking_score}
            );
            list.setAdapter(adapter);
            Log.d(TAG, "ranking2");


        } catch (JSONException e){
            Log.d(TAG, "showResult :",e);
        }

    }

    public void onClick(View v) {
        if(v.getId() == R.id.go_game_main) {
            Intent intent01 = new Intent( this, game_main.class);
            startActivity(intent01);
        }
        if(v.getId() == R.id.btn_school_information_cancel) {
            finish();
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
        }


        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(this, configActivity.class);
            startActivity(intent6);
        }
    }
}
