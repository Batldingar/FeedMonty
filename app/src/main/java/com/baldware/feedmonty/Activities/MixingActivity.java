package com.baldware.feedmonty.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.baldware.feedmonty.Fragments.HintDialogFragment;
import com.baldware.feedmonty.R;
import com.baldware.feedmonty.Utils.HistoryHandler;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class MixingActivity extends FullScreenActivity {

    // Variables
    private Timer mTimer;
    private int mMixingBonus;

    // Tags & Keys
    public final static String MIXING_HINT_KEY = "mixing_hint";
    private static final String MIXING_HINT_FRAGMENT_TAG = "mixing_hint_fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemBars();
        setContentView(R.layout.activity_mixing);

        // Intent Extras
        int[] imageIDArray = getIntent().getIntArrayExtra("chosen_ingredients");
        int chosenValue = getIntent().getIntExtra("chosen_value", 0);

        ImageView imageViewOne = findViewById(R.id.ingredient_image_view_one);
        ImageView imageViewTwo = findViewById(R.id.ingredient_image_view_two);
        ImageView imageViewThree = findViewById(R.id.ingredient_image_view_three);

        imageViewOne.setImageResource(imageIDArray[0]);
        imageViewTwo.setImageResource(imageIDArray[1]);
        imageViewThree.setImageResource(imageIDArray[2]);

        // UI Animation
        GifImageView gifImageView = findViewById(R.id.mixing_gif);
        TextView textview = findViewById(R.id.mixing_bonus_text_view);
        Animation leftwardsAppear = AnimationUtils.loadAnimation(this, R.anim.leftwards_appear);
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

                                        // Showing the mixing hint dialog fragment on first start up
                                        HistoryHandler historyHandler = new HistoryHandler(MixingActivity.this, MainActivity.HISTORY_FILE_TAG);
                                        boolean ingredientsHintRead = historyHandler.getEntryBoolean(MIXING_HINT_KEY, HistoryHandler.Category.SETTINGS, false);
                                        if (!ingredientsHintRead) {
                                            HintDialogFragment hintDialogFragment = HintDialogFragment.newInstance(MIXING_HINT_KEY, getString(R.string.mixing_hint_text));
                                            hintDialogFragment.show(MixingActivity.this.getSupportFragmentManager(), MIXING_HINT_FRAGMENT_TAG);
                                        }

                                        // Showing/animating the "value picker"
                                        textview.setVisibility(View.VISIBLE);
                                        mMixingBonus = 0;
                                        mTimer = new Timer();
                                        mTimer.scheduleAtFixedRate(new TimerTask() {
                                            final Random random = new Random();
                                            @Override
                                            public void run() {
                                                int tempValue;
                                                do {
                                                    tempValue = (random.nextInt(11));
                                                } while (tempValue == mMixingBonus);
                                                mMixingBonus = tempValue;

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        textview.setText(getResources().getString(R.string.mixing_bonus_text, mMixingBonus));
                                                    }
                                                });
                                            }
                                        }, 0, 400);

                                        // On click method for the constraint layout (the whole screen)
                                        ConstraintLayout constraintLayout = findViewById(R.id.mixing_constraint_layout);
                                        constraintLayout.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                gifImageView.setFreezesAnimation(true);
                                                mTimer.cancel();

                                                Animation leftwardsDisappear = AnimationUtils.loadAnimation(MixingActivity.this, R.anim.leftwards_disappear);
                                                leftwardsDisappear.setAnimationListener(new Animation.AnimationListener() {
                                                    @Override
                                                    public void onAnimationStart(Animation animation) {

                                                    }

                                                    @Override
                                                    public void onAnimationEnd(Animation animation) {
                                                        gifImageView.setVisibility(View.INVISIBLE);
                                                        textview.setVisibility(View.INVISIBLE);
                                                        Intent intent = new Intent(MixingActivity.this, ScoreActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                        intent.putExtra("score", chosenValue + mMixingBonus);
                                                        startActivity(intent);

                                                        // Finish this activity so that when the next one opens the back button
                                                        // will bring the user back to the main activity
                                                        finish();
                                                    }

                                                    @Override
                                                    public void onAnimationRepeat(Animation animation) {

                                                    }
                                                });

                                                gifImageView.startAnimation(leftwardsDisappear);
                                                leftwardsDisappear.setStartOffset(750);
                                                textview.startAnimation(leftwardsDisappear);
                                            }
                                        });
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                                imageViewThree.startAnimation(backwardsDisappearThree);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        imageViewTwo.startAnimation(backwardsDisappearTwo);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                imageViewOne.startAnimation(backwardsDisappearOne);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        gifImageView.startAnimation(leftwardsAppear);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
