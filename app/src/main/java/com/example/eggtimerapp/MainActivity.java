package com.example.eggtimerapp;

import  androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    Button btn;
    TextView txt;

    boolean isTimerActive = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);

        btn = findViewById(R.id.btn);

        txt = findViewById(R.id.txt);

        seekBar.setMax(600);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CountDownTimer countDownTimer = null;
                if (isTimerActive) {

                    seekBar.setEnabled(true);
                    btn.setText("GO");
                    countDownTimer.cancel();
                    seekBar.setProgress(30);
                    txt.setText("00:30");

                    isTimerActive = false;

                } else {
                    isTimerActive = true;
                    btn.setText("Stop!");
                    seekBar.setEnabled(false);

                    countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                            updateTimer((int) millisUntilFinished / 1000);
                        }


                        @Override
                        public void onFinish() {

                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                            mediaPlayer.start();
                        }
                    }.start();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void updateTimer(int secondsLeft){

        int minutes = secondsLeft / 60;

        int seconds = secondsLeft - (minutes + 60);

        String secInString = String.valueOf(seconds);


        if(seconds <= 9){

            secInString = "0"+seconds;

        }

        txt.setText(minutes + ":" +secInString);

    }
    public void reset(){

    }

}