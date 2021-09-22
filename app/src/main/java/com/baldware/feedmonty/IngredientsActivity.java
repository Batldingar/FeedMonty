package com.baldware.feedmonty;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IngredientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        // Activity Properties
        setTitle("Select three ingredients!");

        // Ingredients Grid View Populating
        int[] ingredientsImageIDArray = { R.drawable.food_apple, R.drawable.food_avocado, R.drawable.food_burger, R.drawable.food_cake, R.drawable.food_candy, R.drawable.food_cherry, R.drawable.food_cupcake, R.drawable.food_fish, R.drawable.food_flask, R.drawable.food_icecream, R.drawable.food_monty, R.drawable.food_pizza, R.drawable.food_potion, R.drawable.food_rose, R.drawable.food_shoe, R.drawable.food_shrimp, R.drawable.food_stone, R.drawable.food_sushi, R.drawable.food_tea };

        GridView gridView = findViewById(R.id.grid_view);
        gridView.setAdapter(new GridViewAdapter(this, ingredientsImageIDArray));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(IngredientsActivity.this, "Monty isn't hungry yet, sry!", Toast.LENGTH_SHORT).show();
            }
        });

        // UI Animation
        Animation leftwardsAppear = AnimationUtils.loadAnimation(this, R.anim.leftwards_appear);
        gridView.startAnimation(leftwardsAppear);
    }
}
