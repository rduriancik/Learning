package com.example.robert.kedditmvvm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListProvider
import android.arch.paging.PagedList

/**
 * Created by robert on 24.10.2017.
 */
class NewsViewModel(newPagedListProvider: LivePagedListProvider<String, RedditNewsItem>, application: Application) :
        AndroidViewModel(application) {
    val newsList = newPagedListProvider.create("",
            PagedList.Config.Builder()
                    .setPageSize(10)
                    .setEnablePlaceholders(false)
                    .build())
}