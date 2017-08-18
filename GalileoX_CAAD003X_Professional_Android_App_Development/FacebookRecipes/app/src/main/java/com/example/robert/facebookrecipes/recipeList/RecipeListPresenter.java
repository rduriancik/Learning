package com.example.robert.facebookrecipes.recipeList;

import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.recipeList.events.RecipeListEvent;
import com.example.robert.facebookrecipes.recipeList.ui.RecipeListView;

/**
 * Created by robert on 18.8.2017.
 */

public interface RecipeListPresenter {
    void onCreate();

    void onDestroy();

    void getRecipes();

    void removeRecipe(Recipe recipe);

    void toggleFavorite(Recipe recipe);

    void onEventMainThread(RecipeListEvent event);

    RecipeListView getView();

}
