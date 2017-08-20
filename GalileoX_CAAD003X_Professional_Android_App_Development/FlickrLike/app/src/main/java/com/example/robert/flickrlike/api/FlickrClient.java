package com.example.robert.flickrlike.api;

import android.support.annotation.NonNull;
import com.example.robert.flickrlike.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robert on 20.8.2017.
 */

class FlickrClient {
    private static final String FLICKR_API_BASE_URL = "https://api.flickr.com/";
    private static final FlickrService ourInstance ;

    static FlickrService getFlickrService() {
        return ourInstance;
    }

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FLICKR_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHttpClient())
                .build();

        ourInstance = retrofit.create(FlickrService.class);
    }

    private FlickrClient() {
    }

    private static OkHttpClient createHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient().newBuilder();
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl.Builder urlBuilder = request.url().newBuilder();
                urlBuilder.addQueryParameter("api_key", BuildConfig.FLICKR_API_KEY);
                urlBuilder.addQueryParameter("format", "json");
                urlBuilder.addQueryParameter("nojsoncallback", "1");

                request = request.newBuilder().url(urlBuilder.build()).build();
                return chain.proceed(request);
            }
        });

        return client.build();
    }
}
