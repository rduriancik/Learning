package com.example.robert.facebookrecipes.recipeList;

import com.example.robert.facebookrecipes.BaseTest;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

/**
 * Created by robert on 8.10.2017.
 */
public class RecipeListInteractorImplTest extends BaseTest {
    @Mock
    private RecipeListRepository recipeListRepository;

    private RecipeListInteractorImpl interactor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        interactor = new RecipeListInteractorImpl(recipeListRepository);
    }

    @Test
    public void testExecute_shouldCallRepository() throws Exception {
        interactor.execute();
        verify(recipeListRepository).getSavedRecipes();
    }
}