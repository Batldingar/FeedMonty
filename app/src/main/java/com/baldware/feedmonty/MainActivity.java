package com.baldware.feedmonty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        GifImageView gifImageView = findViewById(R.id.start_gif);

        countDownTimer = new CountDownTimer(5000, 3000) {
            int previousAnimationID;

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Random random = new Random();
                int animationID;

                do{
                    animationID = random.nextInt(5);
                }while(animationID == previousAnimationID);

                switch(animationID) {
                    case 0:
                        gifImageView.setImageResource(R.drawable.monty_idle_1);
                        break;
                    case 1:
                        gifImageView.setImageResource(R.drawable.monty_idle_2);
                        break;
                    case 2:
                        gifImageView.setImageResource(R.drawable.monty_idle_3);
                        break;
                    case 3:
                        gifImageView.setImageResource(R.drawable.monty_idle_4);
                        break;
                    case 4:
                        gifImageView.setImageResource(R.drawable.monty_idle_5);
                        break;
                }

                previousAnimationID = animationID;

                countDownTimer.start();
            }
        }.start();
    }
}