package com.rphmelo.design.extensions

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan

fun String.styledTextColor(value: String, color: Int?): SpannableString {
    return SpannableString(this).apply {
        val startIndex = indexOf(value)
        val endIndex = startIndex + value.length

        color?.let {
            setSpan(ForegroundColorSpan(it), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}