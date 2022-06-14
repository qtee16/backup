package com.example.fragmentorentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements iStudentInfo{

    FragmentContainerView fragmentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentInfo = (FragmentContainerView) findViewById(R.id.fragmentInfo);

        if (fragmentInfo != null) {
            fragmentInfo.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void getInfo(Student student, FragmentActivity fragmentActivity) {
        Configuration configuration = getResources().getConfiguration();
        FragmentStudentInfo fragmentStudentInfo = (FragmentStudentInfo) getSupportFragmentManager().findFragmentById(R.id.fragmentInfo);
        if (fragmentStudentInfo != null && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentStudentInfo.setInfo(student);
        } else {
            Intent intent = new Intent(MainActivity.this, StudentInfoActivity.class);
            intent.putExtra("student", student);
            startActivity(intent);
        }
    }

}