package com.rphmelo.design.rules

import android.content.Context
import android.view.View
import android.view.ViewGroup

interface ViewCreator<T : View> {
    fun createView(context: Context, parentView: ViewGroup): T
}
