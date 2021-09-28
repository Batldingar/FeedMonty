package com.baldware.feedmonty;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    }
}
