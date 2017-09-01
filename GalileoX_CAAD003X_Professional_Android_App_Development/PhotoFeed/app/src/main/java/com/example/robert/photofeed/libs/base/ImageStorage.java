package com.example.robert.photofeed.libs.base;

import java.io.File;

/**
 * Created by robert on 1.9.2017.
 */

public interface ImageStorage {
    String getImageUrl(String id);

    void upload(File file, String id, ImageStorageFinishedListener listener);
}
