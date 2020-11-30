package com.rphmelo.data.repository

import com.rphmelo.data.local.source.RepoPullRequestLocalDataSource
import com.rphmelo.data.remote.source.RepoPullRequestRemoteDataSource
import com.rphmelo.domain.entities.RepoPullRequest
import com.rphmelo.domain.repository.RepoPullRequestRepository
import io.reactivex.Observable

class RepoPullRequestRepositoryImpl(
    private val localDataSource: RepoPullRequestLocalDataSource,
    private val remoteDataSource: RepoPullRequestRemoteDataSource
) : RepoPullRequestRepository {

    override fun getRepoPullRequests(
        login: String,
        name: String,
        forceUpdate: Boolean
    ): Observable<List<RepoPullRequest>> {
        return if (forceUpdate) {
            getReposPullRequestRemote(login, name, forceUpdate)
        } else {
            localDataSource.getRepoPullRequests()
                .flatMap { repoPullRequestList ->
                    when {
                        repoPullRequestList.isEmpty() -> getReposPullRequestRemote(login, name, false)
                        else -> Observable.just(repoPullRequestList)
                    }
                }
        }
    }

    private fun getReposPullRequestRemote(login: String, name: String, isUpdate: Boolean): Observable<List<RepoPullRequest>> {
        return remoteDataSource.getRepoPullRequests(login, name)
            .flatMap { repoPullRequestList ->
                if (isUpdate) {
                    localDataSource.updateAll(repoPullRequestList)
                } else {
                    localDataSource.insertAll(repoPullRequestList)
                }
                Observable.just(repoPullRequestList)
            }
    }
}