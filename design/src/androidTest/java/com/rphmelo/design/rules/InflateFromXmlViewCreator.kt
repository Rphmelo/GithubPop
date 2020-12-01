package com.rphmelo.design.rules

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class InflateFromXmlViewCreator<T : View>(private val id: Int) : ViewCreator<T> {
    override fun createView(context: Context, parentView: ViewGroup): T {
        val layoutInflater = LayoutInflater.from(context)
        return layoutInflater.inflate(id, parentView, false) as T
    }
}
