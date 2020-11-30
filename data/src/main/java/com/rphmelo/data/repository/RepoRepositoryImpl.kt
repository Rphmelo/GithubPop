package com.rphmelo.data.repository

import com.rphmelo.data.local.source.RepoLocalDataSource
import com.rphmelo.data.remote.source.RepoRemoteDataSource
import com.rphmelo.domain.entities.Repo
import com.rphmelo.domain.repository.RepoRepository
import io.reactivex.Observable

class RepoRepositoryImpl(
    private val localDataSource: RepoLocalDataSource,
    private val remoteDataSource: RepoRemoteDataSource
) : RepoRepository {

    override fun getRepos(q: String, pageNumber: Int, forceUpdate: Boolean): Observable<List<Repo>> {
        return if (forceUpdate) {
            getReposRemote(q, pageNumber, forceUpdate)
        }
        else {
            localDataSource.getRepos()
                .flatMap { repoList ->
                    when {
                        repoList.isEmpty() -> getReposRemote(q, pageNumber, false)
                        else -> Observable.just(repoList)
                    }
                }
        }
    }

    private fun getReposRemote(q: String, pageNumber: Int, isUpdate: Boolean): Observable<List<Repo>> {
        return remoteDataSource.getRepos(q, pageNumber)
            .flatMap { repoList ->
                if (isUpdate) {
                    localDataSource.updateAll(repoList)
                } else {
                    localDataSource.insertAll(repoList)
                }
                Observable.just(repoList)
            }
    }
}