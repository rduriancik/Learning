package com.android.roomdbobserverdemo

import android.graphics.Paint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion

/**
 * Created by Robert Duriancik on 3/22/19.
 */

@BindingConversion
fun convertBooleanToVisibility(isVisible: Boolean) = if (isVisible) View.VISIBLE else View.INVISIBLE

@BindingAdapter("textStrikeThrough")
fun setTextStrikeThrough(textView: TextView, isStrikeThrough: Boolean) {
    if (isStrikeThrough) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}