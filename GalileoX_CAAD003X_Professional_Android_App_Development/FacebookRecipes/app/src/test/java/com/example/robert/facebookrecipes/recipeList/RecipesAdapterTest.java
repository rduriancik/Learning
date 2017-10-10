package com.example.robert.facebookrecipes.recipeList;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.robert.facebookrecipes.BaseTest;
import com.example.robert.facebookrecipes.BuildConfig;
import com.example.robert.facebookrecipes.R;
import com.example.robert.facebookrecipes.entities.Recipe;
import com.example.robert.facebookrecipes.libs.base.ImageLoader;
import com.example.robert.facebookrecipes.recipeList.adapters.OnItemClickListener;
import com.example.robert.facebookrecipes.recipeList.adapters.RecipesAdapter;
import com.example.robert.facebookrecipes.support.ShadowRecyclerViewAdapter;
import com.facebook.share.model.ShareContent;
import com.facebook.share.widget.SendButton;
import com.facebook.share.widget.ShareButton;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.shadow.api.Shadow.extract;

/**
 * Created by robert on 9.10.2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23,
        shadows = ShadowRecyclerViewAdapter.class)
public class RecipesAdapterTest extends BaseTest {

    @Mock
    private Recipe recipe;
    @Mock
    private List<Recipe> recipes;
    @Mock
    private ImageLoader imageLoader;
    @Mock
    private OnItemClickListener listener;

    private RecipesAdapter adapter;
    private String URL = "https://galileo.edu";
    private ShadowRecyclerViewAdapter shadowAdapter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        when(recipe.getSourceURL()).thenReturn(URL);

        adapter = new RecipesAdapter(recipes, imageLoader, listener);
        shadowAdapter = (ShadowRecyclerViewAdapter) extract(adapter);

        Activity activity = Robolectric.setupActivity(Activity.class);
        RecyclerView recyclerView = new RecyclerView(activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }

    @Test
    public void testSetRecipes_itemCountMatches() throws Exception {
        int itemCount = 5;
        when(recipes.size()).thenReturn(itemCount);
        adapter.setRecipes(recipes);

        assertEquals(itemCount, adapter.getItemCount());
    }

    @Test
    public void testRemoveRecipe_isRemovedFromAdapter() throws Exception {
        adapter.removeRecipes(recipe);
        verify(recipes).remove(recipe);
    }

    @Test
    public void testOnItemClick_shouldCallListener() throws Exception {
        int positionToClick = 0;
        when(recipes.get(positionToClick)).thenReturn(recipe);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClick(positionToClick);

        verify(listener).onItemClick(recipe);
    }

    @Test
    public void testViewHolder_shouldRenderTitle() throws Exception {
        int positionToShow = 0;
        String recipeTitle = "title";
        when(recipe.getTitle()).thenReturn(recipeTitle);
        when(recipes.get(positionToShow)).thenReturn(recipe);

        shadowAdapter.itemVisible(positionToShow);

        View view = shadowAdapter.getViewForHolderPosition(positionToShow);
        TextView txtTitle = (TextView) view.findViewById(R.id.txtRecipeName);

        assertEquals(recipeTitle, txtTitle.getText().toString());
        assertEquals(View.VISIBLE, txtTitle.getVisibility());
    }

    @Test
    public void testOnFavoriteClick_shouldCallListener() throws Exception {
        int positionToClick = 0;
        boolean favorite = true;
        when(recipe.isFavorite()).thenReturn(favorite);
        when(recipes.get(positionToClick)).thenReturn(recipe);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClickOverViewInHolder(positionToClick, R.id.imgFav);

        View view = shadowAdapter.getViewForHolderPosition(positionToClick);
        ImageButton imgFav = (ImageButton) view.findViewById(R.id.imgFav);

        assertEquals(favorite, imgFav.getTag());
        verify(listener).onFavClick(recipe);
    }

    @Test
    public void testOnNonFavoriteClick_shouldCallListener() throws Exception {
        int positionToClick = 0;
        boolean favorite = false;
        when(recipe.isFavorite()).thenReturn(favorite);
        when(recipes.get(positionToClick)).thenReturn(recipe);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClickOverViewInHolder(positionToClick, R.id.imgFav);

        View view = shadowAdapter.getViewForHolderPosition(positionToClick);
        ImageButton imgFav = (ImageButton) view.findViewById(R.id.imgFav);

        assertEquals(favorite, imgFav.getTag());
        verify(listener).onFavClick(recipe);
    }

    @Test
    public void testOnDeleteClick_shouldCallListener() throws Exception {
        int positionToClick = 0;
        when(recipes.get(positionToClick)).thenReturn(recipe);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClickOverViewInHolder(positionToClick, R.id.imgDelete);

        verify(listener).onDeleteClick(recipe);
    }

    @Test
    public void testFBShareBind_shareContentSet() throws Exception {
        int positionToShow = 0;
        when(recipes.get(positionToShow)).thenReturn(recipe);

        shadowAdapter.itemVisible(positionToShow);
        View view = shadowAdapter.getViewForHolderPosition(positionToShow);
        ShareButton fbShare = (ShareButton) view.findViewById(R.id.fbShare);

        ShareContent shareContent = fbShare.getShareContent();
        assertNotNull(shareContent);
        assertEquals(URL, shareContent.getContentUrl().toString());
    }

    @Test
    public void testFBSendBind_shareContentSet() throws Exception {
        int positionToShow = 0;
        when(recipes.get(positionToShow)).thenReturn(recipe);

        shadowAdapter.itemVisible(positionToShow);
        View view = shadowAdapter.getViewForHolderPosition(positionToShow);
        SendButton fbSend = (SendButton) view.findViewById(R.id.fbSend);

        ShareContent shareContent = fbSend.getShareContent();
        assertNotNull(shareContent);
        assertEquals(URL, shareContent.getContentUrl().toString());
    }
}
