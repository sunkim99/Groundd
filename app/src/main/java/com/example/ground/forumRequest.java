
package com.example.ground;
//게시판 내용을 디비에 저장해야하니까.. php연동...
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class forumRequest extends StringRequest {
    final static private String URL = "http://olivia7626.dothome.co.kr/Forum.php";
    private Map<String, String> map;


    public forumRequest(String userID, String notTi, String notCon, /*int notNum, int notType, Date notDate,*/ Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("notTi", notTi);
        map.put("notCon", notCon);
       /* map.put("notNum", notNum);
        map.put("notType", notType);
        map.put("notDate", notDate);*/

    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
