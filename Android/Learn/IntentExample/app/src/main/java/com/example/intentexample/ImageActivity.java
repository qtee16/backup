package com.example.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Collections;

public class ImageActivity extends Activity {

    TableLayout myTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        myTable = (TableLayout) findViewById(R.id.myTable);

        int row = 6;
        int col = 3;

        Collections.shuffle(MainActivity.arrayName);

        for (int i = 1; i <= row; i++) {
            TableRow tableRow = new TableRow(this);

            for (int j = 1; j <= col; j++) {
                ImageView imageView = new ImageView(this);

                // Converts 14 dip into its equivalent px
                Resources r = getResources();
                int px = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        95,
                        r.getDisplayMetrics()
                );

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(px, px);
                imageView.setLayoutParams(layoutParams);

                int pos = col * (i - 1) + j - 1;

                int imageID = getResources().getIdentifier(MainActivity.arrayName.get(pos), "drawable", getPackageName());
                imageView.setImageResource(imageID);
                imageView.setPadding(10, 10, 10, 10);
                tableRow.addView(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("imgName", MainActivity.arrayName.get(pos));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
            myTable.addView(tableRow);
        }
    }
}