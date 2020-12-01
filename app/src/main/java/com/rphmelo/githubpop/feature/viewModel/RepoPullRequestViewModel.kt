package com.rphmelo.githubpop.feature.viewModel

import androidx.lifecycle.MutableLiveData
import com.rphmelo.domain.entities.Repo
import com.rphmelo.domain.entities.RepoPullRequest
import com.rphmelo.domain.entities.RepoPullRequestStates
import com.rphmelo.domain.usecases.RepoPullRequestUseCase
import com.rphmelo.githubpop.feature.utils.RxComposer
import io.reactivex.rxkotlin.plusAssign

class RepoPullRequestViewModel(
    private val useCase: RepoPullRequestUseCase
): BaseViewModel() {

    val state = MutableLiveData<ViewState<List<RepoPullRequest>>>()
    val pullStateCount = MutableLiveData<Pair<Int, Int>>()

    fun getPullRequests(login: String, name: String, forceUpdate: Boolean = false) {
        disposables += useCase.getPullRequests(login, name, forceUpdate)
            .compose(RxComposer.ioThread())
            .doOnSubscribe { state.postValue(ViewState.Loading) }
            .subscribe(
                {
                    val stateOpenCount = getFilterPullRequestCount(RepoPullRequestStates.OPEN.name, it)
                    val stateClosedCount = getFilterPullRequestCount(RepoPullRequestStates.CLOSED.name, it)
                    pullStateCount.postValue(Pair(stateOpenCount, stateClosedCount))
                    state.postValue(ViewState.Success(it))
                },
                {
                    state.postValue(ViewState.Failed(it))
                }
            )
    }

    private fun getFilterPullRequestCount(state: String, list: List<RepoPullRequest>): Int {
        return list.filter { it.state?.toUpperCase() == state.toUpperCase() }.size
    }
}