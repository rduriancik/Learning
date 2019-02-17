package com.example.robert.facebookrecipes.recipeList.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.robert.facebookrecipes.FacebookRecipesApp;
import com.example.robert.facebookrecipes.R;
import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.recipeList.RecipeListPresenter;
import com.example.robert.facebookrecipes.recipeList.adapters.OnItemClickListener;
import com.example.robert.facebookrecipes.recipeList.adapters.RecipesAdapter;
import com.example.robert.facebookrecipes.recipeList.di.RecipeListComponent;
import com.example.robert.facebookrecipes.recipeMain.ui.RecipeMainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeListActivity extends AppCompatActivity implements RecipeListView, OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecipesAdapter adapter;
    private RecipeListPresenter presenter;
    private RecipeListComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);

        setupInjection();
        setupToolbar();
        setupRecyclerView();

        presenter.onCreate();
        presenter.getRecipes();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipes_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_main:
                navigateToMainScreen();
                break;
            case R.id.action_logout:
                logout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        app.logout();
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this, RecipeMainActivity.class));
    }


    private void setupInjection() {
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        component = app.getRecipeListComponent(this, this, this);
        presenter = getPresenter();
        adapter = getAdapter();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.toolbar)
    public void onToolbarClick() {
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void setRecipes(List<Recipe> data) {
        adapter.setRecipes(data);
    }

    @Override
    public void recipeUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void recipeDeleted(Recipe recipe) {
        adapter.removeRecipes(recipe);
    }

    @Override
    public void onFavClick(Recipe recipe) {
        presenter.toggleFavorite(recipe);
    }

    @Override
    public void onItemClick(Recipe recipe) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getSourceURL()));
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Recipe recipe) {
        presenter.removeRecipe(recipe);
    }

    public RecipeListPresenter getPresenter() {
        return component.getPresenter();
    }

    public RecipesAdapter getAdapter() {
        return component.getAdapter();
    }
}
