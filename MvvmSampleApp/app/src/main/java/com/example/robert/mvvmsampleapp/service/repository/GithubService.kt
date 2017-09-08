package com.example.robert.mvvmsampleapp.service.repository

import com.example.robert.mvvmsampleapp.service.model.Project
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by robert on 8.9.2017.
 */
interface GithubService {
    val HTTPS_API_GITHUB_URL: String
        get() = "https://api.github.com/"

    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<Project>>

    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<Project>
}