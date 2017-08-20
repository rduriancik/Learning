package com.example.robert.flickrlike.main.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import com.example.robert.flickrlike.R;
import com.example.robert.flickrlike.main.MainPresenter;
import com.example.robert.flickrlike.main.di.DaggerMainComponent;
import com.example.robert.flickrlike.main.di.MainModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.tags)
    TextInputEditText tags;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupInjection();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @OnClick(R.id.btnSearch)
    public void onBtnSearchClicked() {
        presenter.onSearchClick(tags.getText().toString());
    }

    @OnClick(R.id.btnLikedPhotos)
    public void onBtnLikedPhotosClicked() {
        navigateToLikedPhotosActivity();
    }

    private void navigateToLikedPhotosActivity() {
        // TODO
    }

    @Override
    public void onError(String error) {
        tags.setError(error);
    }

    @Override
    public void navigateToPhotoActivity(String tags) {
        // TODO
    }
}
