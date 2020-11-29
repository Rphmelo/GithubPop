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
        fullName: String,
        forceUpdate: Boolean
    ): Observable<List<RepoPullRequest>> {
        return if (forceUpdate) {
            getReposPullRequestRemote(fullName, forceUpdate)
        } else {
            localDataSource.getRepoPullRequests()
                .flatMap { repoPullRequestList ->
                    when {
                        repoPullRequestList.isEmpty() -> getReposPullRequestRemote(fullName, false)
                        else -> Observable.just(repoPullRequestList)
                    }
                }
        }
    }

    private fun getReposPullRequestRemote(fullName: String, isUpdate: Boolean): Observable<List<RepoPullRequest>> {
        return remoteDataSource.getRepoPullRequests(fullName)
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