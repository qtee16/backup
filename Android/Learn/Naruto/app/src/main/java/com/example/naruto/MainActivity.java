package com.example.naruto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView myScore;
    Button startGame;
    RadioGroup choosePerson;
    RadioButton rdNaruto, rdRockLee, rdSasuke;
    SeekBar NarutoLane, RockLeeLane, SasukeLane;
    EditText oddPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[] checkStart = {0};

        myScore = (TextView) findViewById(R.id.score);
        choosePerson = (RadioGroup) findViewById(R.id.choosePerson);
        rdNaruto = (RadioButton) findViewById(R.id.rdNaruto);
        rdRockLee = (RadioButton) findViewById(R.id.rdRockLee);
        rdSasuke = (RadioButton) findViewById(R.id.rdSasuke);
        startGame = (Button) findViewById(R.id.start);
        NarutoLane = (SeekBar) findViewById(R.id.lane1);
        RockLeeLane = (SeekBar) findViewById(R.id.lane2);
        SasukeLane = (SeekBar) findViewById(R.id.lane3);
        oddPoint = (EditText) findViewById(R.id.oddPoint);


        final int[] myChoice = new int[1];
        choosePerson.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdNaruto:
                        myChoice[0] = R.id.rdNaruto;
                        Toast.makeText(MainActivity.this, "Choose Naruto", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rdRockLee:
                        myChoice[0] = R.id.rdRockLee;
                        Toast.makeText(MainActivity.this, "Choose Rock Lee", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rdSasuke:
                        myChoice[0] = R.id.rdSasuke;
                        Toast.makeText(MainActivity.this, "Choose Sasuke", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });


        CountDownTimer countDownTimer = new CountDownTimer(60000, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                Random random = new Random();
                int one = random.nextInt(50) + 1;
                int two = random.nextInt(50) + 1;
                int three = random.nextInt(50) + 1;
                int numOdd = Integer.parseInt(oddPoint.getText().toString());
                double lossOdd = numOdd == 1 ? numOdd : 1.2*(double) numOdd;

                if (NarutoLane.getProgress() >= NarutoLane.getMax()) {
                    this.cancel();
                    String tmp = "Naruto Win. ";
                    if (myChoice[0] == R.id.rdNaruto) {
                        setMyScore(myScore, 10, numOdd);
                        tmp += "You win. +" + Integer.toString(10*numOdd) + " points!";
                        Toast.makeText(MainActivity.this, tmp, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        double tmpScore = 10*lossOdd;
                        setMyScore(myScore, -10, lossOdd);
                        tmp += "You loss. -"  + Integer.toString((int) tmpScore) + " points!";
                        Toast.makeText(MainActivity.this, tmp, Toast.LENGTH_SHORT).show();
                    }
                    checkStart[0] = 1;
                    startGame.setVisibility(View.VISIBLE);
                    oddPoint.setFocusableInTouchMode(true);
                }
                if (RockLeeLane.getProgress() >= RockLeeLane.getMax()) {
                    this.cancel();
                    String tmp = "Rock Lee Win. ";
                    if (myChoice[0] == R.id.rdRockLee) {
                        setMyScore(myScore, 10, numOdd);
                        tmp += "You win. +" + Integer.toString(10*numOdd) + " points!";
                        Toast.makeText(MainActivity.this, tmp, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        double tmpScore = 10*lossOdd;
                        setMyScore(myScore, -10, lossOdd);
                        tmp += "You loss. -"  + Integer.toString((int) tmpScore) + " points!";
                        Toast.makeText(MainActivity.this, tmp, Toast.LENGTH_SHORT).show();
                    }
                    checkStart[0] = 1;
                    startGame.setVisibility(View.VISIBLE);
                    oddPoint.setFocusableInTouchMode(true);
                }
                if (SasukeLane.getProgress() >= SasukeLane.getMax()) {
                    this.cancel();
                    String tmp = "Sasuke Win. ";
                    if (myChoice[0] == R.id.rdSasuke) {
                        setMyScore(myScore, 10, numOdd);
                        tmp += "You win. +" + Integer.toString(10*numOdd) + " points!";
                        Toast.makeText(MainActivity.this, tmp, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        double tmpScore = 10*lossOdd;
                        setMyScore(myScore, -10, lossOdd);
                        tmp += "You loss. -"  + Integer.toString((int) tmpScore) + " points!";
                        Toast.makeText(MainActivity.this, tmp, Toast.LENGTH_SHORT).show();
                    }
                    checkStart[0] = 1;
                    startGame.setVisibility(View.VISIBLE);
                    oddPoint.setFocusableInTouchMode(true);
                }

                NarutoLane.setProgress(NarutoLane.getProgress() + one);
                RockLeeLane.setProgress(RockLeeLane.getProgress() + two);
                SasukeLane.setProgress(SasukeLane.getProgress() + three);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Finish", Toast.LENGTH_SHORT).show();
            }
        };

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choosePerson.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Please choose person", Toast.LENGTH_SHORT).show();
                }
                else if (oddPoint.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter odds", Toast.LENGTH_SHORT).show();
                }
                else {
                    oddPoint.setFocusable(false);
                    startGame.setVisibility(View.INVISIBLE);
                    if (checkStart[0] == 1) {
                        checkStart[0] = 0;
                        NarutoLane.setProgress(0);
                        RockLeeLane.setProgress(0);
                        SasukeLane.setProgress(0);
                        countDownTimer.start();
                    }
                    else {
                        countDownTimer.start();
                    }
                }

            }
        });

    }

    public void setMyScore(TextView myScore, int bonus, double odd) {
        int score = Integer.parseInt((String) myScore.getText());
        score += (double)bonus*odd;
        myScore.setText(Integer.toString(score));
    }
}