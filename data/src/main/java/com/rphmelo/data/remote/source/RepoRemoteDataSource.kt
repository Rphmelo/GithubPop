package com.rphmelo.data.remote.source

import com.rphmelo.domain.entities.Repo
import io.reactivex.Observable

interface RepoRemoteDataSource {
    fun getRepos(language: String, pageNumber: Int): Observable<List<Repo>>
}