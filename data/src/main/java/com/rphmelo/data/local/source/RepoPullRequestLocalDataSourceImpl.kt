package com.rphmelo.data.local.source

import com.rphmelo.data.local.database.RepoPullRequestDao
import com.rphmelo.data.local.mapper.RepoPullRequestMapper
import com.rphmelo.domain.entities.RepoPullRequest
import io.reactivex.Observable

class RepoPullRequestLocalDataSourceImpl(
    private val dao: RepoPullRequestDao,
    private val mapper: RepoPullRequestMapper
) : RepoPullRequestLocalDataSource {
    override fun getRepoPullRequests(): Observable<List<RepoPullRequest>> {
        return dao.getRepoPullRequests().map { mapper.map(it) }
    }
    override fun insertAll(pullRequestList: List<RepoPullRequest>) {
        dao.insertAll(mapper.mapRepoToCache(pullRequestList))
    }
    override fun updateAll(pullRequestList: List<RepoPullRequest>) {
        dao.updateData(mapper.mapRepoToCache(pullRequestList))
    }
}