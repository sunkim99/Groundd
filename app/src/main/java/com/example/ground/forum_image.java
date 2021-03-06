/* 완성x라 전체 주석처리
package com.example.ground;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

//그림게시판 -forumforum 일단은 긁어옴. 수정봐야함. 게시판이랑 동일하게 변경해야함.
public class forum_image extends AppCompatActivity {
    private static String TAG = "phptest_forum_forum";
    private static final String TAG_JSON = "webnautes";
    private static final String TAG_notCon = "notCon";
    private static final String TAG_notTi = "notTi";

    Button btn_image, write, cancel;

    ListView list;
    ArrayList<HashMap<String, String>> mArrrayList;
    String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_image); //forum_image xml이랑 연결

        btn_image = findViewById(R.id.go_forum_image);
        write = findViewById(R.id.go_forum_write);
        cancel = findViewById(R.id.btn_forum_forum_cancel);


        //리스트뷰 정의
        list = (ListView) findViewById(R.id.list);
        mArrrayList = new ArrayList<>();

        forum_image.GetData task = new forum_image().GetData();
        task.execute("http://olivia7626.dothome.co.kr/Forumlist.php"); //php경로 수정

//
        btn_image.setOnClickListener(this);
        write.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    //서버 연결해서 데이터가져오기
    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override // 백그라운드 실행 전 변수 초기화 및 통신셋팅
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(forum_forum.this, "Please wait", null, true,true);
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
    //
    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i=0;i<jsonArray.length(); i++){
                JSONObject item = jsonArray.getJSONObject(i);


                String notTi  = item.getString(TAG_notTi);
                String notCon  = item.getString(TAG_notCon);

                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_notTi, notTi);
                hashMap.put(TAG_notCon, notCon);

                mArrrayList.add(hashMap);
            }
            ListAdapter adapter = new SimpleAdapter(
                    forum_forum.this, mArrrayList,R.layout.item_list,
                    new String[]{TAG_notTi,TAG_notCon},
                    new int[]{R.id.textView_list_notTi,R.id.textView_list_notCon}
            );
            list.setAdapter(adapter);
        } catch (JSONException e){
            Log.d(TAG, "showResult :",e);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.go_forum_image) { //그림게시판이동
            Intent intent01 = new Intent( forum_forum.this, forum_image.class);
            startActivity(intent01);
        }
        if(v.getId() == R.id.go_forum_write) { //글쓰기 버튼 누름
            Log.d("TEST1234", "글작성 버튼 눌림");
            Intent intent02 = new Intent( forum_forum.this, forum_forum_write.class);
            startActivity(intent02);
        }
        if(v.getId() == R.id.btn_forum_forum_cancel) {
            finish();
        }
    }
}
*/
package com.example.ground;
//이건 forumforum에서 intent 해야해서일단 필요한 부분 실제로 사용할건 위쪽 주석처리부분
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class forum_image extends AppCompatActivity implements View.OnClickListener  {
    Button go_forum_forum;
    Button top_navi, btn_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_image); //forum_image xml이랑 연결

        go_forum_forum = findViewById(R.id.go_forum_forum);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        go_forum_forum.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.go_forum_forum) {
            Intent intent1 = new Intent(forum_image.this, forum_forum.class);
            startActivity(intent1);
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(forum_image.this, MainActivity.class);
            startActivity(intent1);
        }


        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(forum_image.this, configActivity.class);
            startActivity(intent6);
        }
    }
}

