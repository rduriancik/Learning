package com.example.robert.mvvmsampleapp.view.callback

import com.example.robert.mvvmsampleapp.service.model.Project

/**
 * Created by robert on 12.9.2017.
 */
interface ProjectClickCallback {
    fun onClick(project: Project)
}