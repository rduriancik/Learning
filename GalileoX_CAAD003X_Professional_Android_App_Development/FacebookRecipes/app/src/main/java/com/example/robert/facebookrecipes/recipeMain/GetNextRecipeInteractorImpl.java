package com.example.robert.facebookrecipes.recipeMain;

import java.util.Random;

/**
 * Created by robert on 15.8.2017.
 */

public class GetNextRecipeInteractorImpl implements GetNextRecipeInteractor {
    RecipeMainRepository recipeMainRepository;

    public GetNextRecipeInteractorImpl(RecipeMainRepository recipeMainRepository) {
        this.recipeMainRepository = recipeMainRepository;
    }

    @Override
    public void execute() {
        int recipePage = new Random().nextInt(RecipeMainRepository.RECIPE_RANGE);
        recipeMainRepository.setRecipePage(recipePage);
        recipeMainRepository.getNextRecipe();
    }
}
