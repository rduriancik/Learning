package com.example.robertduriancik.youtubeplayer;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {

    static final String GOOGLE_API_KEY = "AIzaSyAkxy2iFkZ0UHoAPqzNugRT5rjS-Ve5adU";
    static final String YOUTUBE_VIDEO_ID = "-6w5KTZx6qE";
    static final String YOUTUBE_PLAYLIST = "PLXtTjtWmQhg0N8akNvcdhtgzJtRtgu6Vf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube);
//        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.activity_youtube);
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);

        YouTubePlayerView player = new YouTubePlayerView(this);
        player.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(player);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
