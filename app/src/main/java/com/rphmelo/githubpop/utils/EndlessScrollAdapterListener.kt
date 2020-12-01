package com.rphmelo.githubpop.utils

interface EndlessScrollAdapterListener {
    fun isLoading() : Boolean
    fun showLoadingItem()
    fun hideLoadingItem()
}