package com.example.robert.facebookrecipes.recipeList;

import com.example.robert.facebookrecipes.entities.Recipe;

/**
 * Created by robert on 18.8.2017.
 */

public class StoredRecipesInteractorImpl implements StoredRecipesInteractor {
    private RecipeListRepository repository;

    public StoredRecipesInteractorImpl(RecipeListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeUpdate(Recipe recipe) {
        repository.updateRecipe(recipe);
    }

    @Override
    public void executeDelete(Recipe recipe) {
        repository.removeRecipe(recipe);
    }
}
