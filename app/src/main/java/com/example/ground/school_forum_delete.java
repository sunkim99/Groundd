package com.example.ground;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
//관리자가 학교 게시판에서 게시글 지우기

public class school_forum_delete extends StringRequest {
    //서버 url 설정(php파일 연동)
    final static  private String URL="http://olivia7626.dothome.co.kr/School_admin_notice_Delete.php";
    private Map<String,String> map;

    public school_forum_delete(Integer schnotNum, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("schnotNum",Integer.toString(schnotNum));

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
