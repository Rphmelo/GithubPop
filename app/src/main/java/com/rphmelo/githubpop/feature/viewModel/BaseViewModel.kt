package com.rphmelo.githubpop.feature.viewModel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {

    val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()

        super.onCleared()
    }
}