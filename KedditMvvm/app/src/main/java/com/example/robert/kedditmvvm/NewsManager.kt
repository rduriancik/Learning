package com.example.robert.kedditmvvm

import com.example.robert.kedditmvvm.api.RestAPI
import io.reactivex.Observable

/**
 * Created by robert on 13.10.2017.
 */
class NewsManager(private val api: RestAPI = RestAPI()) {
    fun getNews(limit: String = "10"): Observable<List<RedditNewsItem>> =
            Observable.create { subscriber ->
                val callResponse = api.getNews("", limit)
                val response = callResponse.execute()

                if (response.isSuccessful) {
                    val news = response.body()?.data?.children?.map {
                        val item = it.data
                        RedditNewsItem(item.author, item.title, item.num_comments, item.created,
                                item.thumbnail, item.url)
                    } ?: emptyList<RedditNewsItem>()

                    subscriber.onNext(news)
                    subscriber.onComplete()

                } else {
                    subscriber.onError(Throwable(response.message()))
                }
            }
}