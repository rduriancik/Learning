package io.catter2;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.catter2.favorites.FavoriteModel;
import io.catter2.favorites.FavoritesRepository;
import io.catter2.favorites.GetFavoritesUseCase;

/**
 * Created by robert on 19.9.2017.
 */

public class GetFavoritesUseCaseTest {

    @Test
    public void testEmptyList() throws InterruptedException {
        FavoritesRepository mock = Mockito.mock(FavoritesRepository.class);
        GetFavoritesUseCase useCase = useCase(mock);

        Mockito.when(mock.getFavorites()).thenReturn(new ArrayList<FavoriteModel>());

        final CountDownLatch latch = new CountDownLatch(1);

        useCase.getFavorites(new GetFavoritesUseCase.Callback() {
            @Override
            public void favoriteUrlsUpdated(List<String> favoriteUrls) {
                Assert.assertEquals(0, favoriteUrls.size());
                latch.countDown();
            }
        });

        latch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void testSingleElementList() throws InterruptedException {
        FavoritesRepository mock = Mockito.mock(FavoritesRepository.class);
        GetFavoritesUseCase useCase = useCase(mock);

        {
            ArrayList<FavoriteModel> mockList = new ArrayList<>();
            mockList.add(new FavoriteModel(10, "url-0"));
            Mockito.when(mock.getFavorites()).thenReturn(mockList);
        }

        final CountDownLatch latch = new CountDownLatch(1);
        useCase.getFavorites(new GetFavoritesUseCase.Callback() {
            @Override
            public void favoriteUrlsUpdated(List<String> favoriteUrls) {
                Assert.assertEquals(1, favoriteUrls.size());
                Assert.assertEquals("url-0", favoriteUrls.get(0));
                latch.countDown();
            }
        });

        latch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void testThreeElementsOrder() throws InterruptedException {
        FavoritesRepository mock = Mockito.mock(FavoritesRepository.class);
        GetFavoritesUseCase useCase = useCase(mock);

        {
            ArrayList<FavoriteModel> mockList = new ArrayList<>();
            mockList.add(new FavoriteModel(10, "url-0"));
            mockList.add(new FavoriteModel(11, "url-1"));
            mockList.add(new FavoriteModel(12, "url-2"));
            Mockito.when(mock.getFavorites()).thenReturn(mockList);
        }

        final CountDownLatch latch = new CountDownLatch(1);
        useCase.getFavorites(new GetFavoritesUseCase.Callback() {
            @Override
            public void favoriteUrlsUpdated(List<String> favoriteUrls) {
                Assert.assertEquals(3, favoriteUrls.size());
                Assert.assertEquals("url-0", favoriteUrls.get(0));
                Assert.assertEquals("url-1", favoriteUrls.get(1));
                Assert.assertEquals("url-2", favoriteUrls.get(2));
                latch.countDown();
            }
        });

        latch.await(10, TimeUnit.SECONDS);
    }

    private GetFavoritesUseCase useCase(FavoritesRepository repository) {
        return new GetFavoritesUseCase(repository);
    }
}
