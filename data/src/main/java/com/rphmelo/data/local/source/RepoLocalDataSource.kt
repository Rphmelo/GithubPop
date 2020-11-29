package com.rphmelo.data.local.source

import com.rphmelo.domain.entities.Repo
import io.reactivex.Observable

interface RepoLocalDataSource {
    fun getRepos(): Observable<List<Repo>>
    fun insertAll(repoList: List<Repo>)
    fun updateAll(repoList: List<Repo>)
}