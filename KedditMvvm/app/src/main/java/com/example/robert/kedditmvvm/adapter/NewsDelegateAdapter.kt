package com.example.robert.kedditmvvm.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.robert.kedditmvvm.R
import com.example.robert.kedditmvvm.RedditNewsItem
import com.example.robert.kedditmvvm.common.adapter.ViewType
import com.example.robert.kedditmvvm.common.adapter.ViewTypeDelegateAdapter
import com.example.robert.kedditmvvm.databinding.NewsItemBinding

/**
 * Created by robert on 12.10.2017.
 */
class NewsDelegateAdapter() : ViewTypeDelegateAdapter {
    interface OnViewSelectedListener {
        fun onItemSelected(url: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<NewsItemBinding>(LayoutInflater.from(parent.context),
                R.layout.news_item, parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsItemViewHolder
        holder.binding.item = item as RedditNewsItem
        holder.binding.executePendingBindings()
    }

    class NewsItemViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)
}