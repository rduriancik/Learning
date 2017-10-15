package com.example.robert.kedditmvvm.di.fragments

import com.example.robert.kedditmvvm.api.NewsAPI
import com.example.robert.kedditmvvm.api.NewsRestAPI
import com.example.robert.kedditmvvm.api.RedditApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by robert on 15.10.2017.
 */

@Module
class NewsModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditApi =
            retrofit.create(RedditApi::class.java)

    @Provides
    @Singleton
    fun provideNewsAPI(redditApi: RedditApi): NewsAPI =
            NewsRestAPI(redditApi)
}