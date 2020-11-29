package com.rphmelo.domain.usecases

import com.rphmelo.domain.entities.Repo
import com.rphmelo.domain.repository.RepoRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class RepoUseCase(
    private val repository: RepoRepository,
    private val scheduler: Scheduler
) {

    fun getRepos(language: String, pageNumber: Int, forceUpdate: Boolean): Observable<List<Repo>> {
        return repository.getRepos(language, pageNumber, forceUpdate)
            .subscribeOn(scheduler)
    }
}