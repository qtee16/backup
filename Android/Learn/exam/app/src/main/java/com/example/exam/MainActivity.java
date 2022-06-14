package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.textView1);
        tv.setTextColor(this.getResources().getColor(R.color.white));
        tv.setBackgroundResource(R.color.black);
        tv.setAllCaps(true);

    }
}