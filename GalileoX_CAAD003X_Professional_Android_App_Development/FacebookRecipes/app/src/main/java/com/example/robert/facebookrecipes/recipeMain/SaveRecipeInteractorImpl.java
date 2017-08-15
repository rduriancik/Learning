package com.example.robert.facebookrecipes.recipeMain;

import com.example.robert.facebookrecipes.entities.Recipe;

/**
 * Created by robert on 15.8.2017.
 */

public class SaveRecipeInteractorImpl implements SaveRecipeInteractor {
    RecipeMainRepository recipeMainRepository;

    public SaveRecipeInteractorImpl(RecipeMainRepository recipeMainRepository) {
        this.recipeMainRepository = recipeMainRepository;
    }

    @Override
    public void execute(Recipe recipe) {
        recipeMainRepository.saveRecipe(recipe);
    }
}
