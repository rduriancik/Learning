package com.example.robert.facebookrecipes.recipeMain;

import com.example.robert.facebookrecipes.BaseTest;
import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.libs.base.EventBus;
import com.example.robert.facebookrecipes.recipeMain.events.RecipeMainEvent;
import com.example.robert.facebookrecipes.recipeMain.ui.RecipeMainView;

import org.junit.Test;
import org.mockito.Mock;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by robert on 7.10.2017.
 */

public class RecipeMainPresenterImplTest extends BaseTest {
    @Mock
    private EventBus eventBus;
    @Mock
    private RecipeMainView view;
    @Mock
    private SaveRecipeInteractor saveRecipeInteractor;
    @Mock
    private GetNextRecipeInteractor getNextRecipeInteractor;
    @Mock
    private Recipe recipe;
    @Mock
    private RecipeMainEvent event;

    private RecipeMainPresenterImpl presenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        presenter = new RecipeMainPresenterImpl(eventBus, view, saveRecipeInteractor, getNextRecipeInteractor);
    }

    @Test
    public void testOnCreate_subscribedToEventBus() throws Exception {
        presenter.onCreate();
        verify(eventBus).register(presenter);
    }

    @Test
    public void testOnDestroy_unsubscribedToEventBus() throws Exception {
        presenter.onDestroy();
        verify(eventBus).unregister(presenter);
        assertNull(presenter.getView());
    }

    @Test
    public void testSaveRecipe_executeSaveInteractor() throws Exception {
        presenter.saveRecipe(recipe);

        assertNotNull(presenter.getView());
        verify(view).saveAnimation();
        verify(view).hideUIElements();
        verify(view).showProgress();
        verify(saveRecipeInteractor).execute(recipe);
    }

    @Test
    public void testDismissRecipe_executeGetNextRecipeInteractor() throws Exception {
        presenter.dismissRecipe();

        assertNotNull(presenter.getView());
        verify(view).dismissAnimation();
    }

    @Test
    public void testGetNextRecipe_executeGetNextRecipeInteractor() throws Exception {
        presenter.dismissRecipe();

        assertNotNull(presenter.getView());
        verify(view).hideUIElements();
        verify(view).showProgress();
        verify(getNextRecipeInteractor).execute();
    }

    @Test
    public void testOnEvent_hasError() throws Exception {
        String errorMsg = "error";

        when(event.getError()).thenReturn(errorMsg);

        presenter.onEventMainThread(event);

        assertNotNull(presenter.getView());
        verify(view).hideProgress();
        verify(view).onGetRecipeError(event.getError());
    }

    @Test
    public void testOnNextEvent_setRecipeOnView() throws Exception {
        when(event.getType()).thenReturn(RecipeMainEvent.NEXT_EVENT);
        when(event.getRecipe()).thenReturn(recipe);

        presenter.onEventMainThread(event);

        assertNotNull(presenter.getView());
        verify(view).setRecipe(event.getRecipe());
    }

    @Test
    public void testOnSaveEvent_notifyViewAndCallGetNextRecipe() throws Exception {
        when(event.getType()).thenReturn(RecipeMainEvent.SAVE_EVENT);

        presenter.onEventMainThread(event);

        assertNotNull(presenter.getView());
        verify(view).onRecipeSaved();
        verify(getNextRecipeInteractor).execute();
    }

    @Test
    public void testImageReady_updateUI() throws Exception {
        presenter.imageReady();

        assertNotNull(presenter.getView());
        verify(view).hideProgress();
        verify(view).showUIElements();
    }

    @Test
    public void testImageError_updateUI() throws Exception {
        String errorMsg = "error";
        presenter.imageError(errorMsg);

        assertNotNull(presenter.getView());
        verify(view).onGetRecipeError(errorMsg);
    }

    @Test
    public void testGetView_returnsView() throws Exception {
        assertEquals(view, presenter.getView());
    }
}
