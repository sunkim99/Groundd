package com.example.ground;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//설정 - 내가쓴댓글 - (어플관리자)- 게시글 상세 내용 요청하기
public class config_my_forum_request extends StringRequest {


    //서버 url 설정(php파일 연동)
    final static  private String URL="http://olivia7626.dothome.co.kr/Config_My_Forum_detail.php"; //Config_My_Forum_detail php
    private Map<String,String> map;

    public config_my_forum_request(Integer notNum, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("notNum",Integer.toString(notNum));

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}