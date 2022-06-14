package com.example.beenlove;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edtTimeOne, edtTimeTwo;
    Button btnCalculate;
    TextView tvResult;
    Calendar calendarOne, calendarTwo;
    SimpleDateFormat dinhDangNgay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");

        mapping();

        edtTimeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDayOne();
            }
        });

        edtTimeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDayTwo();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long dayOne = calendarOne.getTimeInMillis();
                long dayTwo = calendarTwo.getTimeInMillis();
                long diff   = dayTwo - dayOne;
                long diffDays = diff / (24*60*60*1000);
                tvResult.setText(diffDays + " " + diff);
            }
        });

    }

    private void mapping() {
        edtTimeOne      = (EditText) findViewById(R.id.edtTimeOne);
        edtTimeTwo      = (EditText) findViewById(R.id.edtTimeTwo);
        btnCalculate    = (Button) findViewById(R.id.btnCalculate);
        tvResult        = (TextView) findViewById(R.id.tvResult);
    }

    private void selectDayOne() {
        calendarOne = Calendar.getInstance();
        int date = calendarOne.get(Calendar.DATE);
        int month = calendarOne.get(Calendar.MONTH);
        int year = calendarOne.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarOne.set(year, month, dayOfMonth);
                calendarOne.set(Calendar.HOUR_OF_DAY, 0);
                calendarOne.set(Calendar.MINUTE, 0);
                calendarOne.set(Calendar.SECOND, 0);
                calendarOne.set(Calendar.MILLISECOND, 0);
                edtTimeOne.setText(dinhDangNgay.format(calendarOne.getTime()));
            }
        }, year, month, date);
        datePickerDialog.show();
    }
    private void selectDayTwo() {
        calendarTwo = Calendar.getInstance();
        int date = calendarTwo.get(Calendar.DATE);
        int month = calendarTwo.get(Calendar.MONTH);
        int year = calendarTwo.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarTwo.set(year, month, dayOfMonth);
                calendarTwo.set(Calendar.HOUR_OF_DAY, 0);
                calendarTwo.set(Calendar.MINUTE, 0);
                calendarTwo.set(Calendar.SECOND, 0);
                calendarTwo.set(Calendar.MILLISECOND, 0);
                edtTimeTwo.setText(dinhDangNgay.format(calendarTwo.getTime()));
            }
        }, year, month, date);
        datePickerDialog.show();
    }

}