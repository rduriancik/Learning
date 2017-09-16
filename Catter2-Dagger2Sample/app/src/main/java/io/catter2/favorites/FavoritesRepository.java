package io.catter2.favorites;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.catter2.FavoritesActivity;
import io.catter2.R;

/**
 * Created by robert on 14.9.2017.
 */

public class FavoritesRepository {

    public interface OnChangeListener {
        void onChanged(List<FavoriteModel> favorites);
    }

    private static String SP_USER_FAVORITES_KEY = "user-favorites-urls-%s";
    private static final String TAG = "FavoritesRepository";

    private Context mContext;
    private String userToken;
    private OnChangeListener onChangeListener;
    private SharedPreferences.OnSharedPreferenceChangeListener sharedPrefListener;

    public FavoritesRepository(Context mContext, String userToken) {
        this.mContext = mContext;
        this.userToken = userToken;
    }

    public void registerOnChangeListener(OnChangeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Invalid OnChangeListener");
        }
        this.onChangeListener = listener;
        this.sharedPrefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Log.d(TAG, "Key changed: " + key);
                String prefKey = getFavoritesKey();
                if (key.equals(prefKey)) {
                    onChangeListener.onChanged(getFavorites());
                }
            }
        };
        getPref().registerOnSharedPreferenceChangeListener(sharedPrefListener);
    }

    private List<FavoriteModel> getFavorites() {
        SharedPreferences preferences = getPref();
        String prefKey = getFavoritesKey();
        Set<String> entriesSet = preferences.getStringSet(prefKey, new HashSet<String>());

        ArrayList<FavoriteModel> favorites = new ArrayList<>(entriesSet.size());
        for (String entry : entriesSet) {
            String[] decoded = entry.split(";");
            favorites.add(new FavoriteModel(Long.valueOf(decoded[1]), decoded[0]));
        }

        Collections.sort(favorites, new Comparator<FavoriteModel>() {
            @Override
            public int compare(FavoriteModel o1, FavoriteModel o2) {
                return (int) (o2.getTimeAdded() - o1.getTimeAdded());
            }
        });

        return favorites;
    }

    public void clearListeners() {
        onChangeListener = null;
        if (sharedPrefListener != null) {
            getPref().unregisterOnSharedPreferenceChangeListener(sharedPrefListener);
            sharedPrefListener = null;
        }
    }

    public List<FavoriteModel> addFavorite(FavoriteModel favorite) {
        SharedPreferences pref = getPref();
        String prefKey = getFavoritesKey();
        Log.d(TAG, "Pref key: " + prefKey);
        List<FavoriteModel> oldModels = getFavorites();

        boolean hasUrl = false;
        for (FavoriteModel entry : oldModels) {
            if (entry.getUrl().equals(favorite.getUrl())) {
                hasUrl = true;
                break;
            }
        }

        if (hasUrl) {
            return oldModels;
        }

        List<FavoriteModel> newModels = new ArrayList<>(oldModels);
        newModels.add(favorite);
        saveFavorites(newModels);
        return newModels;
    }

    private SharedPreferences getPref() {
        return mContext.getSharedPreferences(
                mContext.getString(R.string.pref_key_user_data), Context.MODE_PRIVATE);
    }

    private String getFavoritesKey() {
        return String.format(FavoritesActivity.SP_USER_FAVORITES_KEY, userToken);
    }

    private void saveFavorites(List<FavoriteModel> newFav) {
        Set<String> newEntries = new HashSet<>(newFav.size());

        for (FavoriteModel model : newFav) {
            newEntries.add(model.getUrl() + ";" + model.getTimeAdded());
        }

        getPref().edit().putStringSet(getFavoritesKey(), newEntries).apply();
    }
}
