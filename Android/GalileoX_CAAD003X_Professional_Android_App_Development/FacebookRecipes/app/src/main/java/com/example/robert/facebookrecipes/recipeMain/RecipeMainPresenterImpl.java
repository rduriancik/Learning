package com.example.robert.facebookrecipes.recipeMain;

import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.libs.base.EventBus;
import com.example.robert.facebookrecipes.recipeMain.events.RecipeMainEvent;
import com.example.robert.facebookrecipes.recipeMain.ui.RecipeMainView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by robert on 15.8.2017.
 */

public class RecipeMainPresenterImpl implements RecipeMainPresenter {
    private EventBus eventBus;
    private RecipeMainView view;
    SaveRecipeInteractor saveRecipeInteractor;
    GetNextRecipeInteractor getNextRecipeInteractor;

    public RecipeMainPresenterImpl(EventBus eventBus, RecipeMainView view, SaveRecipeInteractor saveRecipeInteractor, GetNextRecipeInteractor getNextRecipeInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.saveRecipeInteractor = saveRecipeInteractor;
        this.getNextRecipeInteractor = getNextRecipeInteractor;
    }

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
    public void dismissRecipe() {
        if (this.view != null) {
            view.dismissAnimation();
        }

        getNextRecipe();
    }

    @Override
    public void getNextRecipe() {
        if (this.view != null) {
            view.hideUIElements();
            view.showProgress();
        }
        getNextRecipeInteractor.execute();
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        if (this.view != null) {
            view.saveAnimation();
            view.hideUIElements();
            view.showProgress();
        }
        saveRecipeInteractor.execute(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeMainEvent event) {
        if (this.view != null) {
            String error = event.getError();
            if (error != null) {
                view.hideProgress();
                view.onGetRecipeError(error);
            } else {
                if (event.getType() == RecipeMainEvent.NEXT_EVENT) {
                    view.setRecipe(event.getRecipe());
                } else if (event.getType() == RecipeMainEvent.SAVE_EVENT) {
                    view.onRecipeSaved();
                    getNextRecipeInteractor.execute();
                }
            }
        }
    }

    @Override
    public void imageReady() {
        if (view != null) {
            view.hideProgress();
            view.showUIElements();
        }
    }

    @Override
    public void imageError(String error) {
        if (view != null) {
            view.onGetRecipeError(error);
        }
    }

    @Override
    public RecipeMainView getView() {
        return this.view;
    }
}
