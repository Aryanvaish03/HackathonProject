package com.example.android.hackathonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    ProgressBar progressBar;
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar=findViewById(R.id.progressBar);

        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                counter++;
                progressBar.setProgress(counter);

                if(counter==100)
                {
                    timer.cancel();
                    startActivity(new Intent(SplashScreen.this,NGOHomeActivity.class));

                }
            }
        };

        timer.schedule(timerTask,10,10);

        startActivity(new Intent(SplashScreen.this,LoginActivity.class));
        finish();
    }
}