package com.example.robert.twitterclient.images.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.robert.twitterclient.R;
import com.example.robert.twitterclient.TwitterClientApp;
import com.example.robert.twitterclient.entities.Image;
import com.example.robert.twitterclient.images.ImagesPresenter;
import com.example.robert.twitterclient.images.di.ImagesComponent;
import com.example.robert.twitterclient.images.ui.adapters.ImagesAdapter;
import com.example.robert.twitterclient.images.ui.adapters.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment implements ImagesView, OnItemClickListener {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.container)
    FrameLayout container;

    @Inject
    ImagesAdapter adapter;
    @Inject
    ImagesPresenter presenter;


    public ImagesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);

        setupInjection();
        setupRecyclerView();
        presenter.getImageTweets();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        presenter.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onItemClick(Image image) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.getTweetUrl()));
        startActivity(intent);
    }

    private void setupInjection() {
        TwitterClientApp app = (TwitterClientApp) getActivity().getApplication();
        ImagesComponent imagesComponent = app.getImagesComponent(this, this, this);
        imagesComponent.inject(this);
//        presenter = imagesComponent.getPresenter();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showImages() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImages() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setContent(List<Image> items) {
        adapter.setItems(items);
    }

}
