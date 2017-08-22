package com.example.robert.flickrlike.photoActivity.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robert.flickrlike.R;
import com.example.robert.flickrlike.entities.Photo;
import com.example.robert.flickrlike.libs.base.ImageLoader;
import com.example.robert.flickrlike.photoActivity.PhotoPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoActivity extends AppCompatActivity implements PhotoView, SwipeGestureListener {

    public static final String TAGS_KEY = "tags_key";
    public static final String PHOTOS_KEY = "photos_key";
    public static final String PAGE_KEY = "page_key";

    public static final int SWIPE_UP = 0;
    public static final int SWIPE_DOWN = 1;
    public static final int SWIPE_RIGHT = 3;
    public static final int SWIPE_LEFT = 4;

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.imgTitle)
    TextView imgTitle;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private ArrayList<Photo> photos;
    private int page = 0;
    private String tags;
    private Photo currentPhoto;

    PhotoPresenter presenter;
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        setupInjection();
        setupGestureDetector();

        if (savedInstanceState != null) {
            photos = savedInstanceState.getParcelable(PHOTOS_KEY);
            tags = savedInstanceState.getString(TAGS_KEY);
            page = savedInstanceState.getInt(PAGE_KEY);
        } else {
            tags = getIntent().getStringExtra(TAGS_KEY);
            if (tags == null) {
                throw new IllegalStateException("MainActivity must provide tags");
            }
        }

        if (photos == null || photos.isEmpty()) {
            presenter.findPhotos(tags, page);
        }
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
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(TAGS_KEY, tags);
        outState.putParcelableArrayList(PHOTOS_KEY, photos);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
    }

    private void setupGestureDetector() {
        final GestureDetector detector = new GestureDetector(this, new SwipeGestureDetector(this));
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        });
    }

    public void setPhoto(Photo photo) {
        if (photo != null) {
            this.currentPhoto = photo;
            imageLoader.load(image, currentPhoto.getPhotoUrl());
        }
    }

    @Override
    public void showNextPhoto() {
        if (photos != null && !photos.isEmpty()) {
            setPhoto(photos.get(0));
            photos.remove(0);
        } else {
            presenter.findPhotos(tags, ++page);
        }
    }

    @Override
    public void showProgress() {
        image.setVisibility(View.GONE);
        imgTitle.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent() {
        progressBar.setVisibility(View.GONE);
        image.setVisibility(View.VISIBLE);
        imgTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAnimation(int type) {
        Animation animation;

        switch (type) {
            case SWIPE_UP:
                animation = AnimationUtils.loadAnimation(this, R.anim.dismiss_animation_up);
                break;
            case SWIPE_DOWN:
                animation = AnimationUtils.loadAnimation(this, R.anim.dismiss_animation_down);
                break;
            case SWIPE_RIGHT:
                animation = AnimationUtils.loadAnimation(this, R.anim.save_animation_right);
                break;
            case SWIPE_LEFT:
                animation = AnimationUtils.loadAnimation(this, R.anim.save_animation_left);
                break;
            default:
                throw new IllegalArgumentException("Unknown type of the animation");
        }

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                presenter.getNextPhoto();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.setAnimation(animation);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveSwipeRight() {
        presenter.onSwipePhoto(currentPhoto, SWIPE_RIGHT);
    }

    @Override
    public void onSaveSwipeLeft() {
        presenter.onSwipePhoto(currentPhoto, SWIPE_LEFT);
    }

    @Override
    public void onDismissSwipeUp() {
        presenter.onSwipePhoto(currentPhoto, SWIPE_UP);
    }

    @Override
    public void onDismissSwipeDown() {
        presenter.onSwipePhoto(currentPhoto, SWIPE_DOWN);
    }
}
