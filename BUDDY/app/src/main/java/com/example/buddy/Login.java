package com.example.buddy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private AlertDialog dialog;
    TextView login_id, login_pwd;
    Button login, join;
    TextView year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_id = findViewById(R.id.login_id);
        login_pwd = findViewById(R.id.login_pwd);

        login = findViewById(R.id.login);
        join = findViewById(R.id.join);

        year = findViewById(R.id.year);
        year.setText(getTime());

        join.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    public void onClick(View view){
        if(view.getId() == R.id.join){
            Intent intent_join = new Intent(this, Join.class);
            startActivity(intent_join);
        }

        else if(view.getId() == R.id.login){
            check_login();
            Intent intent_login = new Intent(this, MainActivity.class);
            startActivity(intent_login);

        }
    }

    private void check_login(){
        String id = login_id.getText().toString();
        String pwd = login_pwd.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);

                        dialog = builder.setMessage("로그인에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();

                        dialog.show();

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        dialog = builder.setMessage("계정을 다시 확인하세요")
                                        .setNegativeButton("다시시도", null)
                                        .create();
                        dialog.show();

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        };

        LoginRequest loginRequest = new LoginRequest(id, pwd, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Login.this);
        queue.add(loginRequest);
    }

    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String getTime = dateFormat.format(date);

        return getTime;
    }
    
    @Override
    protected void onStop(){
        super.onStop();

        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }
}