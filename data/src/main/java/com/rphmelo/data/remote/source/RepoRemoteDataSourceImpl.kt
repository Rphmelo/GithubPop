package com.rphmelo.data.remote.source

import com.rphmelo.data.remote.api.GitHubApi
import com.rphmelo.data.remote.mapper.RepoMapper
import com.rphmelo.domain.entities.Repo
import io.reactivex.Observable

class RepoRemoteDataSourceImpl(
    private val api: GitHubApi,
    private val mapper: RepoMapper
) : RepoRemoteDataSource {
    override fun getRepos(language: String, pageNumber: Int): Observable<List<Repo>> {
        return api.getRepos(language, pageNumber).map { mapper.map(it) }
    }
}