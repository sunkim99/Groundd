package com.example.ground;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
import java.util.ArrayList;
import java.util.HashMap;
//게시판에서 게시물을 눌렀을때 해당게시물 보여지는화면

public class forum_forum_in extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "phptest_forum_forum_in";
    private static final String TAG_JSON = "webnautes";
    private static final String TAG_userNick = "userNick";
    private static final String TAG_commCon = "commCon";
    private static final String TAG_commDate = "commDate";


    Button btn_image, write, cancel;
    Button top_navi, btn_setting;
    Button delete;

    TextView id_notTi, id_notCon, id_notNum, id_notDate, id_userNick, store_id;
    ImageView I_char_hair, I_char_face, I_char_cloth, I_char_acce;

    int admin_s;

    private EditText commment_Context;

    ListView list;
    ArrayList<HashMap<String, String>> mArrrayList;
    private String mJsonString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_forum_in);
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

        //final allround NICKNAME = (allround) getApplicationContext(); //전역변수 NICKNAME 소환

        final allround ID = (allround) getApplicationContext(); // 전역변수 ID 소환
        String userID = ID.getID();
        store_id = findViewById(R.id.store_id);
        store_id.setText(userID);

        final allround ADMIN = (allround) getApplicationContext();
        admin_s = ADMIN.getADMIN();

        btn_image = findViewById(R.id.go_forum_image);
        write = findViewById(R.id.go_forum_write); //댓글쓰기버튼
        cancel = findViewById(R.id.btn_forum_forum_in_cancel);
        delete = findViewById(R.id.button_delete);

        top_navi = findViewById(R.id.top_navi);
        btn_setting = findViewById(R.id.btn_setting);

        btn_image.setOnClickListener(this);
        write.setOnClickListener(this); //댓글쓰기 이벤트
        cancel.setOnClickListener(this);
        top_navi.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
        delete.setOnClickListener(this);

        String post_notNum;
        commment_Context = findViewById(R.id.commCon);

        Intent intent1 = getIntent();
        String notNum1 = intent1.getExtras().getString("check_position1");
        Integer notNum = Integer.valueOf(notNum1);


        id_notNum = findViewById(R.id.id_notNum);
        id_notNum.setText(notNum1);
        Log.d("TEST1234", String.valueOf(notNum));

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


        if (admin_s == 2){
            delete.setVisibility(Button.VISIBLE);
        }
        else{
            delete.setVisibility(Button.GONE);
        }


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
                        Log.d("TEST1234", "[Forum] 아이디 : " + userID);

                        String title = jasonObject.getString("notTi");
                        Log.d("TEST1234", "[Forum]  제목 : " + title);
                        String notCon = jasonObject.getString("notCon");

                        String notDate = jasonObject.getString("notDate");
                        String Date = jasonObject.getString("notDate");
                        Log.d("TEST1234", "[Forum] 날짜 : " + Date);


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


        GetData task = new GetData();
        task.execute("http://olivia7626.dothome.co.kr/Commentlist.php?notNum="+notNum1);
        Log.d("TEST1324","전당된 게시판번호 "+notNum1);

    }

    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(forum_forum_in.this, "Please wait", null, true, true);

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
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code -" + responseStatusCode);

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

    private void showResult() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            Log.d("가져온 json 데이터 : ", String.valueOf(jsonObject));
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);

                String userNick = item.getString(TAG_userNick);
                String commCon = item.getString(TAG_commCon);
                String commDate = item.getString(TAG_commDate);

                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_userNick, userNick);
                hashMap.put(TAG_commCon, commCon);
                hashMap.put(TAG_commDate, commDate);

                mArrrayList.add(hashMap);
            }
            ListAdapter adapter = new SimpleAdapter(
                    forum_forum_in.this, mArrrayList, R.layout.comment_item_list,
                    new String[]{TAG_userNick,TAG_commCon, TAG_commDate},
                    new int[]{R.id.textView_list_userNick,R.id.textView_list_commCon, R.id.textView_list_commDate}
            );
            list.setAdapter(adapter);
        } catch (JSONException e) {
            Log.d(TAG, "showResult :", e);
        }
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.go_forum_write) {
            Log.d("TEST1234", "[게시판 댓글] 댓글 작성버튼 눌림");
            AlertDialog.Builder builder = new AlertDialog.Builder(forum_forum_in.this);
            builder.setTitle("저장");
            builder.setMessage("댓글을 저장할까요?");
            builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String notNum = id_notNum.getText().toString();
                    String userID = store_id.getText().toString();
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


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };


                    CommentWriteRequest commentWriteRequest = new CommentWriteRequest(notNum_temp, userID, commCon, commentwriteListener);
                    Log.d("TEST1234", "[게시판 댓글] 확인4 " + Thread.currentThread());
                    RequestQueue commentWritequeue = Volley.newRequestQueue(forum_forum_in.this);
                    Log.d("TEST1234", "[게시판 댓글] 확인5 " + Thread.currentThread());
                    commentWritequeue.add(commentWriteRequest);
                    Log.d("TEST1234", "[게시판 댓글] 확인6 " + Thread.currentThread());
                    commment_Context.setText("");
                }
            });
            builder.setNegativeButton("아니요", null);
          /*  builder.setNeutralButton("게시판으로 돌아가기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });*/
            builder.create().show();

        }
        if (v.getId() == R.id.btn_forum_forum_in_cancel) { //빨간버튼
            finish();
        }
        if (v.getId() == R.id.button_delete) { // 삭제버튼
            AlertDialog.Builder builder = new AlertDialog.Builder(forum_forum_in.this);
            builder.setTitle("게시글 삭제");
            builder.setMessage("해당 게시글을 삭제하시겠습니까?");
            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent1 = getIntent();
                    String notNum1 = intent1.getStringExtra("check_position1");
                    final Integer notNum = Integer.valueOf(notNum1);
                    id_notNum = findViewById(R.id.id_notNum);
                    id_notNum.setText(notNum1);

                    //공지 삭제하기
                    Response.Listener<String> delete_notice1 = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jasonObject = new JSONObject(response);
                                boolean success = jasonObject.getBoolean("success");
                                if (success) {
                                    Log.d("TEST1234", "[게시글 삭제하기] 쓰레드확인");
                                    String ssss = jasonObject.getString("str");
                                    Log.d("TEST1234", "[게시글 삭제하기] 수행될 쿼리문 :" + ssss);

                                    Log.d("TEST1234", "[게시글 삭제하기] 쓰레드확인2:");

                                } else {
                                    Log.d("TEST1234", "[게시글 삭제하기] 게시글 정보오류 ");
                                    return;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    };
                    Intent intent = new Intent(forum_forum_in.this, forum_forum.class); //화면 어디로 넘어가는지 확인하기
                    Toast.makeText(getApplicationContext(), "해당 게시물이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    Log.d("TEST1234", "[게시글 삭제하기] 해당 게시물 삭제");
                    startActivity(intent);

                    config_delete_notice delete_notice = new config_delete_notice(notNum, delete_notice1);
                    RequestQueue comment_queue = Volley.newRequestQueue(forum_forum_in.this);
                    comment_queue.add(delete_notice);

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
    }
}