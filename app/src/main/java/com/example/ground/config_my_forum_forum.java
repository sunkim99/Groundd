package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

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
//설정에서 사용자가 작성한 게시글 가져오기

public class config_my_forum_forum extends AppCompatActivity implements View.OnClickListener{

    Button go_forum_image;
    Button top_navi, btn_setting;
    Button showmynot, showmycomm;
    TextView tvID;

    private static String TAG = "phptest_config_my_forum_forum";
    private static final String TAG_JSON = "webnautes";
    private static final String TAG_notNum = "notNum";
    private static final String TAG_notTi = "notTi";
    private static final String TAG_notDate = "notDate";
    private static final String TAG_commCon = "commCon";
    private static final String TAG_commDate = "commDate";

    boolean clickednum; // 댓글 버튼 클릭 화인 변수

    ListView list;
    ArrayList<HashMap<String, String>> MyNoticeList;
    ArrayList<HashMap<String, String>> MyCommentList;
    String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_my_forum_forum);

        go_forum_image = findViewById(R.id.go_config_forum_image);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        showmynot = findViewById(R.id.button_myNotice);
        showmycomm = findViewById(R.id.button_myComment);

        allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        String userID = ID.getID();

        tvID=findViewById(R.id.tvID);
        tvID.setText(userID);
        Log.d("TEST1234", "[Config] 쓰레드확인1:"+userID);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
        go_forum_image.setOnClickListener(this);


        //리스트뷰 정의
        list = (ListView) findViewById(R.id.config_list);
        MyNoticeList = new ArrayList<>();
        MyCommentList = new ArrayList<>();

        showmynot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //내 게시글 보기 버튼 클릭시
                allround ID = (allround) getApplicationContext();
                String userID = ID.getID();

                clickednum = true; // 불변수 true로 주기 (onPostexecute 에서 true면  showmyNotice()실행)

                config_my_forum_forum.MyNotice task = new config_my_forum_forum.MyNotice();
                task.execute("http://olivia7626.dothome.co.kr/config_mynoticelist.php?userID=" + userID); //유저 아이디를 보냄, 게시글 확인하는 php파일 연동
                Log.d("TEST1324", "전당된 유저아이디 " + userID);
            }
        });
        showmycomm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 내 댓글 보기 버튼 클릭시
                allround ID = (allround) getApplicationContext();
                String userID = ID.getID();

                clickednum = false; // 불변수 false로 주기 (onPostexecute에서 false면 ShowmyComment()실행)

                config_my_forum_forum.MyNotice task = new config_my_forum_forum.MyNotice();
                task.execute("http://olivia7626.dothome.co.kr/config_mycommentlist.php?userID=" + userID); //유저 아이디 보냄, 댓글 확인 하는 php파일 연동
                Log.d("TEST1324", "전당된 유저아이디 " + userID);
            }
        });

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 클릭시 해당 게시글로 이동
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), config_my_forum_in.class); //클릭하면 이동하는 화면
                    HashMap check_position = MyNoticeList.get(position);   //리스트뷰의 포지션에대한 객체를 가져옴.

                    Log.d("TEST1234", "게시판 글번호 " + check_position.get(TAG_notNum)); //글번호 찍히기

                    String i = (String) check_position.get(TAG_notNum); //글번호 스트링 i에 넣어주기
                    intent.putExtra("check_position1", i); //글번호 값 저장해 전달하기

                    startActivity(intent);
                }
            });

    }

        //사용자가 쓴 게시글 불러오기
        private class MyNotice extends AsyncTask<String, Void, String> {
            ProgressDialog progressDialog;
            String errorString = null;

            @Override // 백그라운드 실행 전 변수 초기화 및 통신셋팅
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(config_my_forum_forum.this, "Please wait", null, true,true);
            }

            @Override // 백그라운드에 저장된 데이터 뿌려주기
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                progressDialog.dismiss();
                mJsonString = result;
                if(clickednum == true){ // 불변수 확인해서 내 글보기 클릭시 true 설정
                showMyNotice(); // true면 showMyNotice() -> 게시글 번호(번호는 invisible), 제목, 날짜 가져와서 리스트에 삽입
                }
                else{
                ShowMyComment(); // false면 showMyNotice() -> 게시글 번호(번호는 invisible), 내용, 날짜 가져와서 리스트에 삽입
                }
            }

            @Override // 서벼연결해서 데이터 저장
            protected String doInBackground(String... params) {

                String serverURL = params[0];
                try {
                    URL url = new URL(serverURL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setDoInput(true);
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

        private void showMyNotice(){
        MyNoticeList.clear(); //리스트 내용 초기화, 초기화안하면 버튼 클릭할때마다 리스트 아이템이 중복으로 쌓임

            try {
                JSONObject jsonObject = new JSONObject(mJsonString);
                Log.d("가져온 notice json 데이터 : ", String.valueOf(jsonObject));
                JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

                for (int i=0;i<jsonArray.length(); i++){
                    JSONObject item = jsonArray.getJSONObject(i);

                    String notNum = item.getString(TAG_notNum);
                    String notTi  = item.getString(TAG_notTi);
                    String notDate  = item.getString(TAG_notDate);

                    HashMap<String, String> hashMap = new HashMap<>();

                    hashMap.put(TAG_notNum,notNum);
                    hashMap.put(TAG_notTi, notTi);
                    hashMap.put(TAG_notDate, notDate);

                    MyNoticeList.add(hashMap);
                }


                ListAdapter adapter = new SimpleAdapter(
                        config_my_forum_forum.this, MyNoticeList,R.layout.config_item_list,
                        new String[]{TAG_notNum,TAG_notTi,TAG_notDate},
                        new int[]{R.id.textView_list_config_notNum, R.id.textView_list_config_notTi,R.id.textView_list_config_notDate}
                );
                list.setAdapter(adapter);



            } catch (JSONException e){
                Log.d(TAG, "showResult :",e);
            }


    }

    private void ShowMyComment() {
        MyCommentList.clear();
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            Log.d("가져온 comment json 데이터 : ", String.valueOf(jsonObject));
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i =0 ; i < jsonArray.length(); i++){
                JSONObject item = jsonArray.getJSONObject(i);

                String notNum = item.getString(TAG_notNum);
                String commCon = item.getString(TAG_commCon);
                String commDate = item.getString(TAG_commDate);

                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_notNum,notNum);
                hashMap.put(TAG_commCon,commCon);
                hashMap.put(TAG_commDate,commDate);

                MyCommentList.add(hashMap);
            }
            ListAdapter adapter = new SimpleAdapter(
                    config_my_forum_forum.this, MyCommentList,R.layout.config_item_list,
                    new String[]{TAG_notNum,TAG_commCon,TAG_commDate},
                    new int[]{R.id.textView_list_config_notNum,R.id.textView_list_config_notTi,R.id.textView_list_config_notDate}
            );
            list.setAdapter(adapter);
        } catch (JSONException e) {
            Log.d(TAG, "showResult :", e);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.go_config_forum_image) {
            Intent intent01 = new Intent(config_my_forum_forum.this, config_my_forum_image.class);
            startActivity(intent01);
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(config_my_forum_forum.this, MainActivity.class);
            startActivity(intent1);
        }
        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(config_my_forum_forum.this, configActivity.class);
            startActivity(intent6);
        }
    }

}
