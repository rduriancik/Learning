package io.catter2;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.catter2.favorites.AddFavoriteUseCase;
import io.catter2.favorites.FavoriteModel;
import io.catter2.favorites.FavoritesRepository;

/**
 * Created by robert on 19.9.2017.
 */

public class AddFavoriteUseCaseTest {

    @Test
    public void testAddTrue() {
        StubRepo repo = new StubRepo();
        AddFavoriteUseCase useCase = useCase(repo);

        boolean added = useCase.addUrlToUserFavoritesList("url");
        Assert.assertTrue(added);
    }

    @Test
    public void testAddFalse() {
        StubRepo repo = new StubRepo();
        AddFavoriteUseCase useCase = useCase(repo);

        repo.addModel = false;

        boolean added = useCase.addUrlToUserFavoritesList("url");
        Assert.assertFalse(added);
    }

    private AddFavoriteUseCase useCase(FavoritesRepository repository) {
        return new AddFavoriteUseCase(repository);
    }

    private class StubRepo implements FavoritesRepository {
        private List<FavoriteModel> models;
        boolean addModel;

        public StubRepo() {
            this.addModel = true;
            models = new ArrayList<>();
        }

        @Override
        public void registerOnChangeListener(OnChangeListener listener) {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public List<FavoriteModel> getFavorites() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public void clearOnChangeListener() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public List<FavoriteModel> addFavorite(FavoriteModel favorite) {
            if (addModel) {
                models.add(favorite);
            }

            return models;
        }
    }
}
