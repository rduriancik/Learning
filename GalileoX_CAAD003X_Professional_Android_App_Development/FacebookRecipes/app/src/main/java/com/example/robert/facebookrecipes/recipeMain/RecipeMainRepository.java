package com.example.robert.facebookrecipes.recipeMain;

import com.example.robert.facebookrecipes.entities.Recipe;

/**
 * Created by robert on 9.8.2017.
 */

public interface RecipeMainRepository {
    public static final int COUNT = 1;
    public static final String RECENT_SORT = "r";
    public static final int RECIPE_RANGE = 10000;

    void getNextRecipe();

    void saveRecipe(Recipe recipe);

    void setRecipePage(int recipePage);
}
