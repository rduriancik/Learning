package com.example.robert.facebookrecipes.api;

import com.example.robert.facebookrecipes.entities.Recipe;

import java.util.List;

/**
 * Created by robert on 3.8.2017.
 */

public class RecipeSearchResponse {
    private int count;
    private List<Recipe> recipes;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Recipe getFirstRecipe() {
        Recipe first = null;
        if (!recipes.isEmpty()) {
            first = recipes.get(0);
        }

        return first;
    }

    @Override
    public String toString() {
        return "RecipeSearchResponse{" +
                "count=" + count +
                ", recipes=" + recipes +
                '}';
    }
}
