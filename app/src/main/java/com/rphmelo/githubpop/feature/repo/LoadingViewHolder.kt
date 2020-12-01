package com.rphmelo.githubpop.feature.repo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_loading.view.*

class LoadingViewHolder(view: View) :  RecyclerView.ViewHolder(view) {
    val progressBar = itemView.pbLoading
}