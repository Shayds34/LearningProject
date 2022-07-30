package com.example.common.utils

import android.view.View
import android.widget.TextView

fun TextView.setTextOrGone(text: String?) {
    this.visibility = if (text.isNullOrBlank()) View.GONE else View.VISIBLE
    this.text = text
}