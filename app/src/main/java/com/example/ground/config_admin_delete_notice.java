package com.example.ground;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
//어플관리자가 공지에서 상세공지본다음에 삭제 버튼 누르는 작업

public class config_admin_delete_notice extends StringRequest {
    //서버 url 설정(php파일 연동)
    final static  private String URL="http://olivia7626.dothome.co.kr/Config_admin_notice.php";
    private Map<String,String> map;

    public config_admin_delete_notice(Integer annNum, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("annNum",Integer.toString(annNum));

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
