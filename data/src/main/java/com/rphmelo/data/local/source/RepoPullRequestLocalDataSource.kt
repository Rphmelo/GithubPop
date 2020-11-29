package com.rphmelo.data.local.source

import com.rphmelo.domain.entities.RepoPullRequest
import io.reactivex.Observable

interface RepoPullRequestLocalDataSource {
    fun getRepoPullRequests(): Observable<List<RepoPullRequest>>
    fun insertAll(pullRequestList: List<RepoPullRequest>)
    fun updateAll(pullRequestList: List<RepoPullRequest>)
}