package com.rphmelo.data.repository

import com.rphmelo.data.local.source.RepoLocalDataSource
import com.rphmelo.data.local.source.RepoPullRequestLocalDataSource
import com.rphmelo.data.remote.source.RepoPullRequestRemoteDataSource
import com.rphmelo.data.remote.source.RepoRemoteDataSource
import com.rphmelo.domain.entities.Repo
import com.rphmelo.domain.entities.RepoPullRequest
import io.reactivex.Observable

class RepoRepositoryImpl(
    private val repoLocalDataSource: RepoLocalDataSource,
    private val repoRemoteDataSource: RepoRemoteDataSource,
    private val repoPullRequestLocalDataSource: RepoPullRequestLocalDataSource,
    private val repoPullRequestRemoteDataSource: RepoPullRequestRemoteDataSource
) : RepoRepository {

    override fun getRepos(language: String, pageNumber: Int, forceUpdate: Boolean): Observable<List<Repo>> {
        return if (forceUpdate)
            getReposRemote(language, pageNumber, forceUpdate)
        else
            repoLocalDataSource.getRepos()
                .flatMap { repoList ->
                    when{
                        repoList.isEmpty() -> getReposRemote(language, pageNumber, false)
                        else -> Observable.just(repoList)
                    }
                }
    }

    override fun getRepoPullRequests(
        fullName: String,
        forceUpdate: Boolean
    ): Observable<List<RepoPullRequest>> {
        return if (forceUpdate) {
            getReposPullRequestRemote(fullName, forceUpdate)
        } else {
            repoPullRequestLocalDataSource.getRepoPullRequests()
                .flatMap { repoPullRequestList ->
                    when {
                        repoPullRequestList.isEmpty() -> getReposPullRequestRemote(fullName, false)
                        else -> Observable.just(repoPullRequestList)
                    }
                }
        }
    }

    private fun getReposRemote(language: String, pageNumber: Int, isUpdate: Boolean): Observable<List<Repo>> {
        return repoRemoteDataSource.getRepos(language, pageNumber)
            .flatMap { repoList ->
                if (isUpdate) {
                    repoLocalDataSource.updateAll(repoList)
                } else {
                    repoLocalDataSource.insertAll(repoList)
                }
                Observable.just(repoList)
            }
    }

    private fun getReposPullRequestRemote(fullName: String, isUpdate: Boolean): Observable<List<RepoPullRequest>> {
        return repoPullRequestRemoteDataSource.getRepoPullRequests(fullName)
            .flatMap { repoPullRequestList ->
                if (isUpdate) {
                    repoPullRequestLocalDataSource.updateAll(repoPullRequestList)
                } else {
                    repoPullRequestLocalDataSource.insertAll(repoPullRequestList)
                }
                Observable.just(repoPullRequestList)
            }
    }

}