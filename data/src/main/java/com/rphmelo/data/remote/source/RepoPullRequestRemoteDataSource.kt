package com.rphmelo.data.remote.source

import com.rphmelo.domain.entities.RepoPullRequest
import io.reactivex.Observable

interface RepoPullRequestRemoteDataSource {
    fun getRepoPullRequests(fullName: String): Observable<List<RepoPullRequest>>
}