package com.baldware.feedmonty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;

public class MixingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixing);

        // Activity Properties
        setTitle("");

        // Intent Extras
        int[] imageIDArray = getIntent().getIntArrayExtra("chosen_ingredients");

        ImageView imageViewOne = findViewById(R.id.ingredient_image_view_one);
        ImageView imageViewTwo = findViewById(R.id.ingredient_image_view_two);
        ImageView imageViewThree = findViewById(R.id.ingredient_image_view_three);

        imageViewOne.setImageResource(imageIDArray[0]);
        imageViewTwo.setImageResource(imageIDArray[1]);
        imageViewThree.setImageResource(imageIDArray[2]);

        // UI Animation
        Animation leftwardsAppear = AnimationUtils.loadAnimation(this, R.anim.leftwards_appear);
        GifImageView gifImageView = findViewById(R.id.mixing_gif);
        leftwardsAppear.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation backwardsDisappearOne = AnimationUtils.loadAnimation(MixingActivity.this, R.anim.backwards_disappear);
                backwardsDisappearOne.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        imageViewOne.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        imageViewOne.setVisibility(View.INVISIBLE);

                        Animation backwardsDisappearTwo = AnimationUtils.loadAnimation(MixingActivity.this, R.anim.backwards_disappear);
                        backwardsDisappearTwo.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                imageViewTwo.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                imageViewTwo.setVisibility(View.INVISIBLE);

                                Animation backwardsDisappearThree = AnimationUtils.loadAnimation(MixingActivity.this, R.anim.backwards_disappear);
                                backwardsDisappearThree.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {
                                        imageViewThree.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        imageViewThree.setVisibility(View.INVISIBLE);
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                                backwardsDisappearThree.setStartOffset(1000);
                                imageViewThree.startAnimation(backwardsDisappearThree);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        backwardsDisappearTwo.setStartOffset(1000);
                        imageViewTwo.startAnimation(backwardsDisappearTwo);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                backwardsDisappearOne.setStartOffset(1000);
                imageViewOne.startAnimation(backwardsDisappearOne);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        gifImageView.startAnimation(leftwardsAppear);
    }
}
