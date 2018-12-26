package com.example.robert.twitterclient.api;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by robert on 23.7.2017.
 */

public class CustomTwitterApiClient extends TwitterApiClient {

    public CustomTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public TimelineService getTimelineService() {
        return getService(TimelineService.class);
    }
}
