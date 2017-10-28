package com.example.robert.kedditmvvm.model.api

import javax.inject.Inject

/**
 * Created by robert on 13.10.2017.
 */
class NewsRestAPI @Inject constructor(private val redditApi: RedditApi) : NewsAPI {
    override fun getNews(after: String, before: String, limit: String) =
            redditApi.getTop(after, before, limit)
}