package com.rphmelo.githubpop.feature.repo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rphmelo.design.extensions.inflate
import com.rphmelo.domain.entities.Repo
import com.rphmelo.githubpop.R

typealias OnItemClick = (Repo) -> Unit

class RepoListAdapter : RecyclerView.Adapter<RepoListViewHolder>() {

    private var repoList: List<Repo> = arrayListOf()
    private var onItemClick: OnItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val view = parent.inflate(R.layout.item_repo_list, false)
        return RepoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        holder.bind(repoList[position], onItemClick)
    }

    override fun getItemCount(): Int = repoList.size

    fun addAll(repoList: List<Repo>, onItemClick: OnItemClick) {
        this.repoList = repoList
        this.onItemClick = onItemClick
        notifyDataSetChanged()
    }
}