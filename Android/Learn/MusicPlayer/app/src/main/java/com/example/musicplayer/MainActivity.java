package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvSongName, tvEnd, tvStart;
    ImageButton btnPlay, btnNext, btnPre, btnStop;
    SeekBar sbSong;
    ImageView imgCD;
    Animation animation;
    ArrayList<Song> arraySong;
    MediaPlayer mediaPlayer;
    SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);

        mapping();
        addSong();
        initMediaPlayer();
        updateSongTime();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                    imgCD.clearAnimation();
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);
                    imgCD.startAnimation(animation);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                imgCD.startAnimation(animation);
                pos++;
                if (pos > arraySong.size() - 1) {
                    pos = 0;
                }
                initMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
            }
        });

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                imgCD.startAnimation(animation);
                pos--;
                if (pos < 0) {
                    pos = arraySong.size() - 1;
                }
                initMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                btnPlay.setImageResource(R.drawable.play);
                initMediaPlayer();
                imgCD.clearAnimation();
            }
        });

        sbSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sbSong.getProgress());
            }
        });

    }

    private void updateSongTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvStart.setText(timeFormat.format(mediaPlayer.getCurrentPosition()));
                sbSong.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 500);
            }
        },100);

    }

    private void initMediaPlayer() {
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(pos).getSongFile());
        tvSongName.setText(arraySong.get(pos).getSongName());
        tvEnd.setText(timeFormat.format(mediaPlayer.getDuration()));
        sbSong.setMax(mediaPlayer.getDuration());
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                pos++;
                if (pos > arraySong.size() - 1) {
                    pos = 0;
                }
                initMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
            }
        });
    }

    private void addSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Ai đó không phải anh", R.raw.ai_do_khong_phai_anh));
        arraySong.add(new Song("Chạnh lòng thương cô", R.raw.chanh_long_thuong_co));
        arraySong.add(new Song("Già cùng nhau là được", R.raw.gia_cung_nhau_la_duoc));
        arraySong.add(new Song("Phía sau một cô gái", R.raw.phia_sau_mot_co_gai));
        arraySong.add(new Song("Yêu đương khó quá thì chạy về khóc với anh", R.raw.yeu_duong_kho_qua_thi_chay_ve_khoc_voi_anh));

    }

    private void mapping() {
        tvSongName  = (TextView) findViewById(R.id.tvSongName);
        btnPlay     = (ImageButton) findViewById(R.id.btnPlay);
        btnPre      = (ImageButton) findViewById(R.id.btnPre);
        btnNext     = (ImageButton) findViewById(R.id.btnNext);
        btnStop     = (ImageButton) findViewById(R.id.btnStop);
        tvStart     = (TextView) findViewById(R.id.tvStart);
        tvEnd       = (TextView) findViewById(R.id.tvEnd);
        sbSong      = (SeekBar) findViewById(R.id.sbSong);
        imgCD       = (ImageView) findViewById(R.id.imgCD);
    }
}