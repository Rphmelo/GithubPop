package com.rphmelo.domain.repository

import com.rphmelo.domain.entities.RepoPullRequest
import io.reactivex.Observable

interface RepoPullRequestRepository {
    fun getRepoPullRequests(fullName: String, forceUpdate: Boolean): Observable<List<RepoPullRequest>>
}