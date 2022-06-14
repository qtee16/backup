package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnDownload = (Button) findViewById(R.id.button);
        progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = progressBar.getProgress();
                int n = (100 - tmp) / 10;
                CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int current = progressBar.getProgress();
                        progressBar.setProgress(current + n);
                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this, "Time out", Toast.LENGTH_LONG).show();
                    }
                };
                countDownTimer.start();
            }
        });

    }
}