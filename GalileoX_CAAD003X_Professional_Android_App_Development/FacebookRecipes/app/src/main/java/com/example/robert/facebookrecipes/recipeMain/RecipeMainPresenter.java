package com.example.robert.facebookrecipes.recipeMain;

import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.recipeMain.events.RecipeMainEvent;
import com.example.robert.facebookrecipes.recipeMain.ui.RecipeMainView;

/**
 * Created by robert on 9.8.2017.
 */

public interface RecipeMainPresenter {
    void onCreate();

    void onDestroy();

    void dismissRecipe();

    void getNextRecipe();

    void saveRecipe(Recipe recipe);

    void onEventMainThread(RecipeMainEvent event);

    RecipeMainView getView();
}
