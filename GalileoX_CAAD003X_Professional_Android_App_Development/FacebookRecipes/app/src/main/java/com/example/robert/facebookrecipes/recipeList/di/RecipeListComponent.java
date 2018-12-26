package com.example.robert.facebookrecipes.recipeList.di;

import com.example.robert.facebookrecipes.libs.di.LibsModule;
import com.example.robert.facebookrecipes.recipeList.RecipeListPresenter;
import com.example.robert.facebookrecipes.recipeList.adapters.RecipesAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 16.8.2017.
 */

@Singleton
@Component(modules = {RecipeListModule.class, LibsModule.class})
public interface RecipeListComponent {
    RecipeListPresenter getPresenter();

    RecipesAdapter getAdapter();
}
