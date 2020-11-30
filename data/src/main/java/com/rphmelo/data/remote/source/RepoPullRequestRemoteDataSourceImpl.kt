package com.rphmelo.data.remote.source

import com.rphmelo.data.remote.api.GitHubApi
import com.rphmelo.data.remote.mapper.RepoPullRequestMapper
import com.rphmelo.domain.entities.RepoPullRequest
import io.reactivex.Observable

class RepoPullRequestRemoteDataSourceImpl(
    private val api: GitHubApi,
    private val mapper: RepoPullRequestMapper
) : RepoPullRequestRemoteDataSource {
    override fun getRepoPullRequests(login: String, name: String): Observable<List<RepoPullRequest>> {
        return api.getRepoPullRequests(login, name).map { mapper.map(it) }
    }
}