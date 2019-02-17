package com.example.robert.mvvmsampleapp.service.model

import java.util.*

/**
 * Created by robert on 8.9.2017.
 */
data class Project(val id: Long, val name: String, val full_name: String,
                   val owner: User, val html_url: String, val description: String,
                   val url: String, val created_at: Date, val updated_at: Date,
                   val pushed_at: Date, val git_url: String, val ssh_url: String,
                   val clone_url: String, val svn_url: String, val homepage: String,
                   val stargazers_count: Int, val watchers_count: Int, val language: String,
                   val has_issues: Boolean, val has_downloads: Boolean, val has_wiki: Boolean,
                   val has_pages: Boolean, val forks_count: Int, val open_issues_count: Int,
                   val forks: Int, val open_issues: Int, val watchers: Int, val default_branch: String)
