package com.example.robert.kedditmvvm.model.api

import com.example.robert.kedditmvvm.api.RedditNewsResponse
import retrofit2.Call

/**
 * Created by robert on 14.10.2017.
 */
interface NewsAPI {
    fun getNews(after: String, before: String, limit: String): Call<RedditNewsResponse>
}