package com.example.robert.facebookrecipes.recipeMain;

import com.example.robert.facebookrecipes.BaseTest;
import com.example.robert.facebookrecipes.BuildConfig;
import com.example.robert.facebookrecipes.api.RecipeSearchResponse;
import com.example.robert.facebookrecipes.api.RecipeService;
import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.libs.base.EventBus;
import com.example.robert.facebookrecipes.recipeMain.events.RecipeMainEvent;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Random;

import edu.emory.mathcs.backport.java.util.Arrays;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by robert on 7.10.2017.
 */
public class RecipeMainRepositoryImplTest extends BaseTest {
    @Mock
    private EventBus eventBus;
    @Mock
    private RecipeService service;
    @Mock
    private Recipe recipe;

    private RecipeMainRepository recipeMainRepository;
    private ArgumentCaptor<RecipeMainEvent> recipeMainEventArgumentCaptor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        recipeMainRepository = new RecipeMainRepositoryImpl(eventBus, service);
        recipeMainEventArgumentCaptor = ArgumentCaptor.forClass(RecipeMainEvent.class);
    }

    @Test
    public void testSaveRecipeCalled_eventPosted() throws Exception {
        when(recipe.exists()).thenReturn(true);
        recipeMainRepository.saveRecipe(recipe);

        verify(eventBus).post(recipeMainEventArgumentCaptor.capture());
        RecipeMainEvent event = recipeMainEventArgumentCaptor.getValue();

        assertEquals(RecipeMainEvent.SAVE_EVENT, event.getType());
        assertNull(event.getRecipe());
        assertNull(event.getError());
    }

    @Test
    public void testGetNextRecipeCalled_APIServiceSuccessCall_eventPosted() throws Exception {
        int recipePage = new Random().nextInt(RecipeMainRepository.RECIPE_RANGE);
        when(service.search(BuildConfig.FOOD_API_KEY,
                RecipeMainRepository.RECENT_SORT,
                RecipeMainRepository.COUNT,
                recipePage)).thenReturn(buildCall(true, null));

        recipeMainRepository.setRecipePage(recipePage);
        recipeMainRepository.getNextRecipe();

        verify(service).search(BuildConfig.FOOD_API_KEY,
                RecipeMainRepository.RECENT_SORT,
                RecipeMainRepository.COUNT,
                recipePage);
        verify(eventBus).post(recipeMainEventArgumentCaptor.capture());
        RecipeMainEvent event = recipeMainEventArgumentCaptor.getValue();

        assertEquals(RecipeMainEvent.NEXT_EVENT, event.getType());
        assertNotNull(event.getRecipe());
        assertNull(event.getError());
        assertEquals(recipe, event.getRecipe());
    }

    @Test
    public void testGetNextRecipeCalled_APIServiceFailedCall_eventPosted() throws Exception {
        String errorMsg = "error";
        int recipePage = new Random().nextInt(RecipeMainRepository.RECIPE_RANGE);
        when(service.search(BuildConfig.FOOD_API_KEY,
                RecipeMainRepository.RECENT_SORT,
                RecipeMainRepository.COUNT,
                recipePage)).thenReturn(buildCall(false, errorMsg));

        recipeMainRepository.setRecipePage(recipePage);
        recipeMainRepository.getNextRecipe();

        verify(service).search(BuildConfig.FOOD_API_KEY,
                RecipeMainRepository.RECENT_SORT,
                RecipeMainRepository.COUNT,
                recipePage);
        verify(eventBus).post(recipeMainEventArgumentCaptor.capture());
        RecipeMainEvent event = recipeMainEventArgumentCaptor.getValue();

        assertEquals(RecipeMainEvent.NEXT_EVENT, event.getType());
        assertNull(event.getRecipe());
        assertNotNull(event.getError());
        assertEquals(errorMsg, event.getError());
    }

    private Call<RecipeSearchResponse> buildCall(final boolean success, final String errorMsg) {
        Call<RecipeSearchResponse> response = new Call<RecipeSearchResponse>() {
            @Override
            public Response<RecipeSearchResponse> execute() throws IOException {
                Response<RecipeSearchResponse> result = null;

                if (success) {
                    RecipeSearchResponse response = new RecipeSearchResponse();
                    response.setCount(1);
                    response.setRecipes(Arrays.asList(new Recipe[]{recipe}));
                    result = Response.success(response);
                } else {
                    result = Response.error(null, null);
                }

                return result;
            }

            @Override
            public void enqueue(Callback<RecipeSearchResponse> callback) {
                if (success) {
                    try {
                        callback.onResponse(this, execute());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    callback.onFailure(this, new Throwable(errorMsg));
                }
            }

            @Override
            public boolean isExecuted() {
                return true;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<RecipeSearchResponse> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return response;
    }
}