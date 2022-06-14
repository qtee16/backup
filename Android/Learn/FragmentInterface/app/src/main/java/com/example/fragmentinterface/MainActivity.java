package com.example.fragmentinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DeleleDialog{

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDialogCustom fragmentDialogCustom = new FragmentDialogCustom();
                fragmentDialogCustom.show(getSupportFragmentManager(), "dialog");
            }
        });
    }

    @Override
    public void deleteItem(boolean delete) {
        if (delete) {
            Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "No delete", Toast.LENGTH_SHORT).show();
        }
    }
}