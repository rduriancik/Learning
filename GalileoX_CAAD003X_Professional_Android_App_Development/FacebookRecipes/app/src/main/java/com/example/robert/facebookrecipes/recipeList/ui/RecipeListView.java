package com.example.robert.facebookrecipes.recipeList.ui;

import com.example.robert.facebookrecipes.entities.Recipe;

import java.util.List;

/**
 * Created by robert on 18.8.2017.
 */

public interface RecipeListView {
    void setRecipes(List<Recipe> data);

    void recipeUpdated();

    void recipeDeleted(Recipe recipe);
}
