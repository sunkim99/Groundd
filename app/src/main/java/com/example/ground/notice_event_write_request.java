
package com.example.ground;
//공지- 이벤트 내용을 디비에 저장
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class notice_event_write_request extends StringRequest {
    final static private String URL = "http://olivia7626.dothome.co.kr/Notice_Event_Write.php";
    private Map<String, String> map;

    public notice_event_write_request(String userID, String annTi, String annCon, Response.Listener<String> listener) {
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
