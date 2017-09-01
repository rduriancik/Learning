package com.example.robert.photofeed.main;

import android.location.Location;

/**
 * Created by robert on 1.9.2017.
 */

public interface UploadInteractor {
    void execute(Location location, String path);
}
