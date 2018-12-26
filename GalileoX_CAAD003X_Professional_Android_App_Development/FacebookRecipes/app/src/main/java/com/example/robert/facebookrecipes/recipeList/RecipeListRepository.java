package com.example.robert.facebookrecipes.recipeList;

import com.example.robert.facebookrecipes.entities.Recipe;

/**
 * Created by robert on 18.8.2017.
 */

public interface RecipeListRepository {
    void getSavedRecipes();

    void updateRecipe(Recipe recipe);

    void removeRecipe(Recipe recipe);
}
