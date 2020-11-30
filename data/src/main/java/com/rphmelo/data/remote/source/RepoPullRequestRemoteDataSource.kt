package com.rphmelo.data.remote.source

import com.rphmelo.domain.entities.RepoPullRequest
import io.reactivex.Observable

interface RepoPullRequestRemoteDataSource {
    fun getRepoPullRequests(login: String, name: String): Observable<List<RepoPullRequest>>
}