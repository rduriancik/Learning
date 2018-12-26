package com.example.robert.kedditmvvm.model.api

import com.example.robert.kedditmvvm.api.RedditNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by robert on 13.10.2017.
 */
interface RedditApi {
    @GET("top.json")
    fun getTop(@Query("after") after: String,
               @Query("before") before: String,
               @Query("limit") limit: String): Call<RedditNewsResponse>
}