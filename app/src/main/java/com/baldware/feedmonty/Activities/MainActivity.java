package com.baldware.feedmonty.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baldware.feedmonty.Fragments.PrivacyDialogFragment;
import com.baldware.feedmonty.Utils.HistoryHandler;
import com.baldware.feedmonty.R;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends FullScreenActivity {

    // UI Elements
    private GifImageView montyImageView; // Animated monty image view
    private Button startButton; // Green start button
    private TextView textView; // Title/Prompt text
    private ImageView infoButton; // Info button leading to privacy policy

    // Tools
    private CountDownTimer countDownTimer;

    // Tags & Keys
    public final static String HISTORY_FILE_TAG = "history_file";
    public final static String PRIVACY_POLICY_KEY = "privacy_policy";
    private static final String PRIVACY_POLICY_FRAGMENT_TAG = "privacy_policy_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemBars();
        setContentView(R.layout.activity_main);

        // TODO: Update the privacy policy
        // TODO: Comment/Rework the history handler
        // TODO: Rework the Fragments
        // TODO: Make every screen scrollable (scrollview) and test for all sizes and orientations

        // Showing the privacy dialog fragment on first start up
        HistoryHandler historyHandler = new HistoryHandler(this, HISTORY_FILE_TAG);
        boolean privacyPolicyRead = historyHandler.getEntryBoolean(PRIVACY_POLICY_KEY, HistoryHandler.Category.SETTINGS, false);
        if (!privacyPolicyRead) {
            PrivacyDialogFragment privacyDialogFragment = PrivacyDialogFragment.newInstance(getString(R.string.privacy_policy_title));
            privacyDialogFragment.show(MainActivity.this.getSupportFragmentManager(), PRIVACY_POLICY_FRAGMENT_TAG);
        }

        // Showing the UI animations on start up
        startUIAnimations();

        // On click method for Monty
        montyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(R.string.start_text_emphasized);
            }
        });

        // On click method for the start button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation leftwardsDisappear = AnimationUtils.loadAnimation(MainActivity.this, R.anim.leftwards_disappear);
                montyImageView.startAnimation(leftwardsDisappear);
                startButton.startAnimation(leftwardsDisappear);
                infoButton.startAnimation(leftwardsDisappear);
                textView.startAnimation(leftwardsDisappear);

                leftwardsDisappear.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        montyImageView.setVisibility(View.INVISIBLE);
                        startButton.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        infoButton.setVisibility(View.INVISIBLE);
                        textView.setText(R.string.start_text);

                        startActivity(new Intent(MainActivity.this, IngredientsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

        // On click method for the info button
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrivacyDialogFragment privacyDialogFragment = PrivacyDialogFragment.newInstance(getString(R.string.privacy_policy_title));
                privacyDialogFragment.show(MainActivity.this.getSupportFragmentManager(), PRIVACY_POLICY_FRAGMENT_TAG);
            }
        });

        // Performing Monty's idle animations
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
                        montyImageView.setImageResource(R.drawable.monty_idle_1);
                        break;
                    case 1:
                        montyImageView.setImageResource(R.drawable.monty_idle_2);
                        break;
                    case 2:
                        montyImageView.setImageResource(R.drawable.monty_idle_3);
                        break;
                    case 3:
                        montyImageView.setImageResource(R.drawable.monty_idle_4);
                        break;
                    case 4:
                        montyImageView.setImageResource(R.drawable.monty_idle_5);
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

        if (montyImageView.getVisibility() == View.INVISIBLE) {
            montyImageView.setVisibility(View.VISIBLE);
            startButton.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            infoButton.setVisibility(View.VISIBLE);

            startUIAnimations();
        }
    }

    /**
     * Executes the start up animations
     */
    private void startUIAnimations() {
        Animation forwardsAppear = AnimationUtils.loadAnimation(this, R.anim.forwards_appear);
        infoButton = findViewById(R.id.info_button);
        infoButton.startAnimation(forwardsAppear);

        Animation rightwardsAppear = AnimationUtils.loadAnimation(this, R.anim.rightwards_appear);
        montyImageView = findViewById(R.id.start_gif);
        montyImageView.startAnimation(rightwardsAppear);

        Animation leftwardsAppear = AnimationUtils.loadAnimation(this, R.anim.leftwards_appear);
        startButton = findViewById(R.id.start_button);
        startButton.startAnimation(leftwardsAppear);

        Animation downwardsAppear = AnimationUtils.loadAnimation(this, R.anim.downwards_appear);
        textView = findViewById(R.id.start_text);
        textView.startAnimation(downwardsAppear);
    }
}