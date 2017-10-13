package com.example.robert.kedditmvvm.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by robert on 13.10.2017.
 */
class RestAPI {
    private val redditApi: RedditApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        redditApi = retrofit.create(RedditApi::class.java)
    }

    fun getNews(after: String, limit: String) = redditApi.getTop(after, limit)
}