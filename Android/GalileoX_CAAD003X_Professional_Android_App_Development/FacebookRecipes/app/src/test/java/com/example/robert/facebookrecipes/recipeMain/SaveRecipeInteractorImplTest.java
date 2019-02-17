package com.example.robert.facebookrecipes.recipeMain;

import com.example.robert.facebookrecipes.BaseTest;
import com.example.robert.facebookrecipes.entities.Recipe;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

/**
 * Created by robert on 7.10.2017.
 */
public class SaveRecipeInteractorImplTest extends BaseTest {
    @Mock
    private RecipeMainRepository recipeMainRepository;
    @Mock
    private Recipe recipe;
    private SaveRecipeInteractor recipeInteractor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        recipeInteractor = new SaveRecipeInteractorImpl(recipeMainRepository);
    }

    @Test
    public void testExecute_callRepository() throws Exception {
        recipeInteractor.execute(recipe);

        verify(recipeMainRepository).saveRecipe(recipe);
    }

}