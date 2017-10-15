package com.example.robert.kedditmvvm


import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.robert.kedditmvvm.adapter.NewsAdapter
import com.example.robert.kedditmvvm.common.InfiniteScrollListener
import com.example.robert.kedditmvvm.common.extensions.inflate
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment() {

    companion object {
        private const val KEY_REDDIT_NEWS = "redditNews"
    }

    private var redditNews: RedditNews? = null
    @Inject lateinit var newsManager: NewsManager
    private val subscriptions = CompositeDisposable()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            container?.inflate(R.layout.fragment_news)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.apply {
            setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayoutManager))
        }

        initAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            (news_list.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    override fun onPause() {
        super.onPause()
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
        subscriptions.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (news_list.adapter as NewsAdapter).getNews()
        if (redditNews != null && news.size > 0) {
            outState.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }

    private fun requestNews() {
        val subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ retrievedNews ->
                    redditNews = retrievedNews
                    (news_list.adapter as NewsAdapter).addNews(retrievedNews.news)
                }, { e ->
                    Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                })

        subscriptions.add(subscription)
    }

    private fun initAdapter() {
        news_list.adapter = NewsAdapter()
    }

}
