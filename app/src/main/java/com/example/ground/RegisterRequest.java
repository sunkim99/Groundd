package com.example.ground;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
//회원가입 할때 요청
public class RegisterRequest extends StringRequest {

    //서버 url 설정(php파일 연동)
    final static  private String URL="http://olivia7626.dothome.co.kr/Register2.php";
    private Map<String,String>map;

    public RegisterRequest(String userID, String userPassword, String userName, String userNick,int userPh,int userParPh,String schName, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);//위 url에 post방식으로 값을 전송

        map=new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword",userPassword);
        map.put("userName",userName);
        map.put("userNick",userNick);
        map.put("userPh",Integer.toString(userPh));
        map.put("userParPh",Integer.toString(userParPh));
        map.put("schName",schName);


    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}