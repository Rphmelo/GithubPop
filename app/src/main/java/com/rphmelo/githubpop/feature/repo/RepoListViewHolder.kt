package com.rphmelo.githubpop.feature.repo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rphmelo.design.extensions.setOnSingleClickListener
import com.rphmelo.domain.entities.Repo
import com.rphmelo.githubpop.R
import com.rphmelo.githubpop.extension.orZero
import kotlinx.android.synthetic.main.item_repo_list.view.*

class RepoListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Repo?, onItemClick: OnItemClick?) {
        with(itemView) {
            with(uivRepoItem) {
                item?.owner?.apply {
                    avatarUrl?.let { setAvatarUser(it, context.getString(R.string.userImageAcessibility)) }
                    login?.let { setUserName(it, context.getString(R.string.userNameAcessibility, it)) }
                }

                item?.fullName?.let { setUserFullName(it, context.getString(R.string.userNameAcessibility, it)) }
            }
            with(riRepoItem) {
                item?.apply {
                    name?.let { setTitle(it) }
                    description?.let { setDescription(it) }
                }
            }

            item?.apply {
                with(fiForkCount) {
                    forksCount?.let { setCount(it.toString()) }
                    setImageView(R.drawable.ic_fork, context.getString(R.string.forkImageAcessibility))
                }
                with(fiStarsCount) {
                    starsCount?.let { setCount(it.toString()) }
                    setImageView(R.drawable.ic_star, context.getString(R.string.starImageAcessibility))
                }
                llFooterInfo.contentDescription = context.getString(R.string.footerInfoAcessibility, forksCount?.toString(), starsCount?.toString())
            }

            setOnSingleClickListener {
                onItemClick?.invoke(item)
            }
        }
    }
}