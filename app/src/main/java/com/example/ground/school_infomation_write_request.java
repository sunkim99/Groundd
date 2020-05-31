
package com.example.ground;
//학교 게시판 내용을 디비에 저장해야하니까..
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class school_infomation_write_request extends StringRequest {
    final static private String URL = "http://olivia7626.dothome.co.kr/SchoolForum.php";
    private Map<String, String> map;

    public school_infomation_write_request(String schName, String schTi, String schCon, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("schName", schName);
        map.put("schTi", schTi);
        map.put("schCon", schCon);


    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
