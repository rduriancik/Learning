package com.example.robert.facebookrecipes.recipeList;

import com.example.robert.facebookrecipes.BaseTest;
import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.libs.base.EventBus;
import com.example.robert.facebookrecipes.recipeList.events.RecipeListEvent;
import com.example.robert.facebookrecipes.recipeList.ui.RecipeListView;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import edu.emory.mathcs.backport.java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by robert on 8.10.2017.
 */

public class RecipeListPresenterImplTest extends BaseTest {
    @Mock
    private EventBus eventBus;
    @Mock
    private RecipeListView view;
    @Mock
    private RecipeListInteractor listInteractor;
    @Mock
    private StoredRecipesInteractor storedInteractor;
    @Mock
    private Recipe recipe;
    @Mock
    private RecipeListEvent listEvent;

    private RecipeListPresenterImpl presenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        presenter = new RecipeListPresenterImpl(eventBus, view, listInteractor, storedInteractor);
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
    public void testGetRecipes_executeListInteractor() throws Exception {
        presenter.getRecipes();
        verify(listInteractor).execute();
    }

    @Test
    public void testRemoveRecipe_executeStoredRecipeInteractor() throws Exception {
        presenter.removeRecipe(recipe);
        verify(storedInteractor).executeDelete(recipe);
    }

    @Test
    public void testToggleFavorite_true() throws Exception {
        Recipe recipe = new Recipe();
        boolean favorite = true;
        recipe.setFavorite(favorite);

        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        presenter.toggleFavorite(recipe);

        verify(storedInteractor).executeUpdate(recipeArgumentCaptor.capture());
        assertEquals(!favorite, recipeArgumentCaptor.getValue().isFavorite());
    }

    @Test
    public void testToggleFavorite_false() throws Exception {
        Recipe recipe = new Recipe();
        boolean favorite = false;
        recipe.setFavorite(favorite);

        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        presenter.toggleFavorite(recipe);

        verify(storedInteractor).executeUpdate(recipeArgumentCaptor.capture());
        assertEquals(!favorite, recipeArgumentCaptor.getValue().isFavorite());
    }

    @Test
    public void testOnReadEvent_setRecipesOnView() throws Exception {
        when(listEvent.getType()).thenReturn(RecipeListEvent.READ_EVENT);
        when(listEvent.getRecipeList()).thenReturn(Arrays.asList(new Recipe[]{recipe}));

        presenter.onEventMainThread(listEvent);

        assertNotNull(presenter.getView());
        verify(view).setRecipes(Arrays.asList(new Recipe[]{recipe}));
    }

    @Test
    public void testOnUpdateEvent_shouldCallUpdateOnView() throws Exception {
        when(listEvent.getType()).thenReturn(RecipeListEvent.UPDATE_EVENT);

        presenter.onEventMainThread(listEvent);

        assertNotNull(presenter.getView());
        verify(view).recipeUpdated();
    }

    @Test
    public void testOnDeleteEvent_shouldCallDeleteOnView() throws Exception {
        when(listEvent.getType()).thenReturn(RecipeListEvent.DELETE_EVENT);
        when(listEvent.getRecipeList()).thenReturn(Arrays.asList(new Recipe[]{recipe}));

        presenter.onEventMainThread(listEvent);

        assertNotNull(presenter.getView());
        verify(view).recipeDeleted(recipe);
    }

    @Test
    public void testGetView_shouldReturnView() throws Exception {
        assertEquals(view, presenter.getView());
    }
}
