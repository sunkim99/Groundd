package com.example.ground;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//공지 게시판 상세 내용 요청하기
public class notice_notice_detail_request extends StringRequest {


    //서버 url 설정(php파일 연동)
    final static  private String URL="http://olivia7626.dothome.co.kr/Notice_detail.php"; //Notice_detail php
    private Map<String,String> map;

    public notice_notice_detail_request(Integer annNum, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("annNum",Integer.toString(annNum));

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}