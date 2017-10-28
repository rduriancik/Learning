package com.example.robert.kedditmvvm.di.fragments

import android.app.Application
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListProvider
import com.example.robert.kedditmvvm.model.NewsDataSource
import com.example.robert.kedditmvvm.model.entities.RedditNewsItem
import com.example.robert.kedditmvvm.news.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by robert on 28.10.2017.
 */
@Module
class NewsFragmentModule {

    @Provides
    fun provideNewsViewModelFactory(newsPagedListProvider: LivePagedListProvider<String, RedditNewsItem>,
                                    application: Application): NewsViewModelFactory =
            NewsViewModelFactory(newsPagedListProvider, application)

    @Provides
    fun provideNewsPagedListProvider(newsDataSource: NewsDataSource): LivePagedListProvider<String, RedditNewsItem> =
            object : LivePagedListProvider<String, RedditNewsItem>() {
                override fun createDataSource(): DataSource<String, RedditNewsItem> = newsDataSource
            }
}