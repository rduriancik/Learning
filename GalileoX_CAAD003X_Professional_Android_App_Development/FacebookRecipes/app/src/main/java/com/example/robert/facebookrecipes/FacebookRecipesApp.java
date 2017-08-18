package com.example.robert.facebookrecipes;

import android.app.Application;
import android.content.Intent;

import com.example.robert.facebookrecipes.libs.di.LibsModule;
import com.example.robert.facebookrecipes.login.ui.LoginActivity;
import com.example.robert.facebookrecipes.recipeList.adapters.OnItemClickListener;
import com.example.robert.facebookrecipes.recipeList.di.DaggerRecipeListComponent;
import com.example.robert.facebookrecipes.recipeList.di.RecipeListComponent;
import com.example.robert.facebookrecipes.recipeList.di.RecipeListModule;
import com.example.robert.facebookrecipes.recipeList.ui.RecipeListActivity;
import com.example.robert.facebookrecipes.recipeList.ui.RecipeListView;
import com.example.robert.facebookrecipes.recipeMain.di.DaggerRecipeMainComponent;
import com.example.robert.facebookrecipes.recipeMain.di.RecipeMainComponent;
import com.example.robert.facebookrecipes.recipeMain.di.RecipeMainModule;
import com.example.robert.facebookrecipes.recipeMain.ui.RecipeMainActivity;
import com.example.robert.facebookrecipes.recipeMain.ui.RecipeMainView;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by robert on 3.8.2017.
 */

public class FacebookRecipesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFacebook();
        initDB();
    }

    @Override
    public void onTerminate() {
        DBTearDown();
        super.onTerminate();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initDB() {
        FlowManager.init(this);
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(this);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public RecipeMainComponent getRecipeMainComponent(RecipeMainActivity activity, RecipeMainView view) {
        return DaggerRecipeMainComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeMainModule(new RecipeMainModule(view))
                .build();
    }

    public RecipeListComponent getRecipeListComponent(RecipeListActivity activity, RecipeListView view, OnItemClickListener listener) {
        return DaggerRecipeListComponent.builder()
                .libsModule(new LibsModule(activity))
                .recipeListModule(new RecipeListModule(view, listener))
                .build();
    }
}
