package com.example.robertduriancik.weatherapp.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

/**
 * Created by robert-ntb on 4/24/17.
 */

val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)