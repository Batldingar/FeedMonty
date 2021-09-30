package com.baldware.feedmonty;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Activity Properties
        setTitle("");

        // Intent Extras
        int score = getIntent().getIntExtra("score", 0);

        // UI Animation
        Animation leftwardsAppear = AnimationUtils.loadAnimation(this, R.anim.leftwards_appear);
        GifImageView gifImageView = findViewById(R.id.result_gif);
        gifImageView.startAnimation(leftwardsAppear);

        // Content Animation
        gifImageView.setBackgroundResource(R.drawable.monty_drink_1);

        CountDownTimer countDownTimerOne = new CountDownTimer(4000, 4000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(score <= 10) {
                    gifImageView.setBackgroundResource(R.drawable.monty_result_1);
                } else if (score <= 20) {
                    gifImageView.setBackgroundResource(R.drawable.monty_result_2);
                } else if (score <= 30) {
                    gifImageView.setBackgroundResource(R.drawable.monty_result_3);
                } else if (score <= 40) {
                    gifImageView.setBackgroundResource(R.drawable.monty_result_4);
                } else if (score <= 50) {
                    gifImageView.setBackgroundResource(R.drawable.monty_result_5);
                } else if (score <= 60) {
                    gifImageView.setBackgroundResource(R.drawable.monty_result_6);
                } else if (score <= 70) {
                    gifImageView.setBackgroundResource(R.drawable.monty_result_7);
                }


                CountDownTimer countDownTimerTwo = new CountDownTimer(2000, 2000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        TextView textView = findViewById(R.id.score_text);
                        textView.setText(String.valueOf(score));
                    }
                }.start();
            }
        }.start();

    }
}
