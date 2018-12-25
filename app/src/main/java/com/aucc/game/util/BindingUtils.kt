package com.aucc.game.util

import android.databinding.BindingAdapter
import android.view.View

class BindingUtils {

    companion object {

        @JvmStatic
        @BindingAdapter("android:visible")
        fun setVisibility(view: View, value: Boolean) {
            view.visibility = if (value) View.VISIBLE else View.GONE
        }
    }
}