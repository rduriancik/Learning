package com.example.robert.kedditmvvm.api

/**
 * Created by robert on 13.10.2017.
 */
class RedditNewsResponse(val data: RedditDataResponse)

class RedditDataResponse(val children: List<RedditChildrenResponse>,
                         val after: String?,
                         val before: String?)

class RedditChildrenResponse(val data: RedditNewsDataResponse)

class RedditNewsDataResponse(val author: String,
                             val title: String,
                             val num_comments: Int,
                             val created: Long,
                             val thumbnail: String,
                             val url: String,
                             val name: String)