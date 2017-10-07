package com.example.robert.facebookrecipes.api;

import com.example.robert.facebookrecipes.BaseTest;
import com.example.robert.facebookrecipes.BuildConfig;
import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.recipeMain.RecipeMainRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by robert on 5.10.2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class RecipeServiceTest extends BaseTest {
    private RecipeService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        RecipeClient client = new RecipeClient();
        service = client.getRecipeService();
    }

    @Test
    public void doSearch_getRecipeFromBackend() throws Exception {
        String sort = RecipeMainRepository.RECENT_SORT;
        int count = RecipeMainRepository.COUNT;
        int page = 1;
        Call<RecipeSearchResponse> call = service.search(BuildConfig.FOOD_API_KEY, sort, count, page);

        Response<RecipeSearchResponse> response = call.execute();
        assertTrue(response.isSuccessful());

        RecipeSearchResponse recipeSearchResponse = response.body();
        assertEquals(1, recipeSearchResponse.getCount());

        Recipe recipe = recipeSearchResponse.getFirstRecipe();
        assertNotNull(recipe);
    }

    @Test
    public void doSearch_getNoRecipeFromBackend() throws Exception {
        String sort = RecipeMainRepository.RECENT_SORT;
        int count = RecipeMainRepository.COUNT;
        int page = 10000000;
        Call<RecipeSearchResponse> call = service.search(BuildConfig.FOOD_API_KEY, sort, count, page);

        Response<RecipeSearchResponse> response = call.execute();
        assertTrue(response.isSuccessful());

        RecipeSearchResponse recipeSearchResponse = response.body();
        assertEquals(0, recipeSearchResponse.getCount());

        Recipe recipe = recipeSearchResponse.getFirstRecipe();
        assertNull(recipe);
    }

    @Test
    public void doSearch_getRandomRecipeFromBackend() throws Exception {
        String sort = RecipeMainRepository.RECENT_SORT;
        int count = RecipeMainRepository.COUNT;
        int page = new Random().nextInt(RecipeMainRepository.RECIPE_RANGE);
        Call<RecipeSearchResponse> call = service.search(BuildConfig.FOOD_API_KEY, sort, count, page);

        Response<RecipeSearchResponse> response = call.execute();
        assertTrue(response.isSuccessful());

        RecipeSearchResponse recipeSearchResponse = response.body();
        if (recipeSearchResponse.getCount() == 1) {
            Recipe recipe = recipeSearchResponse.getFirstRecipe();
            assertNotNull(recipe);
        } else {
            System.out.println("Invalid recipe, try again");
        }
    }
}
