package com.example.robert.flickrlike.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by robert on 20.8.2017.
 */

interface FlickrService {
    @GET("/services/rest/?method=flickr.photos.search")
    Call<PhotosResponse> search(@Query("tags") String tags, @Query("per_page") int perPage);
}
