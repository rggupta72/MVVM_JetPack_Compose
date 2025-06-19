package com.example.composepoc.utils

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter

const val INVALID_RESOURCE_ID = 0

@BindingAdapter("textOrEmpty")
fun setTextOrEmpty(textView: TextView, text: String?) =
    setTextOrDefault(
        textView = textView,
        textResource = null,
        defaultText = text ?: ""
    )

@BindingAdapter("visibleOrGone")
fun setVisibleOrGone(
    view: View,
    visible: Boolean,
) {
    val x = 10
    println(x)
    Log.e("Hi", "dummy$x")
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("textResource", "defaultText")
fun setTextOrDefault(
    textView: TextView,
    @StringRes textResource: Int?,
    defaultText: String,
) {
    textView.text =
        if (textResource == null || textResource == INVALID_RESOURCE_ID) {
            defaultText
        } else {
            textView.context.getString(textResource)
        }
}


