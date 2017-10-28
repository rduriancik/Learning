package com.example.robert.kedditmvvm


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.robert.kedditmvvm.adapter.NewsAdapter
import com.example.robert.kedditmvvm.common.extensions.inflate
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment() {

    @Inject lateinit var newsViewModelFactory: NewsViewModelFactory

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            container?.inflate(R.layout.fragment_news)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val newsAdapter = NewsAdapter()

        news_list.apply {
            setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            adapter = newsAdapter
        }

        ViewModelProviders.of(this, newsViewModelFactory).get(NewsViewModel::class.java).let {
            it.newsList.observe(this, Observer { pagedList -> newsAdapter.setList(pagedList) })
        }
    }
}
