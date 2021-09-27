package com.baldware.feedmonty;

import android.content.Intent;
import android.media.Image;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        // Activity Properties
        setTitle("Select three ingredients!");

        // Ingredients Grid View Populating
        ArrayList<Integer> ingredientsImageIDList = new ArrayList<>();
        ingredientsImageIDList.add(R.drawable.food_apple);
        ingredientsImageIDList.add(R.drawable.food_avocado);
        ingredientsImageIDList.add(R.drawable.food_burger);
        ingredientsImageIDList.add(R.drawable.food_cake);
        ingredientsImageIDList.add(R.drawable.food_candy);
        ingredientsImageIDList.add(R.drawable.food_cherry);
        ingredientsImageIDList.add(R.drawable.food_cupcake);
        ingredientsImageIDList.add(R.drawable.food_fish);
        ingredientsImageIDList.add(R.drawable.food_flask);
        ingredientsImageIDList.add(R.drawable.food_icecream);
        ingredientsImageIDList.add(R.drawable.food_monty);
        ingredientsImageIDList.add(R.drawable.food_pizza);
        ingredientsImageIDList.add(R.drawable.food_potion);
        ingredientsImageIDList.add(R.drawable.food_rose);
        ingredientsImageIDList.add(R.drawable.food_shoe);
        ingredientsImageIDList.add(R.drawable.food_shrimp);
        ingredientsImageIDList.add(R.drawable.food_stone);
        ingredientsImageIDList.add(R.drawable.food_sushi);
        ingredientsImageIDList.add(R.drawable.food_tea);

        GridView gridView = findViewById(R.id.grid_view);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, ingredientsImageIDList);
        gridView.setAdapter(gridViewAdapter);

        ImageView imageViewOne = findViewById(R.id.image_view_one);
        ImageView imageViewTwo = findViewById(R.id.image_view_two);
        ImageView imageViewThree = findViewById(R.id.image_view_three);

        ImageView imageViewOneEmpty = findViewById(R.id.image_view_one_empty);
        ImageView imageViewTwoEmpty = findViewById(R.id.image_view_two_empty);
        ImageView imageViewThreeEmpty = findViewById(R.id.image_view_three_empty);

        int[] chosenImageIDArray = new int[3];

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animation forwardsAppear = AnimationUtils.loadAnimation(IngredientsActivity.this, R.anim.forwards_appear);

                if(imageViewOne.getVisibility() == View.INVISIBLE) {
                    chosenImageIDArray[0] = (Integer)gridViewAdapter.getItem(i);
                    imageViewOne.setImageResource(chosenImageIDArray[0]);
                    imageViewOne.startAnimation(forwardsAppear);
                    imageViewOne.setVisibility(View.VISIBLE);
                } else if(imageViewTwo.getVisibility() == View.INVISIBLE) {
                    chosenImageIDArray[1] = (Integer)gridViewAdapter.getItem(i);
                    imageViewTwo.setImageResource(chosenImageIDArray[1]);
                    imageViewTwo.startAnimation(forwardsAppear);
                    imageViewTwo.setVisibility(View.VISIBLE);
                } else if(imageViewThree.getVisibility() == View.INVISIBLE) {
                    chosenImageIDArray[2] = (Integer)gridViewAdapter.getItem(i);
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
                            imageViewOneEmpty.startAnimation(leftwardsDisappear);
                            imageViewTwo.startAnimation(leftwardsDisappear);
                            imageViewTwoEmpty.startAnimation(leftwardsDisappear);
                            imageViewThree.startAnimation(leftwardsDisappear);
                            imageViewThreeEmpty.startAnimation(leftwardsDisappear);
                            leftwardsDisappear.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    gridView.setVisibility(View.INVISIBLE);
                                    imageViewOne.setVisibility(View.INVISIBLE);
                                    imageViewOneEmpty.setVisibility(View.INVISIBLE);
                                    imageViewTwo.setVisibility(View.INVISIBLE);
                                    imageViewTwoEmpty.setVisibility(View.INVISIBLE);
                                    imageViewThree.setVisibility(View.INVISIBLE);
                                    imageViewThreeEmpty.setVisibility(View.INVISIBLE);

                                    // TODO: Start new activity that fades in leftwards
                                    // and then lets the icons "fall" into the bottle while the bottle is being animated
                                    // Parse the three ingredients by using intent extras
                                    Intent intent = new Intent(IngredientsActivity.this, MixingActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    intent.putExtra("chosen_ingredients", chosenImageIDArray);
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

        // UI Animation
        Animation leftwardsAppear = AnimationUtils.loadAnimation(this, R.anim.leftwards_appear);
        gridView.startAnimation(leftwardsAppear);

        Animation upwardsAppearOne = AnimationUtils.loadAnimation(this, R.anim.upwards_appear);
        Animation upwardsAppearTwo = AnimationUtils.loadAnimation(this, R.anim.upwards_appear);
        Animation upwardsAppearThree = AnimationUtils.loadAnimation(this, R.anim.upwards_appear);

        upwardsAppearOne.setStartOffset(500);
        imageViewOneEmpty.startAnimation(upwardsAppearOne);
        upwardsAppearTwo.setStartOffset(750);
        imageViewTwoEmpty.startAnimation(upwardsAppearTwo);
        upwardsAppearThree.setStartOffset(1000);
        imageViewThreeEmpty.startAnimation(upwardsAppearThree);
    }
}
