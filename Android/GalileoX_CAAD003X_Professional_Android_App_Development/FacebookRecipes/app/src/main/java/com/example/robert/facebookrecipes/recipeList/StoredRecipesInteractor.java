package com.example.robert.facebookrecipes.recipeList;

import com.example.robert.facebookrecipes.entities.Recipe;

/**
 * Created by robert on 18.8.2017.
 */

public interface StoredRecipesInteractor {
    void executeUpdate(Recipe recipe);

    void executeDelete(Recipe recipe);
}
