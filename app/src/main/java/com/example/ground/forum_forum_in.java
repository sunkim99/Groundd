package com.example.ground;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
//게시판에서 게시물을 눌렀을때 해당게시물 보여지는화면

public class forum_forum_in extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "phptest_forum_forum";
    private static final String TAG_JSON = "webnautes";
    private static final String TAG_commCon = "commCon";
    private static final String TAG_commDate = "commDate";


    Button btn_image, write, cancel;
    Button top_navi, btn_setting;

    TextView id_notTi, id_notCon, id_notNum, id_notDate, id_userNick, store_id;

    private EditText commment_Context;

    ListView list;
    ArrayList<HashMap<String, String>> mArrrayList;
    String mJsonString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_forum_in);
        //final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환
        allround ADMIN = (allround) getApplicationContext();

        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        String userID = ID.getID();
        store_id = findViewById(R.id.store_id);
        store_id.setText(userID);






        btn_image = findViewById(R.id.go_forum_image);
        write = findViewById(R.id.go_forum_write); //댓글쓰기버튼
        cancel = findViewById(R.id.go_forum_forum);

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        btn_image.setOnClickListener(this);
        write.setOnClickListener(this); //댓글쓰기 이벤트
        //cancel.setOnClickListener(this); <--------------------------- 활성화시 오류
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);

        commment_Context = findViewById(R.id.commCon);

        Intent intent1 = getIntent();
        String notNum1 = intent1.getStringExtra("check_position1");
        Integer notNum = Integer.valueOf(notNum1);
        id_notNum = findViewById(R.id.id_notNum);
        id_notNum.setText(notNum1);
        Log.d("TEST1234", String.valueOf(notNum));


        /*String userNick = NICKNAME.getNICKNAME();


        id_userNick.setText(userNick);*/ //닉네임은 이렇게말고 쿼리문에서 가져와야하는데 작성하는거 모르겠음..
        id_userNick = findViewById(R.id.id_userNick);
        id_notTi = findViewById(R.id.id_notTi);
        id_notDate = findViewById(R.id.id_notDate);
        id_notCon = findViewById(R.id.id_notCon);

        Log.d("TEST1234", "[School Info] 쓰레드확인!!!");
        //학교 정보 가져오기
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jasonObject = new JSONObject(response);
                    boolean success = jasonObject.getBoolean("success");
                    if (success) {
                        Log.d("TEST1234", "[Forum] 쓰레드확인");

                        String number = jasonObject.getString("notNum");
                        Log.d("TEST1234", "[Forum] 쓰레드확인1:");
                        Log.d("TEST1234", "[Forum]  가져온 글번호 :" + number);
                        //String schName11 = schName1;
                        //Log.d("TEST1234", "학교이름 : " + schName1);


                        // SCHOOL.setSCHOOL(schName11);  // 전역변수는 schName11의 값을 가짐


                        String userID = jasonObject.getString("userID");
                        Log.d("TEST1234", "[Forum] 아이디 " + userID);

                        String title = jasonObject.getString("notTi");
                        Log.d("TEST1234", "[Forum]  제목 " + title);
                        String notCon = jasonObject.getString("notCon");

                        String notDate = jasonObject.getString("notDate");

                        Log.d("TEST1234", "[Forum] 쓰레드확인2:");


                        id_userNick.setText(userID);
                        id_notTi.setText(title);
                        id_notCon.setText(notCon);
                        id_notDate.setText(notDate);


                    } else {
                        Log.d("TEST1234", "[Forum] 게시글 정보");
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };
        forum_froum_detail_request ffd = new forum_froum_detail_request(notNum, responseListener);
        RequestQueue queue = Volley.newRequestQueue(forum_forum_in.this);
        queue.add(ffd);

        // 댓글 내용 불러오기

        list = (ListView) findViewById(R.id.comment_list);
        mArrrayList = new ArrayList<>();
/*
        GetData task = new GetData();
        task.execute("http://olivia7626.dothome.co.kr/Commentlist.php");
 */
    }
/*
    private class GetData extends AsyncTask<String,Void,String>{

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(forum_forum_in.this, "Please wait", null, true,true);

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mJsonString = result;
            showResult();
        }

        @Override
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
    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i=0;i<jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);


                String commCon = item.getString(TAG_commCon);
                String commDate = item.getString(TAG_commDate);

                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_commCon, commCon);
                hashMap.put(TAG_commDate, commDate);


                mArrrayList.add(hashMap);
            }
                ListAdapter adapter = new SimpleAdapter(
                        forum_forum_in.this, mArrrayList,R.layout.comment_item_list,
                        new String[]{TAG_commCon,TAG_commDate},

                        new int[]{R.id.textView_list_commCon,R.id.textView_list_commDate}
                );
                list.setAdapter(adapter);
            }
                catch (JSONException e){
                Log.d(TAG, "showResult :",e);
        }
    }

*/

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.go_forum_write) {
            Log.d("TEST1234", "[게시판 댓글] 댓글 작성버튼 눌림");

            String notNum = id_notNum.getText().toString();
            String userID =  store_id.getText().toString();
            String commCon = commment_Context.getText().toString();

            int notNum_temp = Integer.parseInt(notNum);
            //   commCon = commCon.replace("'","'");

            Response.Listener<String> commentwriteListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("TEST1234", "[게시판 댓글] 확인1" + Thread.currentThread());
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");

                        String sta = jsonObject.getString("str");
                        Log.d("TEST1234", "[게시판 댓글] success : " + success);
                        Log.d("TEST1324", "[게시판 댓글] php->안스 값 : " + sta);

                        Toast.makeText(getApplicationContext(), "댓글이 작성되었습니다", Toast.LENGTH_SHORT).show();

                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };



            CommentWriteRequest commentWriteRequest = new CommentWriteRequest(notNum_temp,userID,commCon, commentwriteListener);
            Log.d("TEST1234", "[게시판 댓글] 확인4 " + Thread.currentThread());
            RequestQueue commentWritequeue = Volley.newRequestQueue(forum_forum_in.this);
            Log.d("TEST1234", "[게시판 댓글] 확인5 " + Thread.currentThread());
            commentWritequeue.add(commentWriteRequest);
            Log.d("TEST1234", "[게시판 댓글] 확인6 " + Thread.currentThread());
        }
    }
}