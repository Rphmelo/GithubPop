package com.rphmelo.githubpop.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rphmelo.githubpop.extension.orZero

abstract class EndlessScrollListener(private val adapterListener: EndlessScrollAdapterListener) : RecyclerView.OnScrollListener() {
    abstract fun onLoadMore()
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (!adapterListener.isLoading()) {
            if ((recyclerView.layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition().orZero() >= recyclerView.layoutManager?.itemCount.orZero() - 1 ) {
                adapterListener.showLoadingItem()
                onLoadMore()
            }
        }
    }
}