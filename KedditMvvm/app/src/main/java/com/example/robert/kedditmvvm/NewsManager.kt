package com.example.robert.kedditmvvm

import com.example.robert.kedditmvvm.api.NewsAPI
import com.example.robert.kedditmvvm.api.NewsRestAPI
import io.reactivex.Observable

/**
 * NewsManager allows you to request news from Reddit API
 */
class NewsManager(private val apiNews: NewsAPI = NewsRestAPI()) {

    /**
     * Returns Reddit News paginated by the given limit
     *
     * @param after indicates the next page to navigate
     * @param limit the number of news to request
     */
    fun getNews(after: String, limit: String = "10"): Observable<RedditNews> =
            Observable.create { subscriber ->
                val callResponse = apiNews.getNews(after, limit)
                val response = callResponse.execute()

                if (response.isSuccessful) {
                    val dataResponse = response.body()?.data
                    val news = dataResponse?.children?.map {
                        val item = it.data
                        RedditNewsItem(item.author, item.title, item.num_comments, item.created,
                                item.thumbnail, item.url)
                    } ?: emptyList<RedditNewsItem>()

                    val redditNews = RedditNews(
                            dataResponse?.after ?: "",
                            dataResponse?.before ?: "",
                            news
                    )

                    subscriber.onNext(redditNews)
                    subscriber.onComplete()

                } else {
                    subscriber.onError(Throwable(response.message()))
                }
            }
}