
package com.example.ground;
//공지 게시판 내용을 디비에 저장해야하니까..
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class notice_write_request extends StringRequest {
    final static private String URL = "http://olivia7626.dothome.co.kr/NoticeWrite.php";
    private Map<String, String> map;

    public notice_write_request(String userID, String annTi, String annCon, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("annTi", annTi);
        map.put("annCon", annCon);


    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
