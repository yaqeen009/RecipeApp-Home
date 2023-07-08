package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.Adapters.PopularRecipeAdapter;
import com.example.testapp.Listeners.RandomRecipeResponseListener;
import com.example.testapp.Models.RandomRecipeApiResponse;
import com.example.testapp.Models.Recipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;
    PopularRecipeAdapter popularRecipeAdapter;
    RecyclerView recyclerView;
    Button recipeMore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener);
        dialog.show();

        recipeMore = findViewById(R.id.popular_recipe_more);
        recipeMore.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext() , PopularRecipeActivity.class);
            startActivity(intent);

        });
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener= new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.hide();
            recyclerView = findViewById(R.id.popular_Recipe_Recycler);
            recyclerView.hasFixedSize();
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false ));
            popularRecipeAdapter = new PopularRecipeAdapter(MainActivity.this, response.recipes);
            popularRecipeAdapter.setOnClickListener((position, recipe) -> Toast.makeText(getApplicationContext(), "item clicked", Toast.LENGTH_SHORT).show());
            recyclerView.setAdapter(popularRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            dialog.hide();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}