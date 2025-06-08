package com.example.intentexample;

import android.os.Bundle;
import android.os.Handler;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView resultText;
    Handler handler = new Handler();
    int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        progressBar = findViewById(R.id.progressBar);
        resultText = findViewById(R.id.resultText);

        // Receive data from Intent
        String message = getIntent().getStringExtra("message");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");

        // Simulate loading progress
        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus++;
                handler.post(() -> progressBar.setProgress(progressStatus));
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            handler.post(() -> {
                progressBar.setVisibility(ProgressBar.GONE);
                resultText.setText("Message: " + message + "\nDate: " + date + "\nTime: " + time);
            });
        }).start();
    }
}
