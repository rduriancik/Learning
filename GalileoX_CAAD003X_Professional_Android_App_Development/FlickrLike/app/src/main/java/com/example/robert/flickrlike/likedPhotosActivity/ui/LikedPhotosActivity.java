package com.example.robert.flickrlike.likedPhotosActivity.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.robert.flickrlike.R;
import com.example.robert.flickrlike.entities.Photo;
import com.example.robert.flickrlike.likedPhotosActivity.LikedPhotosPresenter;
import com.example.robert.flickrlike.likedPhotosActivity.adapters.LikedPhotosAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LikedPhotosActivity extends AppCompatActivity implements LikedPhotosView {

    @BindView(R.id.rvPhotos)
    RecyclerView rvPhotos;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.emptyView)
    TextView emptyView;
    @BindView(R.id.container)
    LinearLayout container;

    LikedPhotosAdapter adapter;
    LikedPhotosPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_photos);
        ButterKnife.bind(this);
        setupInjection();
        setupRecyclerView();

        presenter.getLikedPhotos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {

    }

    private void setupRecyclerView() {
        rvPhotos.setAdapter(adapter);
        rvPhotos.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        rvPhotos.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContent() {
        rvPhotos.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void setContent(List<Photo> photos) {
        adapter.setPhotos(photos);
    }
}
