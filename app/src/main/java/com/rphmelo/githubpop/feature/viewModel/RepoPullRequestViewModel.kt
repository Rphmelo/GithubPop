package com.rphmelo.githubpop.feature.viewModel

import androidx.lifecycle.MutableLiveData
import com.rphmelo.domain.entities.RepoPullRequest
import com.rphmelo.domain.usecases.RepoPullRequestUseCase
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign

class RepoPullRequestViewModel(
    private val useCase: RepoPullRequestUseCase,
    private val uiScheduler: Scheduler
): BaseViewModel() {

    val state = MutableLiveData<ViewState<List<RepoPullRequest>>>().apply {
        value = ViewState.Loading
    }

    fun getPullRequests(login: String, name: String, forceUpdate: Boolean = false) {
        disposables += useCase.getPullRequests(login, name, forceUpdate)
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