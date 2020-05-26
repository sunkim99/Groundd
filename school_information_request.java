package com.example.ground;


import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//학교 정보 요청해 가져오기
public class school_information_request extends StringRequest {


    //서버 url 설정(php파일 연동)
    final static  private String URL="http://olivia7626.dothome.co.kr/SchoolInfo.php"; //SchoolInfo php 만들어서 학교 정보 연결하기
    private Map<String,String> map;

    public school_information_request(String schName, String schAdd, int schPh, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("schName",schName);
        map.put("schAdd",schAdd);
        map.put("schPh", Integer.toString(schPh));

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}