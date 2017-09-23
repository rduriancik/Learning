package com.example.robert.mvvmsampleapp.di

import android.arch.lifecycle.ViewModelProvider
import com.example.robert.mvvmsampleapp.service.repository.GithubService
import com.example.robert.mvvmsampleapp.viewmodel.ProjectViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by robert on 23.9.2017.
 */
@Module(subcomponents = arrayOf(ViewModelSubComponent::class))
class AppModule {
    @Provides
    @Singleton
    fun provideGithubService(): GithubService = Retrofit.Builder().baseUrl(GithubService.HTTPS_API_GITHUB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)

    @Provides
    @Singleton
    fun provideViewModelFactory(viewModelSubComponent: ViewModelSubComponent): ViewModelProvider.Factory =
            ProjectViewModelFactory(viewModelSubComponent)
}