package com.rphmelo.githubpop.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rphmelo.githubpop.extension.orZero

abstract class EndlessScrollListener(private val adapterListener: EndlessScrollAdapterListener) : RecyclerView.OnScrollListener() {
    abstract fun onLoadMore()
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        // only load more items if it's currently not loading
        if (!adapterListener.isLoading()) {
            // only load more items if the last visible item on the screen is the last item
            if ((recyclerView.layoutManager as? LinearLayoutManager)?.findLastCompletelyVisibleItemPosition().orZero() >= recyclerView.layoutManager?.itemCount.orZero() - 1 ) {
                // add progress bar, the loading footer
                adapterListener.showLoadingItem()
                // load more items after 2 seconds, and remove the loading footer
                onLoadMore()
            }
        }
    }
}