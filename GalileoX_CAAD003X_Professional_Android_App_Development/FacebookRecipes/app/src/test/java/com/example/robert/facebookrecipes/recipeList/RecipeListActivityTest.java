package com.example.robert.facebookrecipes.recipeList;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.robert.facebookrecipes.BaseTest;
import com.example.robert.facebookrecipes.BuildConfig;
import com.example.robert.facebookrecipes.R;
import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.libs.base.ImageLoader;
import com.example.robert.facebookrecipes.login.ui.LoginActivity;
import com.example.robert.facebookrecipes.recipeList.adapters.OnItemClickListener;
import com.example.robert.facebookrecipes.recipeList.adapters.RecipesAdapter;
import com.example.robert.facebookrecipes.recipeList.ui.RecipeListActivity;
import com.example.robert.facebookrecipes.recipeList.ui.RecipeListView;
import com.example.robert.facebookrecipes.recipeMain.ui.RecipeMainActivity;
import com.example.robert.facebookrecipes.support.ShadowRecyclerView;
import com.example.robert.facebookrecipes.support.ShadowRecyclerViewAdapter;
import com.facebook.FacebookActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;
import static org.robolectric.shadow.api.Shadow.extract;

/**
 * Created by robert on 11.10.2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23,
        shadows = {ShadowRecyclerView.class, ShadowRecyclerViewAdapter.class})
public class RecipeListActivityTest extends BaseTest {
    @Mock
    private RecipesAdapter adapter;
    @Mock
    private RecipeListPresenter presenter;
    @Mock
    private ImageLoader imageLoader;
    @Mock
    private List<Recipe> recipes;
    @Mock
    private Recipe recipe;

    private RecipeListView view;
    private RecipeListActivity activity;
    private OnItemClickListener onItemClickListener;

    private ShadowRecyclerViewAdapter shadowAdapter;
    private ShadowActivity shadowActivity;
    private ActivityController<RecipeListActivity> activityController;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RecipeListActivity recipeListActivity = new RecipeListActivity() {
            @Override
            public RecipeListPresenter getPresenter() {
                return presenter;
            }

            @Override
            public RecipesAdapter getAdapter() {
                return adapter;
            }

            @Override
            public void setTheme(int resid) {
                super.setTheme(R.style.AppTheme_NoActionBar);
            }
        };

        activityController = ActivityController.of(Robolectric.getShadowsAdapter(), recipeListActivity).create().visible();
        activity = activityController.get();
        view = activity;
        onItemClickListener = activity;

        shadowActivity = shadowOf(activity);
    }

    @Test
    public void testOnCreate_shouldCallPresenter() throws Exception {
        verify(presenter).onCreate();
        verify(presenter).getRecipes();
    }

    @Test
    public void testOnDestroy_shouldCallPresenter() throws Exception {
        activityController.destroy();
        verify(presenter).onDestroy();
    }

    @Test
    public void testSetRecipes_shouldSetInAdapter() throws Exception {
        view.setRecipes(recipes);
        verify(adapter).setRecipes(recipes);
    }

    @Test
    public void testRecipeUpdated_shouldUpdateAdapter() throws Exception {
        view.recipeUpdated();
        verify(adapter).notifyDataSetChanged();
    }

    @Test
    public void testRecipeDelete_shouldRemoveRecipeFromAdapter() throws Exception {
        view.recipeDeleted(recipe);
        verify(adapter).removeRecipes(recipe);
    }

    @Test
    public void testLogoutMenuClick_launchLoginActivity() throws Exception {
        shadowActivity.clickMenuItem(R.id.action_logout);
        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(new ComponentName(activity, LoginActivity.class), intent.getComponent());
    }

    @Test
    public void testMainMenuClicked_launchRecipeMainActivity() throws Exception {
        shadowActivity.clickMenuItem(R.id.action_main);
        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(new ComponentName(activity, RecipeMainActivity.class), intent.getComponent());
    }

    @Test
    public void testOnRecyclerViewScroll_shouldChangeScrollPosition() throws Exception {
        int scrollPosition = 1;

        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerView);
        ShadowRecyclerView shadowRecyclerView = extract(recyclerView);

        recyclerView.smoothScrollToPosition(scrollPosition);
        assertEquals(scrollPosition, shadowRecyclerView.getSmoothScrollPosition());
    }

    @Test
    public void testOnToolbarClicked_recyclerViewShouldScrollToTop() throws Exception {
        int scrollPosition = 1;
        int topScrollPosition = 0;

        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerView);
        ShadowRecyclerView shadowRecyclerView = extract(recyclerView);

        recyclerView.smoothScrollToPosition(scrollPosition);
        toolbar.performClick();

        assertEquals(topScrollPosition, shadowRecyclerView.getSmoothScrollPosition());
    }

    @Test
    public void testRecyclerViewItemClicked_shouldStartViewActivity() throws Exception {
        int positionToClick = 0;
        setUpShadowAdapter(positionToClick);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClick(positionToClick);

        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(Intent.ACTION_VIEW, intent.getAction());
        assertEquals(recipes.get(positionToClick).getSourceURL(), intent.getDataString());
    }

    @Test
    public void testRecyclerViewFavoriteClicked_shouldCallPresenter() throws Exception {
        int positionToClick = 0;
        setUpShadowAdapter(positionToClick);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClickOverViewInHolder(positionToClick, R.id.imgFav);

        verify(presenter).toggleFavorite(recipe);
    }

    @Test
    public void testRecyclerViewRemoveClicked_shouldCallPresenter() throws Exception {
        int positionToClick = 0;
        setUpShadowAdapter(positionToClick);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClickOverViewInHolder(positionToClick, R.id.imgDelete);

        verify(presenter).removeRecipe(recipe);
    }

    @Test
    public void testRecyclerViewFBShareClicked_shouldStartFBActivity() throws Exception {
        int positionToClick = 0;
        setUpShadowAdapter(positionToClick);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClickOverViewInHolder(positionToClick, R.id.fbShare);

        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(new ComponentName(RuntimeEnvironment.application, FacebookActivity.class), intent.getComponent());
    }

    @Test
    public void testRecyclerViewFBSendClicked_shouldStartFBActivity() throws Exception {
        int positionToClick = 0;
        setUpShadowAdapter(positionToClick);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClickOverViewInHolder(positionToClick, R.id.fbSend);

        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(new ComponentName(RuntimeEnvironment.application, FacebookActivity.class), intent.getComponent());
    }

    private void setUpShadowAdapter(int positionToClick) {
        when(recipe.getSourceURL()).thenReturn("https://galileo.edu");
        when(recipes.get(positionToClick)).thenReturn(recipe);

        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerView);
        RecipesAdapter adapterPopulated = new RecipesAdapter(recipes, imageLoader, onItemClickListener);
        recyclerView.setAdapter(adapterPopulated);
        shadowAdapter = extract(adapterPopulated);
    }
}