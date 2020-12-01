package com.rphmelo.githubpop.feature.repoPullRequest

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rphmelo.domain.entities.RepoPullRequest
import com.rphmelo.githubpop.R
import kotlinx.android.synthetic.main.item_repo_pull_request_list.view.*

class RepoPullRequestListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: RepoPullRequest) {
        with(itemView) {
            with(uivRepoPullRequestItem) {
                item.user?.apply {
                    avatarUrl?.let { setAvatarUser(it, context.getString(R.string.userPullImageAcessibility)) }
                    login?.let {
                        setUserName(it)
                        setUserFullName(it)
                    }
                }
            }
            with(riRepoPullRequestItem) {
                item.apply {
                    title?.let { setTitle(it) }
                    body?.let { setDescription(it) }
                }
            }
        }
    }
}