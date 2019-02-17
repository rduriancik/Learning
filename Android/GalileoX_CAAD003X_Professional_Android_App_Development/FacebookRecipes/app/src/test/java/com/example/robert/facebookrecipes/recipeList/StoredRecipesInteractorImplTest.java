package com.example.robert.facebookrecipes.recipeList;

import com.example.robert.facebookrecipes.BaseTest;
import com.example.robert.facebookrecipes.entities.Recipe;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

/**
 * Created by robert on 8.10.2017.
 */
public class StoredRecipesInteractorImplTest extends BaseTest {
    @Mock
    private RecipeListRepository recipeListRepository;
    @Mock
    private Recipe recipe;

    private StoredRecipesInteractorImpl interactor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        interactor = new StoredRecipesInteractorImpl(recipeListRepository);
    }

    @Test
    public void testExecuteUpdate_callRepository() throws Exception {
        interactor.executeUpdate(recipe);
        verify(recipeListRepository).updateRecipe(recipe);
    }

    @Test
    public void testExecuteDelete_callRepository() throws Exception {
        interactor.executeDelete(recipe);
        verify(recipeListRepository).removeRecipe(recipe);
    }
}