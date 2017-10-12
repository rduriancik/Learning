package com.example.robert.kedditmvvm.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.robert.kedditmvvm.R
import com.example.robert.kedditmvvm.RedditNewsItem
import com.example.robert.kedditmvvm.common.adapter.ViewType
import com.example.robert.kedditmvvm.common.adapter.ViewTypeDelegateAdapter
import com.example.robert.kedditmvvm.common.extensions.getFriendlyTime
import com.example.robert.kedditmvvm.common.extensions.inflate
import com.example.robert.kedditmvvm.common.extensions.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_item.*

/**
 * Created by robert on 12.10.2017.
 */
class NewsDelegateAdapter() : ViewTypeDelegateAdapter {
    interface OnViewSelectedListener {
        fun onItemSelected(url: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            NewsItemViewHolder(parent.inflate(R.layout.news_item))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsItemViewHolder
        holder.bind(item as RedditNewsItem)
    }

    class NewsItemViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        fun bind(item: RedditNewsItem) {
            img_thumbnail.load(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
        }
    }
}