package com.example.buddy;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://127.0.0.0:142/BUDDY/UserRegister.php";
    private Map<String, String> parameters;
    
    public RegisterRequest(String userId, String userPassword, String userName, String userEmail, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("userID", userId);
        parameters.put("userPassword", userPassword);
        parameters.put("userName", userName);
        parameters.put("userEmail", userEmail);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError{
        return parameters;
    }
}
