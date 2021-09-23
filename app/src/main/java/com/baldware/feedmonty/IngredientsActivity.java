package com.baldware.feedmonty;

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

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animation forwardsAppear = AnimationUtils.loadAnimation(IngredientsActivity.this, R.anim.forwards_appear);

                if(imageViewOne.getVisibility() == View.INVISIBLE) {
                    imageViewOne.setImageResource((Integer)gridViewAdapter.getItem(i));
                    imageViewOne.startAnimation(forwardsAppear);
                    imageViewOne.setVisibility(View.VISIBLE);
                } else if(imageViewTwo.getVisibility() == View.INVISIBLE) {
                    imageViewTwo.setImageResource((Integer)gridViewAdapter.getItem(i));
                    imageViewTwo.startAnimation(forwardsAppear);
                    imageViewTwo.setVisibility(View.VISIBLE);
                } else if(imageViewThree.getVisibility() == View.INVISIBLE) {
                    imageViewThree.setImageResource((Integer)gridViewAdapter.getItem(i));
                    imageViewThree.startAnimation(forwardsAppear);
                    imageViewThree.setVisibility(View.VISIBLE);
                }

                gridViewAdapter.removeItemAt(i);
            }
        });

        // UI Animation
        Animation leftwardsAppear = AnimationUtils.loadAnimation(this, R.anim.leftwards_appear);
        gridView.startAnimation(leftwardsAppear);
    }
}
