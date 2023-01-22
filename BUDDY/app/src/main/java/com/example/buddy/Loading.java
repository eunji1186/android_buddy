package com.example.buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Loading extends AppCompatActivity {

    TextView year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        year = (TextView) findViewById(R.id.year);
        year.setText(getTime());

        Loadingstart();
    }

    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String getTime = dateFormat.format(date);

        return getTime;    }

    private void Loadingstart(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable(){

            @Override
            public void run(){
                Intent intent=new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}