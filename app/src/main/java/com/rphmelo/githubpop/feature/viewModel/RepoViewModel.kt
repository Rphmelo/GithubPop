package com.rphmelo.githubpop.feature.viewModel

import androidx.lifecycle.MutableLiveData
import com.rphmelo.domain.entities.Repo
import com.rphmelo.domain.usecases.RepoUseCase
import com.rphmelo.githubpop.feature.utils.RxComposer
import io.reactivex.rxkotlin.plusAssign

class RepoViewModel(
    private val useCase: RepoUseCase
): BaseViewModel() {

    val state = MutableLiveData<ViewState<List<Repo>>>()

    fun getRepos(q: String, pageNumber: Int, forceUpdate: Boolean = false) {
        disposables += useCase.getRepos(q, pageNumber, forceUpdate)
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