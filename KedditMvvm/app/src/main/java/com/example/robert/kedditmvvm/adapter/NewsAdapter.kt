package com.example.robert.kedditmvvm.adapter

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.robert.kedditmvvm.R
import com.example.robert.kedditmvvm.RedditNewsItem
import com.example.robert.kedditmvvm.databinding.NewsItemBinding

/**
 * Created by robert on 12.10.2017.
 */
class NewsAdapter : PagedListAdapter<RedditNewsItem, NewsAdapter.NewsItemViewHolder>(diffCallback) {
    companion object {
        private val diffCallback = object : DiffCallback<RedditNewsItem>() {
            override fun areContentsTheSame(oldItem: RedditNewsItem, newItem: RedditNewsItem): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: RedditNewsItem, newItem: RedditNewsItem): Boolean =
                    oldItem.url == newItem.url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val binding = DataBindingUtil.inflate<NewsItemBinding>(LayoutInflater.from(parent.context),
                R.layout.news_item, parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.item = item as RedditNewsItem
        holder.binding.executePendingBindings()
    }

    class NewsItemViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)
}