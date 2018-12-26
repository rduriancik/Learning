package com.example.robert.kedditmvvm.model

import android.arch.paging.KeyedDataSource
import com.example.robert.kedditmvvm.api.RedditDataResponse
import com.example.robert.kedditmvvm.api.RedditNewsResponse
import com.example.robert.kedditmvvm.model.api.NewsAPI
import com.example.robert.kedditmvvm.model.entities.RedditNewsItem
import retrofit2.Response

/**
 * Created by robert on 24.10.2017.
 */
class NewsDataSource(val newsAPI: NewsAPI) : KeyedDataSource<String, RedditNewsItem>() {

    override fun loadAfter(currentEndKey: String, pageSize: Int): MutableList<RedditNewsItem>? {
        val response = newsAPI.getNews(currentEndKey, "", pageSize.toString()).execute()
        return processResponse(response)
    }

    override fun loadInitial(pageSize: Int): MutableList<RedditNewsItem>? {
        val response = newsAPI.getNews("", "", pageSize.toString()).execute()
        return processResponse(response)
    }

    override fun loadBefore(currentBeginKey: String, pageSize: Int): MutableList<RedditNewsItem>? {
        val response = newsAPI.getNews("", currentBeginKey, pageSize.toString()).execute()
        return processResponse(response)
    }

    override fun getKey(item: RedditNewsItem): String = item.id

    private fun processResponse(response: Response<RedditNewsResponse>): MutableList<RedditNewsItem>? =
            if (response.isSuccessful) {
                val dataResponse = response.body()?.data
                convertResponseToRedditNews(dataResponse)
            } else {
                null
            }

    private fun convertResponseToRedditNews(response: RedditDataResponse?): MutableList<RedditNewsItem> {
        val news = response?.children?.map {
            val item = it.data
            RedditNewsItem(item.author, item.title, item.num_comments, item.created,
                    item.thumbnail, item.url, item.name)
        } ?: emptyList<RedditNewsItem>()

        return news.toMutableList()
    }

}