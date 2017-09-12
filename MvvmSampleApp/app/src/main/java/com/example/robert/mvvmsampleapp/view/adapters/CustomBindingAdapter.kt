package com.example.robert.mvvmsampleapp.view.adapters

import android.databinding.BindingAdapter
import android.view.View

/**
 * Created by robert on 12.9.2017.
 */
class CustomBindingAdapter {
    companion object {
        @BindingAdapter("visibleGone")
        fun showHide(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}