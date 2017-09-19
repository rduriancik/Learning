package io.catter2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.plattysoft.leonids.ParticleSystem;

import java.util.List;

import io.catter2.cat_api.FetchImageUseCase;
import io.catter2.cat_api.RetrofitTheCatAPI;
import io.catter2.cat_api.TheCatAPI;
import io.catter2.favorites.AddFavoriteUseCase;
import io.catter2.favorites.FavoritesRepository;
import io.catter2.favorites.SharedPrefFavoritesRepository;

public class ListActivity extends AppCompatActivity {
    private static String TAG = "List";
    private static String ARG_USER_TOKEN = "list-user-token";

    static public void launch(Context context, String userToken) {
        Intent intent = new Intent(context, ListActivity.class);
        intent.putExtra(ARG_USER_TOKEN, userToken);
        context.startActivity(intent);
    }

    private String userToken;
    private RecyclerView recyclerView;
    private AddFavoriteUseCase addFavoriteUseCase;
    private FetchImageUseCase fetchImageUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String extraUserToken = getIntent().getStringExtra(ARG_USER_TOKEN);
        if (extraUserToken != null) {
            userToken = extraUserToken;
        }
        Log.d(TAG, "UserToken: " + userToken);

        recyclerView = (RecyclerView) findViewById(R.id.list_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ImagesRvAdapter adapter = new ImagesRvAdapter(new ImagesRvAdapter.ImageOnClick() {
            @Override
            public void imageClicked(ImageView view, String url) {
                addUrlToUserFavoritesList(view, url);
            }
        });
        recyclerView.setAdapter(adapter);

        FavoritesRepository repository = new SharedPrefFavoritesRepository(this, userToken);
        addFavoriteUseCase = new AddFavoriteUseCase(repository);
        TheCatAPI api = new RetrofitTheCatAPI();
        fetchImageUseCase = new FetchImageUseCase(api);

        fetchImageUseCase.getImagesUrls(new FetchImageUseCase.Callback() {
            @Override
            public void imageUrls(List<String> urls) {
                adapter.updateImageUrls(urls);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FavoritesActivity.launch(this, userToken, true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addUrlToUserFavoritesList(ImageView view, String url) {
        if (addFavoriteUseCase.addUrlToUserFavoritesList(url)) {
            new ParticleSystem(ListActivity.this, 500, R.mipmap.azunyan_2, 2000)
                    .setAcceleration(0.0005f, 90)
                    .setSpeedRange(0.2f, 0.5f)
                    .setRotationSpeedRange(90, 180)
                    .setInitialRotationRange(0, 180)
                    .setFadeOut(500)
                    .setScaleRange(0.1f, 1.0f)
                    .oneShot(view, 100);

            Snackbar.make(recyclerView, R.string.list_user_favorite_url_added_success, Snackbar.LENGTH_SHORT)
                    .show();
        } else {
            Snackbar.make(recyclerView, R.string.list_user_favorite_url_already_in, Snackbar.LENGTH_SHORT)
                    .show();
        }
    }
}