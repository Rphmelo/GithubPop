package com.rphmelo.design.utils

import android.os.SystemClock
import android.view.View

abstract class OnSingleClickListener(private val timeInterval: Int = 1000) : View.OnClickListener {

    private var lastTimeClicked = 0L

    abstract fun onSingleClick(view: View?)

    override fun onClick(view: View?) {
        val currentTime = SystemClock.elapsedRealtime()
        if(currentTime - lastTimeClicked < timeInterval) return
        lastTimeClicked = currentTime
        onSingleClick(view)
    }
}