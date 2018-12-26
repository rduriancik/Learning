package com.example.robert.facebookrecipes.recipeList.adapters;

import com.example.robert.facebookrecipes.entities.Recipe;

/**
 * Created by robert on 18.8.2017.
 */

public interface OnItemClickListener {
    void onFavClick(Recipe recipe);

    void onItemClick(Recipe recipe);

    void onDeleteClick(Recipe recipe);
}
