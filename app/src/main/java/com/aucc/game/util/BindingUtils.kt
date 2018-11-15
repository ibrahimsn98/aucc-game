package com.aucc.game.util

import android.annotation.SuppressLint
import android.databinding.BindingAdapter
import android.view.View
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class BindingUtils {

    companion object {

        @JvmStatic
        @BindingAdapter("android:visible")
        fun setVisibility(view: View, value: Boolean) {
            view.visibility = if (value) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter("android:dateText")
        @SuppressLint("SimpleDateFormat")
        fun timeToDate(textView: TextView, time: Long) {
            val sdf = SimpleDateFormat("dd MMM HH:mm")
            textView.text =  sdf.format(Date(time))
        }
    }
}