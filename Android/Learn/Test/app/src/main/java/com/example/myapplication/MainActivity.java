package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> arrayName = new ArrayList<>();

        arrayName.add("Thang");
        arrayName.add("Lan");
        arrayName.remove(0);
        Log.d("THANG", "Size:" + arrayName);
    }
}