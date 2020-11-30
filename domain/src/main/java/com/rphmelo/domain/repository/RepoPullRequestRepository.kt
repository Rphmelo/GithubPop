package com.rphmelo.domain.repository

import com.rphmelo.domain.entities.RepoPullRequest
import io.reactivex.Observable

interface RepoPullRequestRepository {
    fun getRepoPullRequests(login: String, name: String, forceUpdate: Boolean): Observable<List<RepoPullRequest>>
}