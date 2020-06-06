package com.example.ground;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//학교 게시판 상세 내용 요청하기
public class school_forum_list_request extends StringRequest {


    //서버 url 설정(php파일 연동)
    final static  private String URL="http://olivia7626.dothome.co.kr/School_Forum_detail.php"; //School_Forum_detail php 만들어서 학교 정보 연결하기
    private Map<String,String> map;

    public school_forum_list_request(Integer schnotNum, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("schnotNum",Integer.toString(schnotNum));

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}