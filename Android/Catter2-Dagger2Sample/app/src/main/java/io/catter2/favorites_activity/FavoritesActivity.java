package io.catter2.favorites_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import io.catter2.ImagesRvAdapter;
import io.catter2.R;
import io.catter2.favorites.GetFavoritesUseCase;
import io.catter2.list_activity.ListActivity;

public class FavoritesActivity extends AppCompatActivity {

    private static String TAG = "ImagesRvAdapter";
    private static String ARG_USER_TOKEN = "favorites-user-token";

    static public void launch(Context context) {
        Intent intent = new Intent(context, FavoritesActivity.class);
        context.startActivity(intent);
    }

    private RecyclerView recyclerView;
    private ImagesRvAdapter rvAdapter;
    @Inject
    GetFavoritesUseCase getFavoritesUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListActivity.launch(FavoritesActivity.this);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.favorites_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new ImagesRvAdapter(null);
        recyclerView.setAdapter(rvAdapter);

        FavoritesActivityComponent.initializeAndInject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getFavoritesUseCase.getFavorites(new GetFavoritesUseCase.Callback() {
            @Override
            public void favoriteUrlsUpdated(List<String> favoriteUrls) {
                rvAdapter.updateImageUrls(favoriteUrls);
            }
        });
    }

    @Override
    protected void onPause() {
        getFavoritesUseCase.clear();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        getFavoritesUseCase = null;
        super.onDestroy();
    }
}
