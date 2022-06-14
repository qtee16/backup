package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = (TextView) findViewById(R.id.tvTime);

        Calendar calendar = Calendar.getInstance();

        tvTime.append(calendar.getTime() + "\n");
        tvTime.append(calendar.get(Calendar.DATE) + "\n");
        SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");
        tvTime.append(dinhDangNgay.format(calendar.getTime()) + "\n");

        tvTime.append(calendar.get(Calendar.MINUTE) + "\n");
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("hh:mm:ss");
        tvTime.append(dinhDangGio.format(calendar.getTime()) + "\n");
    }
}