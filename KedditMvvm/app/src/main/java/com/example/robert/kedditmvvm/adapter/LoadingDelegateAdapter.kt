package com.example.robert.kedditmvvm.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.robert.kedditmvvm.R
import com.example.robert.kedditmvvm.common.adapter.ViewType
import com.example.robert.kedditmvvm.common.adapter.ViewTypeDelegateAdapter
import com.example.robert.kedditmvvm.common.inflate

/**
 * Created by robert on 12.10.2017.
 */
class LoadingDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            ProgressBarViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {

    }

    class ProgressBarViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item_loading)) {

    }
}