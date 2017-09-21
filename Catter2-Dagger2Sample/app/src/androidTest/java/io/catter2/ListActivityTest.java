package io.catter2;

import android.content.Intent;
import android.os.SystemClock;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.catter2.cat_api.FetchImageUseCase;
import io.catter2.cat_api.TheCatAPI;
import io.catter2.favorites.AddFavoriteUseCase;
import io.catter2.favorites.FavoritesRepository;
import io.catter2.list_activity.ListActivity;
import io.catter2.list_activity.ListActivityModule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by robert on 21.9.2017.
 */

@RunWith(AndroidJUnit4.class)
public class ListActivityTest {
    @Rule
    public ActivityTestRule<ListActivity> activityRule =
            new ActivityTestRule<>(ListActivity.class, true, false);

    @Test
    public void testThereAreThreeImages() {
        final List<String> expectedUrls = new ArrayList<>();
        expectedUrls.add("ur10");
        expectedUrls.add("ur11");
        expectedUrls.add("ur12");
        setupListCatImages(expectedUrls);

        activityRule.launchActivity(new Intent());

        onView(withId(R.id.list_rv)).check(new RecyclerViewItemCountAssertion(3));
    }

    @Test
    public void testImageTouchAddFavorite() {
        final List<String> expectedUrl = new ArrayList<>();
        expectedUrl.add("ur10");
        setupListCatImages(expectedUrl);

        AddFavoriteUseCase stub = new AddFavoriteUseCase(Mockito.mock(FavoritesRepository.class)) {
            @Override
            public boolean addUrlToUserFavoritesList(String url) {
                Assert.assertTrue("ur10".equals(url));
                return true;
            }
        };
        ListActivityModule.testAddFavoriteUseCase = stub;

        activityRule.launchActivity(new Intent());

        SystemClock.sleep(1000);

        onView(withId(R.id.list_rv)).check(new RecyclerViewItemCountAssertion(1));
        onView(withId(R.id.list_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    private void setupListCatImages(final List<String> urls) {
        ListActivityModule.testAddFavoriteUseCase =
                new AddFavoriteUseCase(Mockito.mock(FavoritesRepository.class));

        ListActivityModule.testFetchImageUseCase =
                new FetchImageUseCase(Mockito.mock(TheCatAPI.class)) {
                    @Override
                    public void getImagesUrls(Callback callback) {
                        callback.imageUrls(urls);
                    }
                };
    }
}
