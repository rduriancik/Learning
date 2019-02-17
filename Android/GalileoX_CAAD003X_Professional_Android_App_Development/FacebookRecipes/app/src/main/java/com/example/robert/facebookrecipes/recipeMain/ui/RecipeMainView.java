package com.example.robert.facebookrecipes.recipeMain.ui;

import com.example.robert.facebookrecipes.entities.Recipe;

/**
 * Created by robert on 9.8.2017.
 */

public interface RecipeMainView {
    void showProgress();

    void hideProgress();

    void showUIElements();

    void hideUIElements();

    void saveAnimation();

    void dismissAnimation();

    void onRecipeSaved();

    void setRecipe(Recipe recipe);

    void onGetRecipeError(String error);
}
