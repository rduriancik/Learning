package com.example.robert.facebookrecipes.recipeList.events;

import com.example.robert.facebookrecipes.entities.Recipe;

import java.util.List;

/**
 * Created by robert on 18.8.2017.
 */

public class RecipeListEvent {

    private int type;
    private List<Recipe> recipeList;
    private String error;

    public static final int READ_EVENT = 0;
    public static final int UPDATE_EVENT = 1;
    public static final int DELETE_EVENT = 2;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
