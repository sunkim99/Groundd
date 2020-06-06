
package com.example.ground;
//그림 게시판 내용을 디비에 저장해야하니까.. php연동...
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
//아직 만들어지지않았음
public class forumImageRequest extends StringRequest {
    final static private String URL = "http://olivia7626.dothome.co.kr/ForumImage.php"; //php만들기
    private Map<String, String> map;
    //php만들고 밑 코드도 수정해야함.

    public forumImageRequest(String notTi, String notCon, /*int notNum, int notType, Date notDate,*/ Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
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
