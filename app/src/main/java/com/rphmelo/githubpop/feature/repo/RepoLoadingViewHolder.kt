package com.rphmelo.githubpop.feature.repo

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_loading.view.*

class RepoLoadingViewHolder(view: View) :  RecyclerView.ViewHolder(view) {
    val progressBar: ProgressBar = itemView.pbLoading
}