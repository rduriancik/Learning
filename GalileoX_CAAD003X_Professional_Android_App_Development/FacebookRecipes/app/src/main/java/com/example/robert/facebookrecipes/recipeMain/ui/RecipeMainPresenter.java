package com.example.robert.facebookrecipes.recipeMain.ui;

import com.example.robert.facebookrecipes.recipeMain.RecipeMainView;
import com.example.robert.facebookrecipes.recipeMain.events.RecipeMainEvent;

/**
 * Created by robert on 9.8.2017.
 */

public interface RecipeMainPresenter {
    void onCreate();

    void onDestroy();

    void dismissRecipe();

    void getNextRecipe();

    void saveRecipe();

    void onEventMainThread(RecipeMainEvent event);

    RecipeMainView getView();
}
