package com.example.ground;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//구구단 점수 보내기
public class game_total2_request extends StringRequest {


    //서버 url 설정(php파일 연동)
    final static  private String URL="http://olivia7626.dothome.co.kr/Game_Score.php"; //Game_Score php 만들어서 학교 정보 연결하기
    private Map<String,String> map;

    public game_total2_request(int userNum, int total, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("userNum", Integer.toString(userNum));
        map.put("total",Integer.toString(total));

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}