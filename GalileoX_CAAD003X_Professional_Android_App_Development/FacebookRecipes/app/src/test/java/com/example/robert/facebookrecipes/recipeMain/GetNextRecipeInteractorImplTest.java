package com.example.robert.facebookrecipes.recipeMain;

import com.example.robert.facebookrecipes.BaseTest;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

/**
 * Created by robert on 7.10.2017.
 */
public class GetNextRecipeInteractorImplTest extends BaseTest {
    @Mock
    private RecipeMainRepository recipeMainRepository;
    private GetNextRecipeInteractorImpl recipeInteractor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        recipeInteractor = new GetNextRecipeInteractorImpl(recipeMainRepository);
    }

    @Test
    public void testExecute_callRepository() throws Exception {
        recipeInteractor.execute();
        verify(recipeMainRepository).getNextRecipe();
    }

}