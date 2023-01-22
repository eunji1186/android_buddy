package com.example.buddy;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.buddy.databinding.ActivityDiaryBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Diary extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDiaryBinding binding;
    private TextView year, today;
    private TextView diary;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDiaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarDiary.toolbar);
        binding.appBarDiary.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        year = findViewById(R.id.year8);
        year.setText(getTime());

        today = findViewById(R.id.today);
        today.setText(getDate());

        diary = findViewById(R.id.diary_content);

        save = findViewById(R.id.save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //해당 날짜에 저장
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

    private String getDate() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date);

        return getTime;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.diary, menu);
        return true;
    }


}
