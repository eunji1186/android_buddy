package com.example.buddy;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Join extends AppCompatActivity {

    private TextView id_input, pwd_input, pwd_check_input, name_input, email_input;
    private Button id_check_btn, join_btn, cancel_btn;
    private TextView year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        id_input = findViewById(R.id.id_input);
        pwd_input = findViewById(R.id.pwd_input);
        pwd_check_input = findViewById(R.id.pwd_check_input);
        name_input = findViewById(R.id.name_input);
        email_input = findViewById(R.id.email_input);

        id_check_btn = findViewById(R.id.id_check_btn);
        join_btn = findViewById(R.id.join_btn);
        cancel_btn = findViewById(R.id.cancel_btn);

        year = findViewById(R.id.year2);
        year.setText(getTime());

        String id = id_input.getText().toString();
        String pwd = pwd_input.getText().toString();
        String pwd_check = pwd_check_input.getText().toString();
        String name = name_input.getText().toString();
        String email =  email_input.getText().toString();

        InsertData task = new InsertData();
        task.execute("http://" + /*localhost +*/ "/insert.php", id, pwd, name, email);

        id_input.setText("");
        pwd_input.setText("");
        pwd_check_input.setText("");
        name_input.setText("");
        email_input.setText("");

        id_check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
/*
        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                if(pwd == pwd_check){
                    builder.setTitle("회원가입 하시겠습니까?").setMessage("아이디: " + id + "\n" + "비밀번호: " + pwd);
                    builder.setPositiveButton();
                    builder.setNegativeButton();
                }

                else{
                    builder.setTitle("비밀번호 불일치").setMessage("비밀번호를 다시 한번 확인해주세요.");

                    AlertDialog alertDialog = builder.create();

                    alertDialog.show();
                }
            }
        });
*/
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Join.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String userID = (String) params[1];
            String userPassword = (String) params[2];
            String email = (String) params[3];
            String phoneNumber = (String) params[4];
            String userSort = (String) params[5];


            String serverURL = (String) params[0];
            String postParameters = "userID=" + userID + "&userPassword=" + userPassword + "&email=" + email + "&phoneNumber=" + phoneNumber + "&userSort=" + userSort;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }
        }
    }
}