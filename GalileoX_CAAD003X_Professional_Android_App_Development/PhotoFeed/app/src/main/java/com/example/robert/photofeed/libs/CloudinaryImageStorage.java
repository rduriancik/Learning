package com.example.robert.photofeed.libs;

import android.os.AsyncTask;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.robert.photofeed.libs.base.EventBus;
import com.example.robert.photofeed.libs.base.ImageStorage;
import com.example.robert.photofeed.libs.base.ImageStorageFinishedListener;

import java.io.File;
import java.util.Map;

/**
 * Created by robert on 1.9.2017.
 */

public class CloudinaryImageStorage implements ImageStorage {
    private EventBus eventBus;
    private Cloudinary cloudinary;

    public CloudinaryImageStorage(EventBus eventBus, Cloudinary cloudinary) {
        this.eventBus = eventBus;
        this.cloudinary = cloudinary;
    }

    @Override
    public String getImageUrl(String id) {
        return cloudinary.url().generate(id);
    }

    @Override
    public void upload(final File file, final String id, final ImageStorageFinishedListener listener) {
        new AsyncTask<Void, Void, Void>() {
            boolean success = false;

            @Override
            protected Void doInBackground(Void... voids) {
                Map params = ObjectUtils.asMap("public_id", id);

                try {
                    cloudinary.uploader().upload(file, params);
                    success = true;
                } catch (Exception e) {
                    listener.onError(e.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (success) {
                    listener.onSuccess();
                }
            }
        }.execute();
    }
}
