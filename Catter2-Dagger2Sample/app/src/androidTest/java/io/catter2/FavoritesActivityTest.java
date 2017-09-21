package io.catter2;

import android.content.Intent;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.catter2.di.FavoritesRepositoryModule;
import io.catter2.di.UserComponent;
import io.catter2.favorites.FavoritesRepository;
import io.catter2.favorites.GetFavoritesUseCase;
import io.catter2.favorites_activity.FavoritesActivity;
import io.catter2.favorites_activity.FavoritesActivityModule;

import static android.support.test.espresso.Espresso.onView;

/**
 * Created by robert on 20.9.2017.
 */

@RunWith(AndroidJUnit4.class)
public class FavoritesActivityTest {
    @Rule
    public ActivityTestRule<FavoritesActivity> activityRule =
            new ActivityTestRule<FavoritesActivity>(FavoritesActivity.class, true, false);

    @BeforeClass
    public static void beforeClass() {
        UserComponent.initialize(Mockito.mock(FavoritesRepositoryModule.class));
    }

    @Test
    public void testThereAreThreeImages() {
        final List<String> expectedUrls = new ArrayList<>();
        expectedUrls.add("url10");
        expectedUrls.add("url11");
        expectedUrls.add("url12");
        setupGetFavoritesResponse(expectedUrls);

        activityRule.launchActivity(new Intent());
        onView(ViewMatchers.withId(R.id.favorites_rv)).check(new RecyclerViewItemCountAssertion(3));
    }

    @Test
    public void testZeroImages() {
        final List<String> expectedUrls = new ArrayList<>();
        setupGetFavoritesResponse(expectedUrls);

        activityRule.launchActivity(new Intent());
        onView(ViewMatchers.withId(R.id.favorites_rv)).check(new RecyclerViewItemCountAssertion(0));
    }

    private void setupGetFavoritesResponse(final List<String> urls) {
        FavoritesActivityModule.testGetFavoritesUseCase =
                new GetFavoritesUseCase(Mockito.mock(FavoritesRepository.class)) {
                    @Override
                    public void getFavorites(Callback callback) {
                        callback.favoriteUrlsUpdated(urls);
                    }

                    @Override
                    public void clear() {
                        //
                    }
                };
    }
}
