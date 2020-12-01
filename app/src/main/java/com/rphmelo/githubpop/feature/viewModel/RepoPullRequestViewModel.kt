package com.rphmelo.githubpop.feature.viewModel

import androidx.lifecycle.MutableLiveData
import com.rphmelo.domain.entities.RepoPullRequest
import com.rphmelo.domain.usecases.RepoPullRequestUseCase
import com.rphmelo.githubpop.feature.utils.RxComposer
import io.reactivex.rxkotlin.plusAssign

class RepoPullRequestViewModel(
    private val useCase: RepoPullRequestUseCase
): BaseViewModel() {

    val state = MutableLiveData<ViewState<List<RepoPullRequest>>>()

    fun getPullRequests(login: String, name: String, forceUpdate: Boolean = false) {
        disposables += useCase.getPullRequests(login, name, forceUpdate)
            .compose(RxComposer.ioThread())
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