package com.baldware.feedmonty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    // UI Elements
    private GifImageView gifImageView;
    private Button button;
    private TextView textView;
    private ImageView infoButton;

    // Tools
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Activity Properties
        setTitle("Baldware Games");

        // UI Animation
        startStartUpAnimation();

        // Monty On Press
        gifImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(R.string.start_text_emphasized);
            }
        });

        // Button On Press
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation leftwardsDisappear = AnimationUtils.loadAnimation(MainActivity.this, R.anim.leftwards_disappear);
                gifImageView.startAnimation(leftwardsDisappear);
                button.startAnimation(leftwardsDisappear);
                infoButton.startAnimation(leftwardsDisappear);

                leftwardsDisappear.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        gifImageView.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        infoButton.setVisibility(View.INVISIBLE);

                        startActivity(new Intent(MainActivity.this, IngredientsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                textView.startAnimation(leftwardsDisappear);

            }
        });

        // Info Button On Press
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Add AGBs
            }
        });

        // Content Animation
        countDownTimer = new CountDownTimer(5000, 5000) {
            int previousAnimationID;

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Random random = new Random();
                int animationID;

                do {
                    animationID = random.nextInt(5);
                } while (animationID == previousAnimationID);

                switch (animationID) {
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

    @Override
    protected void onResume() {
        super.onResume();

        if (gifImageView.getVisibility() == View.INVISIBLE) {
            gifImageView.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            infoButton.setVisibility(View.VISIBLE);

            startStartUpAnimation();
        }
    }

    private void startStartUpAnimation() {
        Animation forwardsAppear = AnimationUtils.loadAnimation(this, R.anim.forwards_appear);
        infoButton = findViewById(R.id.info_button);
        infoButton.startAnimation(forwardsAppear);

        Animation rightwardsAppear = AnimationUtils.loadAnimation(this, R.anim.rightwards_appear);
        gifImageView = findViewById(R.id.start_gif);
        gifImageView.startAnimation(rightwardsAppear);

        Animation leftwardsAppear = AnimationUtils.loadAnimation(this, R.anim.leftwards_appear);
        button = findViewById(R.id.start_button);
        button.startAnimation(leftwardsAppear);

        Animation downwardsAppear = AnimationUtils.loadAnimation(this, R.anim.downwards_appear);
        textView = findViewById(R.id.start_text);
        textView.startAnimation(downwardsAppear);
    }
}