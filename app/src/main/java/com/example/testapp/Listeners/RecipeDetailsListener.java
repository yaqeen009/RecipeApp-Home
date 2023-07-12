package com.example.testapp.Listeners;

import com.example.testapp.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
