package com.rphmelo.githubpop.feature.repo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rphmelo.design.extensions.inflate
import com.rphmelo.design.extensions.visible
import com.rphmelo.domain.entities.Repo
import com.rphmelo.githubpop.R
import com.rphmelo.githubpop.utils.EndlessScrollAdapterListener

typealias OnItemClick = (Repo?) -> Unit

class RepoListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    EndlessScrollAdapterListener {

    private var repoList: MutableList<Repo?> = mutableListOf()
    private var onItemClick: OnItemClick? = null
    private var pageNumber = 1

    companion object {
        const val VIEW_TYPE_LOADING = 1
        const val VIEW_TYPE_ITEM = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_LOADING) {
            val view = parent.inflate(R.layout.item_repo_list, false)
            RepoListViewHolder(view)
        } else {
            val view = parent.inflate(R.layout.item_loading, false)
            RepoLoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? RepoListViewHolder)?.bind(repoList[position], onItemClick)
        (holder as? RepoLoadingViewHolder)?.progressBar?.visible()
    }

    override fun getItemCount(): Int = repoList.size

    override fun getItemViewType(position: Int): Int {
        return if(repoList[position] != null) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    override fun isLoading(): Boolean {
        return repoList.last() == null
    }

    override fun showLoadingItem() {
        repoList.add(null)
        notifyItemInserted(repoList.size - 1)
    }

    override fun hideLoadingItem() {
        val position = repoList.size - 1
        if(position > 0 && repoList[position] == null) {
            repoList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getPageNumber() = pageNumber

    fun addAll(repoList: MutableList<Repo?>, onItemClick: OnItemClick) {
        val lastListPosition = if (this.repoList.size == 0) {
            this.repoList.size
        } else {
            this.repoList.size - 1
        }

        this.onItemClick = onItemClick
        this.repoList.addAll(repoList)
        pageNumber++

        notifyItemRangeInserted(lastListPosition, this.repoList.size)
    }
}