package com.example.robert.facebookrecipes.recipeMain.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.robert.facebookrecipes.R;
import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.libs.base.ImageLoader;
import com.example.robert.facebookrecipes.recipeMain.RecipeMainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeMainActivity extends AppCompatActivity implements RecipeMainView {

    @BindView(R.id.imgRecipe)
    ImageView imgRecipe;
    @BindView(R.id.imgDismiss)
    ImageButton imgDismiss;
    @BindView(R.id.imgKeep)
    ImageButton imgKeep;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layoutContainer)
    RelativeLayout layoutContainer;

    private RecipeMainPresenter presenter;
    private Recipe currentRecipe;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main);
        ButterKnife.bind(this);

        setupInjection();
        presenter.onCreate();
        presenter.getNextRecipe();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUIElements() {
        imgKeep.setVisibility(View.VISIBLE);
        imgDismiss.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUIElements() {
        imgKeep.setVisibility(View.GONE);
        imgDismiss.setVisibility(View.GONE);
    }

    @Override
    public void saveAnimation() {

    }

    @Override
    public void dismissAnimation() {

    }

    @OnClick(R.id.imgKeep)
    public void onKeep() {
        if (currentRecipe != null) {
            presenter.saveRecipe(currentRecipe);
        }
    }

    @OnClick(R.id.imgDismiss)
    public void onDismiss() {
        presenter.dismissRecipe();
    }

    @Override
    public void onRecipeSaved() {
        Snackbar.make(layoutContainer, R.string.recipes_main_notice_saved, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setRecipe(Recipe recipe) {
        this.currentRecipe = recipe;
        imageLoader.load(imgRecipe, recipe.getImageURL());
    }

    @Override
    public void onGetRecipeError(String error) {
        String msgError = String.format(getString(R.string.recipes_main_error), error);
        Snackbar.make(layoutContainer, msgError, Snackbar.LENGTH_SHORT).show();
    }
}
