package com.example.robert.kedditmvvm

import io.reactivex.Observable

/**
 * Created by robert on 13.10.2017.
 */
class NewsManager {
    fun getNews(): Observable<List<RedditNewsItem>> =
            Observable.create { subscriber ->

                val news = mutableListOf<RedditNewsItem>()
                for (i in 1..10) {
                    news.add(RedditNewsItem(
                            "author$i",
                            "Title $i",
                            i, // number of comments
                            +1457207701L - i * 200, // time
                            "http://lorempixel.com/200/200/technics/$i", // image url
                            "url"
                    ))

                }

                subscriber.onNext(news)
            }
}