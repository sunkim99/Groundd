package com.example.ground;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
//댓글작성하고 저장하는 php
public class CommentWriteRequest extends StringRequest {
    final static private String URL = "http://olivia7626.dothome.co.kr/Comment_write.php";
    private Map<String, String> map;

    public  CommentWriteRequest(int notNum,String userID, String commCon, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();

        map.put("notNum",notNum+"");
        map.put("userID",userID);
        map.put("commCon",commCon);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
