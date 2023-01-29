package com.example.buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextView login_id, login_pwd;
    Button login, join;
    TextView year;

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

        join.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String getTime = dateFormat.format(date);

        return getTime;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.join){
            Intent intent = new Intent(getApplicationContext(), Join.class);
            startActivity(intent);
        }

        else if(view.getId() == R.id.login){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
    }
}