package com.baldware.feedmonty.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.baldware.feedmonty.Fragments.HintDialogFragment;
import com.baldware.feedmonty.Utils.GridViewAdapter;
import com.baldware.feedmonty.R;
import com.baldware.feedmonty.Utils.HistoryHandler;

import java.util.ArrayList;

public class IngredientsActivity extends FullScreenActivity {

    // Tags & Keys
    public final static String INGREDIENTS_HINT_KEY = "ingredients_hint";
    private static final String INGREDIENTS_HINT_FRAGMENT_TAG = "ingredients_hint_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemBars();
        setContentView(R.layout.activity_ingredients);

        // Showing the ingredients hint dialog fragment on first start up
        HistoryHandler historyHandler = new HistoryHandler(this, MainActivity.HISTORY_FILE_TAG);
        boolean ingredientsHintRead = historyHandler.getEntryBoolean(INGREDIENTS_HINT_KEY, HistoryHandler.Category.SETTINGS, false);
        if (!ingredientsHintRead) {
            HintDialogFragment hintDialogFragment = HintDialogFragment.newInstance(INGREDIENTS_HINT_KEY, getString(R.string.ingredients_hint_text));
            hintDialogFragment.show(IngredientsActivity.this.getSupportFragmentManager(), INGREDIENTS_HINT_FRAGMENT_TAG);
        }

        // Activity Properties
        setTitle(R.string.ingredients_activity_title);

        // Creating the ingredients resource list (image id list)
        ArrayList<Integer> ingredientsImageIDList = new ArrayList<>();
        ingredientsImageIDList.add(R.drawable.food_apple);
        ingredientsImageIDList.add(R.drawable.food_pizza);
        ingredientsImageIDList.add(R.drawable.food_shrimp);
        ingredientsImageIDList.add(R.drawable.food_cake);
        ingredientsImageIDList.add(R.drawable.food_icecream);
        ingredientsImageIDList.add(R.drawable.food_cherry);
        ingredientsImageIDList.add(R.drawable.food_candy);
        ingredientsImageIDList.add(R.drawable.food_burger);
        ingredientsImageIDList.add(R.drawable.food_cupcake);
        ingredientsImageIDList.add(R.drawable.food_tea);
        ingredientsImageIDList.add(R.drawable.food_sushi);
        ingredientsImageIDList.add(R.drawable.food_avocado);
        ingredientsImageIDList.add(R.drawable.food_fish);
        ingredientsImageIDList.add(R.drawable.food_monty);
        ingredientsImageIDList.add(R.drawable.food_shoe);
        ingredientsImageIDList.add(R.drawable.food_flask);
        ingredientsImageIDList.add(R.drawable.food_potion);
        ingredientsImageIDList.add(R.drawable.food_stone);
        ingredientsImageIDList.add(R.drawable.food_rose);

        // Creating the ingredients value list
        ArrayList<Integer> ingredientsValueList = new ArrayList<>();

        ingredientsValueList.add(2);
        ingredientsValueList.add(-10);
        ingredientsValueList.add(8);
        ingredientsValueList.add(6);
        ingredientsValueList.add(10);
        ingredientsValueList.add(4);
        ingredientsValueList.add(12);
        ingredientsValueList.add(14);
        ingredientsValueList.add(20);
        ingredientsValueList.add(16);
        ingredientsValueList.add(18);
        ingredientsValueList.add(0);
        ingredientsValueList.add(24);
        ingredientsValueList.add(29);
        ingredientsValueList.add(30);
        ingredientsValueList.add(28);
        ingredientsValueList.add(31);
        ingredientsValueList.add(26);
        ingredientsValueList.add(22);

