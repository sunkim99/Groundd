package com.example.ground;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//댓글내용 요청
public class CommentRequest extends StringRequest{
    //서버 url 설정(php파일 연동)
    final static  private String URL="http://olivia7626.dothome.co.kr/Commentlist.php"; //Commentlist php 만들어서 댓글 정보 연결하기
    private Map<String,String> map;

    public CommentRequest(Integer notNum,  Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("notNum",Integer.toString(notNum));

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
