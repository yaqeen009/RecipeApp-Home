package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.testapp.Adapters.PopularRecipeAdapter;
import com.example.testapp.Listeners.RandomRecipeResponseListener;
import com.example.testapp.Models.RandomRecipeApiResponse;

public class PopularRecipeActivity extends AppCompatActivity {
    ProgressDialog dialog;
    RequestManager manager;
    PopularRecipeAdapter popularRecipeAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_recipe);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener);
        dialog.show();


    }

    private final RandomRecipeResponseListener randomRecipeResponseListener= new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.hide();
            recyclerView = findViewById(R.id.popular_Recipe_Recycler2);
            recyclerView.hasFixedSize();
            recyclerView.setLayoutManager(new GridLayoutManager(PopularRecipeActivity.this,2 ));
            popularRecipeAdapter = new PopularRecipeAdapter(PopularRecipeActivity.this, response.recipes);
            popularRecipeAdapter.setOnClickListener((position, recipe) -> Toast.makeText(getApplicationContext(), "item clicked", Toast.LENGTH_SHORT).show());
            recyclerView.setAdapter(popularRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            dialog.hide();
            Toast.makeText(PopularRecipeActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}