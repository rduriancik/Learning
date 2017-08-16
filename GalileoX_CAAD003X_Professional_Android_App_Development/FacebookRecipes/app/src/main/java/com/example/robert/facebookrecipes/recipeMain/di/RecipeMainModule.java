package com.example.robert.facebookrecipes.recipeMain.di;

import com.example.robert.facebookrecipes.api.RecipeClient;
import com.example.robert.facebookrecipes.api.RecipeService;
import com.example.robert.facebookrecipes.libs.base.EventBus;
import com.example.robert.facebookrecipes.recipeMain.GetNextRecipeInteractor;
import com.example.robert.facebookrecipes.recipeMain.GetNextRecipeInteractorImpl;
import com.example.robert.facebookrecipes.recipeMain.RecipeMainPresenter;
import com.example.robert.facebookrecipes.recipeMain.RecipeMainPresenterImpl;
import com.example.robert.facebookrecipes.recipeMain.RecipeMainRepository;
import com.example.robert.facebookrecipes.recipeMain.RecipeMainRepositoryImpl;
import com.example.robert.facebookrecipes.recipeMain.SaveRecipeInteractor;
import com.example.robert.facebookrecipes.recipeMain.SaveRecipeInteractorImpl;
import com.example.robert.facebookrecipes.recipeMain.ui.RecipeMainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 16.8.2017.
 */

@Module
public class RecipeMainModule {
    RecipeMainView view;

    public RecipeMainModule(RecipeMainView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    RecipeMainView provideRecipeMainView() {
        return this.view;
    }

    @Provides
    @Singleton
    RecipeMainPresenter provideRecipeMainPresenter(EventBus eventBus, RecipeMainView view, SaveRecipeInteractor saveRecipeInteractor, GetNextRecipeInteractor getNextRecipeInteractor) {
        return new RecipeMainPresenterImpl(eventBus, view, saveRecipeInteractor, getNextRecipeInteractor);
    }

    @Provides
    @Singleton
    SaveRecipeInteractor provideSaveRecipeInteractor(RecipeMainRepository repository) {
        return new SaveRecipeInteractorImpl(repository);
    }

    @Provides
    @Singleton
    GetNextRecipeInteractor provideGetNextRecipeInteractor(RecipeMainRepository repository) {
        return new GetNextRecipeInteractorImpl(repository);
    }

    @Provides
    @Singleton
    RecipeMainRepository provideRecipeMainRepository(EventBus eventBus, RecipeService service) {
        return new RecipeMainRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    RecipeService provideRecipeService() {
        return new RecipeClient().getRecipeService();
    }
}
