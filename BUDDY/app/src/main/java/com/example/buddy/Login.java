package com.example.buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Login extends AppCompatActivity {
    private Button login, join;
    private TextView login_id, login_pwd;
    private TextView year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_id = (TextView) findViewById(R.id.login_id);
        login_pwd = (TextView) findViewById(R.id.login_pwd);

        login = (Button)findViewById(R.id.login);
        join = (Button)findViewById(R.id.join);

        year = findViewById(R.id.year);
        year.setText(getTime());

        login.setOnClickListener(new Button.OnClickListener() {

            //setLogin();

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        join.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Join.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String getTime = dateFormat.format(date);

        return getTime;
    }

    private void setLogin(){
        
    }


}