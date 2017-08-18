package com.example.robert.facebookrecipes.recipeList;

/**
 * Created by robert on 18.8.2017.
 */

public class RecipeListInteractorImpl implements RecipeListInteractor {

    private RecipeListRepository recipeListRepository;

    public RecipeListInteractorImpl(RecipeListRepository recipeListRepository) {
        this.recipeListRepository = recipeListRepository;
    }

    @Override
    public void execute() {
        recipeListRepository.getSavedRecipes();
    }
}
