package com.example.robert.facebookrecipes.recipeList;

import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.libs.base.EventBus;
import com.example.robert.facebookrecipes.recipeList.events.RecipeListEvent;
import com.example.robert.facebookrecipes.recipeList.ui.RecipeListView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by robert on 18.8.2017.
 */

public class RecipeListPresenterImpl implements RecipeListPresenter {

    private EventBus eventBus;
    private RecipeListView view;
    private RecipeListInteractor listInteractor;
    private StoredRecipesInteractor storedRecipesInteractor;

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void getRecipes() {
        listInteractor.execute();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        storedRecipesInteractor.executeDelete(recipe);
    }

    @Override
    public void toggleFavorite(Recipe recipe) {
        boolean fav = recipe.isFavorite();
        recipe.setFavorite(!fav);
        storedRecipesInteractor.executeUpdate(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeListEvent event) {
        if (this.view != null) {
            switch (event.getType()) {
                case RecipeListEvent.READ_EVENT:
                    view.setRecipes(event.getRecipeList());
                    break;
                case RecipeListEvent.UPDATE_EVENT:
                    view.recipeUpdated();
                    break;
                case RecipeListEvent.DELETE_EVENT:
                    Recipe recipe = event.getRecipeList().get(0);
                    view.recipeDeleted(recipe);
                    break;
            }
        }
    }

    @Override
    public RecipeListView getView() {
        return this.view;
    }
}
