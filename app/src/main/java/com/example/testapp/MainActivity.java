package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.Adapters.PopularRecipeAdapter;
import com.example.testapp.Listeners.RandomRecipeResponseListener;
import com.example.testapp.Models.RandomRecipeApiResponse;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;
    PopularRecipeAdapter popularRecipeAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener);
        dialog.show();




    }

    private final RandomRecipeResponseListener randomRecipeResponseListener= new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            recyclerView = findViewById(R.id.popular_Recipe_Recycler);
            recyclerView.hasFixedSize();
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this , 1 ));

            popularRecipeAdapter = new PopularRecipeAdapter(MainActivity.this, response.recipes);
            recyclerView.setAdapter(popularRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}