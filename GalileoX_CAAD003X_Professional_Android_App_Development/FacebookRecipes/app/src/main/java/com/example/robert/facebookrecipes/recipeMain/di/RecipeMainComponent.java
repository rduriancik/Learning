package com.example.robert.facebookrecipes.recipeMain.di;

import com.example.robert.facebookrecipes.libs.base.ImageLoader;
import com.example.robert.facebookrecipes.libs.di.LibsModule;
import com.example.robert.facebookrecipes.recipeMain.RecipeMainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 16.8.2017.
 */

@Singleton
@Component(modules = {RecipeMainModule.class, LibsModule.class})
public interface RecipeMainComponent {
    //    void inject(RecipeMainActivity recipeMainActivity);
    ImageLoader getImageLoader();

    RecipeMainPresenter getPresenter();
}
