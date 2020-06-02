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

//공지 메인
public class notice_notice extends AppCompatActivity implements View.OnClickListener {

    Button go_notice_event;
    Button top_navi, btn_setting;
    Button cancel, write;


    int admin_s;


    private static String TAG = "phptest_notice_notice";
    private static final String TAG_JSON = "webnautes";
    private static final String TAG_annNum = "annNum";
    private static final String TAG_annTi = "annTi";
    private static final String TAG_userID= "userID";
    ListView list;
    ArrayList<HashMap<String, String>> mArrrayList;
    String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_notice);
        allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        final allround SCHOOL = (allround) getApplicationContext(); // 전역변수 SCHOOL 소환
        allround ADMIN = (allround) getApplicationContext();

        go_notice_event = findViewById(R.id.go_notice_event);
        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);
        cancel = findViewById(R.id.btn_go_notice_notice_cancel);
        write = findViewById(R.id.go_notice_notice_write);

        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
        go_notice_event.setOnClickListener(this);
        cancel.setOnClickListener(this);
        write.setOnClickListener(this);

        admin_s = ADMIN.getADMIN();
        if (admin_s == 0) {  //일반 사용자는 접근불가. 1이 없는이유는 메인에서 버튼 비활성화 시켰으니까.
            write.setVisibility(Button.GONE);
        } else if (admin_s == 2) {
            write.setVisibility(Button.VISIBLE);
        }



        //리스트뷰 정의
        list = (ListView) findViewById(R.id.list);
        mArrrayList = new ArrayList<>();

        notice_notice.GetData task = new notice_notice.GetData();
        task.execute("http://olivia7626.dothome.co.kr/AnnList.php");


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), forum_forum_in.class);

                //intent.putExtra("notNum",mArrrayList.get(0).toString());


               /* int check_position = list.getCheckedItemPosition();   //리스트뷰의 포지션을 가져옴.
                String vo = (String)parent.getAdapter().getItem(check_position);  //리스트뷰의 포지션 내용을 가져옴.
                Log.d("TEST1234","글번호 "+ check_position);
                Log.d("TEST1234","내용"+ vo);*/
              //  String curItem = mArrrayList.get(position);

                출처: https://itmining.tistory.com/1 [IT 마이닝]

                //Log.d("TEST1234","글번호 "+mArrrayList.get(0).toString());
                startActivity(intent);
            }
        });

    }
    //서버 연결해서 데이터가져오기
    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override // 백그라운드 실행 전 변수 초기화 및 통신셋팅
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(notice_notice.this, "Please wait", null, true,true);
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
                    notice_notice.this, mArrrayList,R.layout.item_list,
                    new String[]{TAG_annNum,TAG_annTi,TAG_userID},

                    new int[]{R.id.textView_list_notNum,R.id.textView_list_notTi,R.id.textView_list_notDate}
            );
            list.setAdapter(adapter);



        } catch (JSONException e){
            Log.d(TAG, "showResult :",e);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.go_notice_event) {
            Intent intent01 = new Intent(notice_notice.this, notice_event.class);
            startActivity(intent01);
        }
        if (v.getId() == R.id.top_navi) {
            Intent intent1 = new Intent(notice_notice.this, MainActivity.class);
            startActivity(intent1);
        }
        if (v.getId() == R.id.btn_setting) { // 설정
            Intent intent6 = new Intent(notice_notice.this, configActivity.class);
            startActivity(intent6);
        }
        if (v.getId() == R.id.btn_go_notice_notice_cancel) { // 종료
            finish();
        }
        if (v.getId() == R.id.go_notice_notice_write) { //글쓰기 버튼 누르면 글작성 화면으로 넘어감
            Intent intent6 = new Intent(notice_notice.this, notice_notice_write.class);
            startActivity(intent6);
        }
    }
}
