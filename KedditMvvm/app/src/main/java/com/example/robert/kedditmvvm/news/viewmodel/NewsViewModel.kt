package com.example.robert.kedditmvvm.news.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListProvider
import android.arch.paging.PagedList
import com.example.robert.kedditmvvm.model.entities.RedditNewsItem

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