        GridView gridView = findViewById(R.id.grid_view);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, ingredientsImageIDList, ingredientsValueList);
        gridView.setAdapter(gridViewAdapter);

        ImageView imageViewOne = findViewById(R.id.image_view_one);
        ImageView imageViewTwo = findViewById(R.id.image_view_two);
        ImageView imageViewThree = findViewById(R.id.image_view_three);

        ImageView imageViewOneBorder = findViewById(R.id.image_view_one_empty);
        ImageView imageViewTwoBorder = findViewById(R.id.image_view_two_empty);
        ImageView imageViewThreeBorder = findViewById(R.id.image_view_three_empty);

        int[] chosenImageIDArray = new int[3];
        int[] chosenValueArray = new int[3];

        // Animating the item selection
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animation forwardsAppear = AnimationUtils.loadAnimation(IngredientsActivity.this, R.anim.forwards_appear);

                if (imageViewOne.getVisibility() == View.INVISIBLE) {
                    chosenImageIDArray[0] = (Integer) gridViewAdapter.getItem(i);
                    chosenValueArray[0] = gridViewAdapter.getItemValue(i);
                    imageViewOne.setImageResource(chosenImageIDArray[0]);
                    imageViewOne.startAnimation(forwardsAppear);
                    imageViewOne.setVisibility(View.VISIBLE);
                } else if (imageViewTwo.getVisibility() == View.INVISIBLE) {
                    chosenImageIDArray[1] = (Integer) gridViewAdapter.getItem(i);
                    chosenValueArray[1] = gridViewAdapter.getItemValue(i);
                    imageViewTwo.setImageResource(chosenImageIDArray[1]);
                    imageViewTwo.startAnimation(forwardsAppear);
                    imageViewTwo.setVisibility(View.VISIBLE);
                } else if (imageViewThree.getVisibility() == View.INVISIBLE) {
                    chosenImageIDArray[2] = (Integer) gridViewAdapter.getItem(i);
                    chosenValueArray[2] = gridViewAdapter.getItemValue(i);
                    imageViewThree.setImageResource(chosenImageIDArray[2]);
                    imageViewThree.startAnimation(forwardsAppear);
                    imageViewThree.setVisibility(View.VISIBLE);

                    forwardsAppear.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Animation leftwardsDisappear = AnimationUtils.loadAnimation(IngredientsActivity.this, R.anim.leftwards_disappear);
                            gridView.startAnimation(leftwardsDisappear);
                            imageViewOne.startAnimation(leftwardsDisappear);
                            imageViewOneBorder.startAnimation(leftwardsDisappear);
                            imageViewTwo.startAnimation(leftwardsDisappear);
                            imageViewTwoBorder.startAnimation(leftwardsDisappear);
                            imageViewThree.startAnimation(leftwardsDisappear);
                            imageViewThreeBorder.startAnimation(leftwardsDisappear);
                            leftwardsDisappear.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    gridView.setVisibility(View.INVISIBLE);
                                    imageViewOne.setVisibility(View.INVISIBLE);
                                    imageViewOneBorder.setVisibility(View.INVISIBLE);
                                    imageViewTwo.setVisibility(View.INVISIBLE);
                                    imageViewTwoBorder.setVisibility(View.INVISIBLE);
                                    imageViewThree.setVisibility(View.INVISIBLE);
                                    imageViewThreeBorder.setVisibility(View.INVISIBLE);

                                    // Parse the three ingredients by using intent extras
                                    Intent intent = new Intent(IngredientsActivity.this, MixingActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    intent.putExtra("chosen_ingredients", chosenImageIDArray);
                                    intent.putExtra("chosen_value", chosenValueArray[0] + chosenValueArray[1] + chosenValueArray[2]);
                                    startActivity(intent);

                                    // Finish this activity so that when the next one opens the back button
                                    // will bring the user back to the main activity
                                    finish();
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }

                gridViewAdapter.removeItemAt(i);
            }
        });

        // Showing the UI animations on start up
        Animation leftwardsAppear = AnimationUtils.loadAnimation(this, R.anim.leftwards_appear);
        Animation upwardsAppearOne = AnimationUtils.loadAnimation(this, R.anim.upwards_appear);
        Animation upwardsAppearTwo = AnimationUtils.loadAnimation(this, R.anim.upwards_appear);
        Animation upwardsAppearThree = AnimationUtils.loadAnimation(this, R.anim.upwards_appear);

        gridView.startAnimation(leftwardsAppear);
        upwardsAppearOne.setStartOffset(500);
        imageViewOneBorder.startAnimation(upwardsAppearOne);
        upwardsAppearTwo.setStartOffset(750);
        imageViewTwoBorder.startAnimation(upwardsAppearTwo);
        upwardsAppearThree.setStartOffset(1000);
        imageViewThreeBorder.startAnimation(upwardsAppearThree);
    }
}
