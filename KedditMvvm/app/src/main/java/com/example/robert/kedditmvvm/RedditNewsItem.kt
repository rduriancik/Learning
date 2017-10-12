package com.example.robert.kedditmvvm

import com.example.robert.kedditmvvm.common.adapter.AdapterConstants
import com.example.robert.kedditmvvm.common.adapter.ViewType

/**
 * Created by robert on 12.10.2017.
 */
data class RedditNewsItem(val author: String,
                          val title: String,
                          val numComments: Int,
                          val created: Long,
                          val thumbnail: String,
                          val url: String) : ViewType {
    override fun getViewType() = AdapterConstants.NEWS
}