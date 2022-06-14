package com.example.fragmentorentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class StudentInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");
        FragmentStudentInfo studentInfo =
                (FragmentStudentInfo) getSupportFragmentManager().findFragmentById(R.id.fragmentDetail);
    }
}