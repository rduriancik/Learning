package com.example.robert.flickrlike.photoActivity.ui;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by robert on 21.8.2017.
 */

public class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    private SwipeGestureListener gestureListener;

    public SwipeGestureDetector(SwipeGestureListener gestureListener) {
        this.gestureListener = gestureListener;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float diffX = e2.getX() - e1.getX();
        float diffY = e2.getY() - e1.getY();

        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    gestureListener.onSaveSwipeRight();
                } else {
                    gestureListener.onSaveSwipeLeft();
                }
            }
        } else {
            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    gestureListener.onDismissSwipeDown();
                } else {
                    gestureListener.onDismissSwipeUp();
                }
            }
        }

        return super.onFling(e1, e2, velocityX, velocityY);
    }
}
