package com.example.robert.facebookrecipes.recipeMain;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.robert.facebookrecipes.BaseTest;
import com.example.robert.facebookrecipes.BuildConfig;
import com.example.robert.facebookrecipes.R;
import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.libs.base.ImageLoader;
import com.example.robert.facebookrecipes.login.ui.LoginActivity;
import com.example.robert.facebookrecipes.recipeList.ui.RecipeListActivity;
import com.example.robert.facebookrecipes.recipeMain.ui.RecipeMainActivity;
import com.example.robert.facebookrecipes.recipeMain.ui.RecipeMainView;
import com.example.robert.facebookrecipes.support.ShadowImageView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;
import static org.robolectric.shadow.api.Shadow.extract;

/**
 * Created by robert on 7.10.2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23, shadows = {ShadowImageView.class})
public class RecipeMainActivityTest extends BaseTest {
    @Mock
    private RecipeMainPresenter presenter;
    @Mock
    private Recipe currentRecipe;
    @Mock
    private ImageLoader imageLoader;

    private RecipeMainView view;
    private RecipeMainActivity activity;
    private ActivityController<RecipeMainActivity> controller;
    private ShadowActivity shadowActivity;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RecipeMainActivity recipeMainActivity = new RecipeMainActivity() {
            @Override
            public ImageLoader getImageLoader() {
                return imageLoader;
            }

            @Override
            public RecipeMainPresenter getPresenter() {
                return presenter;
            }
        };

        controller = ActivityController.of(Robolectric.getShadowsAdapter(), recipeMainActivity)
                .create()
                .visible();
        activity = controller.get();
        view = (RecipeMainView) activity;
        shadowActivity = shadowOf(activity);
    }

    @Test
    public void testOnActivityCreated_getNextRecipe() throws Exception {
        verify(presenter).onCreate();
        verify(presenter).getNextRecipe();
    }

    @Test
    public void testOnActivityDestroyed() throws Exception {
        controller.destroy();
        verify(presenter).onDestroy();
    }

    @Test
    public void testLogoutMenuClick_launchLoginActivity() throws Exception {
        shadowActivity.clickMenuItem(R.id.action_logout);
        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(new ComponentName(activity, LoginActivity.class), intent.getComponent());
    }

    @Test
    public void testListMenuClicked_launchRecipeListActivity() throws Exception {
        shadowActivity.clickMenuItem(R.id.action_list);
        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(new ComponentName(activity, RecipeListActivity.class), intent.getComponent());
    }

    @Test
    public void testKeepButtonClicked_shouldSaveRecipe() throws Exception {
        when(currentRecipe.getImageURL()).thenReturn("https://galileo.edu");
        activity.setRecipe(currentRecipe);

        ImageButton imgKeep = (ImageButton) activity.findViewById(R.id.imgKeep);
        imgKeep.performClick();

        verify(presenter).saveRecipe(currentRecipe);
    }

    @Test
    public void testDismissButtonClicked_shouldDismissRecipe() throws Exception {
        ImageButton imgDismiss = (ImageButton) activity.findViewById(R.id.imgDismiss);
        imgDismiss.performClick();

        verify(presenter).dismissRecipe();
    }

    @Test
    public void testOnSwipeToKeep_shouldSaveRecipe() throws Exception {
        when(currentRecipe.getImageURL()).thenReturn("https://galileo.edu");
        activity.setRecipe(currentRecipe);

        ImageView imgRecipe = (ImageView) activity.findViewById(R.id.imgRecipe);
        ShadowImageView shadowImageView = (ShadowImageView) extract(imgRecipe);
        shadowImageView.performSwipe(200, 200, 500, 250, 50);

        verify(presenter).saveRecipe(currentRecipe);
    }

    @Test
    public void testOnSwipeToDismiss_shouldDismissRecipe() throws Exception {
        when(currentRecipe.getImageURL()).thenReturn("https://galileo.edu");
        activity.setRecipe(currentRecipe);

        ImageView imgRecipe = (ImageView) activity.findViewById(R.id.imgRecipe);
        ShadowImageView shadowImageView = (ShadowImageView) extract(imgRecipe);
        shadowImageView.performSwipe(200, 200, -500, 250, 50);

        verify(presenter).dismissRecipe();
    }

    @Test
    public void testShowProgress_progressBarVisible() throws Exception {
        view.showProgress();

        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressBar);
        assertEquals(View.VISIBLE, progressBar.getVisibility());
    }

    @Test
    public void testHideProgress_progressBarGone() throws Exception {
        view.hideProgress();

        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressBar);
        assertEquals(View.GONE, progressBar.getVisibility());
    }

    @Test
    public void testShowUI_buttonsVisible() throws Exception {
        view.showUIElements();

        ImageButton imgKeep = (ImageButton) activity.findViewById(R.id.imgKeep);
        ImageButton imgDismiss = (ImageButton) activity.findViewById(R.id.imgDismiss);

        assertEquals(View.VISIBLE, imgKeep.getVisibility());
        assertEquals(View.VISIBLE, imgDismiss.getVisibility());
    }

    @Test
    public void testHideUI_buttonsGone() throws Exception {
        view.hideUIElements();

        ImageButton imgKeep = (ImageButton) activity.findViewById(R.id.imgKeep);
        ImageButton imgDismiss = (ImageButton) activity.findViewById(R.id.imgDismiss);

        assertEquals(View.GONE, imgKeep.getVisibility());
        assertEquals(View.GONE, imgDismiss.getVisibility());
    }

    @Test
    public void testSetRecipe_imageLoaderCalled() throws Exception {
        String url = "http://galileo.edu";
        when(currentRecipe.getImageURL()).thenReturn(url);

        view.setRecipe(currentRecipe);
        ImageView imgRecipe = (ImageView) activity.findViewById(R.id.imgRecipe);

        verify(imageLoader).load(imgRecipe, currentRecipe.getImageURL());

    }

    @Test
    public void testSaveAnimation_AnimationShouldBeStarted() throws Exception {
        view.saveAnimation();

        ImageView imgRecipe = (ImageView) activity.findViewById(R.id.imgRecipe);

        assertNotNull(imgRecipe.getAnimation());
        assertTrue(imgRecipe.getAnimation().hasStarted());
    }

    @Test
    public void testDismissAnimation_() throws Exception {
        view.dismissAnimation();

        ImageView imgRecipe = (ImageView) activity.findViewById(R.id.imgRecipe);

        assertNotNull(imgRecipe.getAnimation());
        assertTrue(imgRecipe.getAnimation().hasStarted());
    }
}
