package com.example.ground;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
//설정에서 어플관리자가 게시글을 삭제할때 요청하는 php

public class config_admin_delete extends StringRequest {
    //서버 url 설정(php파일 연동)
    final static  private String URL="http://olivia7626.dothome.co.kr/Config_admin.php";
    private Map<String,String> map;

    public config_admin_delete(Integer notNum,Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("notNum",Integer.toString(notNum));

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
