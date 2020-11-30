package com.rphmelo.design.extensions

import android.view.View
import com.rphmelo.design.utils.OnSingleClickListener

fun View.setOnSingleClickListener(timeInterval: Int = 1000, action: ((view: View?) -> Unit)?) {
    setOnClickListener(object : OnSingleClickListener(timeInterval) {
        override fun onSingleClick(view: View?) {
            action?.invoke(view)
        }
    })
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}