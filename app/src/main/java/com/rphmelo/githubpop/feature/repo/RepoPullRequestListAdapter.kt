package com.rphmelo.githubpop.feature.repo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rphmelo.design.extensions.inflate
import com.rphmelo.domain.entities.RepoPullRequest
import com.rphmelo.githubpop.R

class RepoPullRequestListAdapter : RecyclerView.Adapter<RepoPullRequestListViewHolder>() {

    private var repoPullRequestList: List<RepoPullRequest> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoPullRequestListViewHolder {
        val view = parent.inflate(R.layout.item_repo_pull_request_list, false)
        return RepoPullRequestListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoPullRequestListViewHolder, position: Int) {
        holder.bind(repoPullRequestList[position])
    }

    override fun getItemCount(): Int = repoPullRequestList.size

    fun addAll(repoPullRequestList: List<RepoPullRequest>) {
        this.repoPullRequestList = repoPullRequestList
        notifyDataSetChanged()
    }
}