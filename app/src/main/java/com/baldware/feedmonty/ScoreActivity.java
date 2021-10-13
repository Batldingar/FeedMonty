package com.baldware.feedmonty;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import pl.droidsonroids.gif.GifImageView;

public class ScoreActivity extends AppCompatActivity {

    // Tags & Keys
    private final static String HIGHSCORE_KEY = "highscore";

    private int highscore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Activity Properties
        setTitle("");

        // Intent Extras
        int score = getIntent().getIntExtra("score", 0);

        // History Saving
        HistoryHandler historyHandler = new HistoryHandler(this, MainActivity.HISTORY_FILE_TAG);
        highscore = historyHandler.getEntryInt(HIGHSCORE_KEY, HistoryHandler.Category.GAME_STATE, 0);
        if(score > highscore) {
            historyHandler.writeHistory(HIGHSCORE_KEY, String.valueOf(score), HistoryHandler.Category.GAME_STATE);
            highscore = score;
        }

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
                if (score <= 10) {
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
                } else if (score <= 80) {
                    // Future update should replace this one with a new animation
                    gifImageView.setBackgroundResource(R.drawable.monty_result_7);
                }


                CountDownTimer countDownTimerTwo = new CountDownTimer(2000, 2000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        TextView scoreTextView = findViewById(R.id.score_text);

                        // Value Animation
                        ValueAnimator animator = ValueAnimator.ofInt(0, score);
                        animator.setDuration(2000);
                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public void onAnimationUpdate(ValueAnimator animation) {
                                String text = getResources().getString(R.string.score_text, (int) animation.getAnimatedValue());
                                scoreTextView.setText(text);
                            }
                        });
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                TextView highscoreTextView = findViewById(R.id.highscore_text);
                                String text = getResources().getString(R.string.highscore_text, highscore);
                                highscoreTextView.setText(text);

                                // UI Animation
                                Animation forwardsAppear = AnimationUtils.loadAnimation(ScoreActivity.this, R.anim.forwards_appear);
                                forwardsAppear.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        ConstraintLayout wholeView = findViewById(R.id.score_constraint_layout);
                                        wholeView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                finish();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                                highscoreTextView.startAnimation(forwardsAppear);
                            }
                        });
                        animator.start();
                    }
                }.start();
            }
        }.start();

    }
}
