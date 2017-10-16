package com.example.robert.kedditmvvm.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.robert.kedditmvvm.RedditNewsItem
import com.example.robert.kedditmvvm.common.adapter.AdapterConstants
import com.example.robert.kedditmvvm.common.adapter.ViewType
import com.example.robert.kedditmvvm.common.adapter.ViewTypeDelegateAdapter

/**
 * Created by robert on 12.10.2017.
 */
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            delegateAdapters.get(viewType).onCreateViewHolder(parent)

    override fun getItemViewType(position: Int) = items.get(position).getViewType()

    fun addNews(news: List<RedditNewsItem>) {
        val initPosition = items.size - 1
        items.addAll(initPosition, news)
        notifyItemRangeChanged(initPosition, news.size)
    }

    fun clearAndAddNews(news: List<RedditNewsItem>) {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, news.size)
    }

    fun getNews(): List<RedditNewsItem> =
            items.filter { it.getViewType() == AdapterConstants.NEWS }
                    .map { it as RedditNewsItem }

}