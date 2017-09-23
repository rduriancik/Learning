package com.example.robert.mvvmsampleapp.di

import com.example.robert.mvvmsampleapp.viewmodel.ProjectListViewModel
import com.example.robert.mvvmsampleapp.viewmodel.ProjectViewModel
import dagger.Subcomponent

/**
 * Created by robert on 23.9.2017.
 */
@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun projectListViewModel(): ProjectListViewModel
    fun projectViewModel(): ProjectViewModel
}