package com.example.robert.facebookrecipes.recipeList.di;

import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.libs.base.EventBus;
import com.example.robert.facebookrecipes.libs.base.ImageLoader;
import com.example.robert.facebookrecipes.recipeList.RecipeListInteractor;
import com.example.robert.facebookrecipes.recipeList.RecipeListInteractorImpl;
import com.example.robert.facebookrecipes.recipeList.RecipeListPresenter;
import com.example.robert.facebookrecipes.recipeList.RecipeListPresenterImpl;
import com.example.robert.facebookrecipes.recipeList.RecipeListRepository;
import com.example.robert.facebookrecipes.recipeList.RecipeListRepositoryImpl;
import com.example.robert.facebookrecipes.recipeList.StoredRecipesInteractor;
import com.example.robert.facebookrecipes.recipeList.StoredRecipesInteractorImpl;
import com.example.robert.facebookrecipes.recipeList.adapters.OnItemClickListener;
import com.example.robert.facebookrecipes.recipeList.adapters.RecipesAdapter;
import com.example.robert.facebookrecipes.recipeList.ui.RecipeListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 16.8.2017.
 */

@Module
public class RecipeListModule {
    RecipeListView view;
    OnItemClickListener clickListener;

    public RecipeListModule(RecipeListView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    RecipeListView provideRecipeListView() {
        return this.view;
    }

    @Provides
    @Singleton
    RecipeListPresenter provideRecipeListPresenter(EventBus eventBus, RecipeListView view, StoredRecipesInteractor storedRecipesInteractor, RecipeListInteractor recipeListInteractor) {
        return new RecipeListPresenterImpl(eventBus, view, recipeListInteractor, storedRecipesInteractor);
    }

    @Provides
    @Singleton
    StoredRecipesInteractor provideStoredRecipesListInteractor(RecipeListRepository repository) {
        return new StoredRecipesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    RecipeListInteractor provideRecipeListInteractor(RecipeListRepository repository) {
        return new RecipeListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    RecipeListRepository provideRecipeListRepository(EventBus eventBus) {
        return new RecipeListRepositoryImpl(eventBus);
    }

    @Provides
    @Singleton
    RecipesAdapter provideRecipesAdapter(List<Recipe> recipes, ImageLoader imageLoader, OnItemClickListener listener) {
        return new RecipesAdapter(recipes, imageLoader, listener);
    }

    @Provides
    @Singleton
    OnItemClickListener provideOnItemClickListener() {
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Recipe> provideEmptyList() {
        return new ArrayList<Recipe>();
    }
}
