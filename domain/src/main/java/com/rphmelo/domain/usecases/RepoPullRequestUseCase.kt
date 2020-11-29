package com.rphmelo.domain.usecases

import com.rphmelo.domain.entities.RepoPullRequest
import com.rphmelo.domain.repository.RepoPullRequestRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class RepoPullRequestUseCase(
    private val repository: RepoPullRequestRepository,
    private val scheduler: Scheduler
) {
    fun getPullRequests(fullName: String, forceUpdate: Boolean): Observable<List<RepoPullRequest>> {
        return repository.getRepoPullRequests(fullName, forceUpdate)
            .subscribeOn(scheduler)
    }
}