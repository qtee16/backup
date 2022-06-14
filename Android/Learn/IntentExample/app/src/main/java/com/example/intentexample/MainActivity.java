package com.example.intentexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> arrayName;
    ImageView imgQues, imgAns;
    int REQUEST_CODE_IMG = 123;
    String strImgQues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgQues = (ImageView) findViewById(R.id.imageViewQues);
        imgAns = (ImageView) findViewById(R.id.imageViewAns);

        String[] listName = getResources().getStringArray(R.array.list_name);
        arrayName = new ArrayList<>(Arrays.asList(listName));

        Collections.shuffle(arrayName);

        strImgQues = arrayName.get(5);
        int imageID = getResources().getIdentifier(strImgQues, "drawable", getPackageName());
        imgQues.setImageResource(imageID);

        imgAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, ImageActivity.class), REQUEST_CODE_IMG);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_IMG && resultCode == RESULT_OK && data != null) {
            String ansImgName = data.getStringExtra("imgName");
            int imgID = getResources().getIdentifier(ansImgName, "drawable", getPackageName());
            imgAns.setImageResource(imgID);

            if (ansImgName.equals(strImgQues)) {
                Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.reload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuReload) {
            Collections.shuffle(arrayName);

            strImgQues = arrayName.get(5);
            int imageID = getResources().getIdentifier(strImgQues, "drawable", getPackageName());
            imgQues.setImageResource(imageID);
        }
        return super.onOptionsItemSelected(item);
    }
}