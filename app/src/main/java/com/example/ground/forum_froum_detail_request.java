package com.example.ground;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//게시판 상세 내용 요청하기
public class forum_froum_detail_request extends StringRequest {


        //서버 url 설정(php파일 연동)
        final static  private String URL="http://olivia7626.dothome.co.kr/Froum_Froum_detail.php"; //Froum_Froum_detail php 만들어서 학교 정보 연결하기
        private Map<String,String> map;

        public forum_froum_detail_request(Integer notNum,  Response.Listener<String>listener){
            super(Method.POST,URL,listener,null);

            map=new HashMap<>();
            map.put("notNum",Integer.toString(notNum));

        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
}