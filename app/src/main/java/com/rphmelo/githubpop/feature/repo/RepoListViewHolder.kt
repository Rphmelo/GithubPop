package com.rphmelo.githubpop.feature.repo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rphmelo.design.extensions.setOnSingleClickListener
import com.rphmelo.domain.entities.Repo
import com.rphmelo.githubpop.R
import kotlinx.android.synthetic.main.item_repo_list.view.*

class RepoListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Repo, onItemClick: OnItemClick?) {
        with(itemView) {
            with(uivRepoItem) {
                item.owner?.apply {
                    avatarUrl?.let { setAvatarUser(it) }
                    login?.let { setUserName(it) }
                }

                item.fullName?.let { setUserFullName(it) }
            }
            with(riRepoItem) {
                item.apply {
                    name?.let { setTitle(it) }
                    description?.let { setDescription(it) }
                }
            }

//            with(fiForkCount) {
//                item.apply {
//                    with(fiForkCount) {
//                        forksCount?.let { setCount(it.toString()) }
//                        setImageView(R.drawable.ic_arrow_back)
//                    }
//                    with(fiStarsCount) {
//                        forksCount?.let { setCount(it.toString()) }
//                        setImageView(R.drawable.ic_arrow_back)
//                    }
//                }
//            }

            setOnSingleClickListener {
                onItemClick?.invoke(item)
            }
        }
    }
}