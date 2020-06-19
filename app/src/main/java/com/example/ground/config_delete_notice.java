package com.example.ground;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class config_delete_notice extends StringRequest {

    final static private String URL="http://olivia7626.dothome.co.kr/Config_delete_notice.php";
    private Map<String,String> map;


    public config_delete_notice(Integer notNum, Response.Listener<String>listener) {
        super(Method.POST, URL, listener, null);

        map= new HashMap<>();
        map.put("notNum",Integer.toString(notNum));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
