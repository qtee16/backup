package com.example.tablelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtMessage;
    Button btnClick;
    EditText Min;
    EditText Max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMessage = (TextView) findViewById(R.id.txtMessage);
        btnClick = (Button) findViewById(R.id.btnClick);
        Min = (EditText) findViewById(R.id.txtMin);
        Max = (EditText) findViewById(R.id.txtMax);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _min = Min.getText().toString();
                String _max = Max.getText().toString();

                if (check(_min) == 1 && check(_max) == 1) {
                    int min =  Integer.parseInt(_min);
                    int max =  Integer.parseInt(_min);
                    Random rand = new Random();
                    int number = rand.nextInt(max - min + 1) + min;
                    txtMessage.setText(Integer.toString(number));
                }
                else {
                    Toast.makeText(MainActivity.this, "Thông tin nhập chưa đúng", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private int check(String value) {
        if (value.isEmpty()) return 0;
        for (int i = 0; i < value.length(); i++) {
            if (!('0' <= value.charAt(i) && value.charAt(i) <= '9')) return 0;
        }
        return 1;
    }
}