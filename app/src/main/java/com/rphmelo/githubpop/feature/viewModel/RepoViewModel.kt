package com.rphmelo.githubpop.feature.viewModel

import androidx.lifecycle.MutableLiveData
import com.rphmelo.domain.entities.Repo
import com.rphmelo.domain.usecases.RepoUseCase
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign

class RepoViewModel(
    private val useCase: RepoUseCase,
    private val uiScheduler: Scheduler
): BaseViewModel() {

    val state = MutableLiveData<ViewState<List<Repo>>>().apply {
        value = ViewState.Loading
    }

    fun getRepos(q: String, pageNumber: Int, forceUpdate: Boolean = false) {
        disposables += useCase.getRepos(q, pageNumber, forceUpdate)
            .observeOn(uiScheduler)
            .doOnSubscribe { state.postValue(ViewState.Loading) }
            .subscribe(
                {
                    state.postValue(ViewState.Success(it))
                },
                {
                    state.postValue(ViewState.Failed(it))
                }
            )
    }
}