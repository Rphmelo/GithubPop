package com.rphmelo.data.local.source

import com.rphmelo.data.local.database.RepoDao
import com.rphmelo.data.local.mapper.RepoMapper
import com.rphmelo.domain.entities.Repo
import io.reactivex.Observable

class RepoLocalDataSourceImpl(
    private val dao: RepoDao,
    private val mapper: RepoMapper
) : RepoLocalDataSource {
    override fun getRepos(): Observable<List<Repo>> {
        return dao.getRepos().map { mapper.map(it) }
    }
    override fun insertAll(repoList: List<Repo>) {
        dao.insertAll(mapper.mapRepoToCache(repoList))
    }
    override fun updateAll(repoList: List<Repo>) {
        dao.updateData(mapper.mapRepoToCache(repoList))
    }
}