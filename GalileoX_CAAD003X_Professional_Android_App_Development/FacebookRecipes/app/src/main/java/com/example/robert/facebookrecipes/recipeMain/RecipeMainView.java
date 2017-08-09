package com.example.robert.facebookrecipes.recipeMain;

import com.example.robert.facebookrecipes.entities.Recipe;

/**
 * Created by robert on 9.8.2017.
 */

public interface RecipeMainView {
    void showProgress();

    void hideProgress();

    void showUIElement();

    void hideUIElement();

    void saveAnimation();

    void dismissAnimation();

    void onRecipeSaved();

    void setRecipe(Recipe recipe);

    void onGetRecipeError(String error);
}
