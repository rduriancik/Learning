package com.example.robert.kedditmvvm.news.viewmodel

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.paging.LivePagedListProvider
import com.example.robert.kedditmvvm.model.entities.RedditNewsItem

/**
 * Created by robert on 28.10.2017.
 */
class NewsViewModelFactory(private val newsPagedListProvider: LivePagedListProvider<String, RedditNewsItem>,
                           private val application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            NewsViewModel(newsPagedListProvider, application) as T
}