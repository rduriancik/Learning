package com.example.robert.facebookrecipes.recipeList;

import com.example.robert.facebookrecipes.BaseTest;
import com.example.robert.facebookrecipes.BuildConfig;
import com.example.robert.facebookrecipes.FacebookRecipesApp;
import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.entities.Recipe_Table;
import com.example.robert.facebookrecipes.libs.base.EventBus;
import com.example.robert.facebookrecipes.recipeList.events.RecipeListEvent;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.mockito.Mockito.verify;

/**
 * Created by robert on 9.10.2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class RecipeListRepositoryImplTest extends BaseTest {

    @Mock
    private EventBus eventBus;

    private RecipeListRepositoryImpl repository;
    private FacebookRecipesApp app;
    private ArgumentCaptor<RecipeListEvent> recipeArgumentCaptor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        repository = new RecipeListRepositoryImpl(eventBus);
        app = (FacebookRecipesApp) RuntimeEnvironment.application;
        recipeArgumentCaptor = ArgumentCaptor.forClass(RecipeListEvent.class);

        app.onCreate();
    }

    @After
    public void tearDown() throws Exception {
        app.onTerminate();
    }

    @Test
    public void getSavedRecipes_eventPosted() throws Exception {
        int recipesToStore = 5;
        Recipe currentRecipe;
        List<Recipe> testRecipeList = new ArrayList<>();
        for (int i = 0; i < recipesToStore; ++i) {
            currentRecipe = new Recipe();
            currentRecipe.setRecipeID("id " + i);
            currentRecipe.save();
            testRecipeList.add(currentRecipe);
        }

        List<Recipe> recipesFromDb = new Select()
                .from(Recipe.class)
                .queryList();

        repository.getSavedRecipes();

        verify(eventBus).post(recipeArgumentCaptor.capture());
        RecipeListEvent event = recipeArgumentCaptor.getValue();

        assertEquals(RecipeListEvent.READ_EVENT, event.getType());
        assertEquals(recipesFromDb, event.getRecipeList());

        for (Recipe recipe : testRecipeList) {
            recipe.delete();
        }
    }

    @Test
    public void updateRecipe() throws Exception {
        String recipeId = "id1";
        String titleBefore = "title before update";
        String titleAfter = "title after update";

        Recipe recipe = new Recipe();
        recipe.setRecipeID(recipeId);
        recipe.setTitle(titleBefore);
        recipe.save();
        recipe.setTitle(titleAfter);

        repository.updateRecipe(recipe);

        Recipe recipeFromDb = new Select()
                .from(Recipe.class)
                .where(Recipe_Table.recipeID.is(recipeId))
                .querySingle();

        assertEquals(titleAfter, recipeFromDb.getTitle());
        verify(eventBus).post(recipeArgumentCaptor.capture());

        RecipeListEvent event = recipeArgumentCaptor.getValue();
        assertEquals(RecipeListEvent.UPDATE_EVENT, event.getType());

        recipe.delete();
    }

    @Test
    public void removeRecipe() throws Exception {
        String recipeId = "id1";

        Recipe recipe = new Recipe();
        recipe.setRecipeID(recipeId);
        recipe.save();

        repository.removeRecipe(recipe);

        assertFalse(recipe.exists());
        verify(eventBus).post(recipeArgumentCaptor.capture());

        RecipeListEvent event = recipeArgumentCaptor.getValue();
        assertEquals(RecipeListEvent.DELETE_EVENT, event.getType());
        assertEquals(1, event.getRecipeList().size());
        assertEquals(recipe, event.getRecipeList().get(0));

        recipe.delete();
    }

}