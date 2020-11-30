package com.rphmelo.domain.repository

import com.rphmelo.domain.entities.Repo
import io.reactivex.Observable

interface RepoRepository {
    fun getRepos(q: String, pageNumber: Int, forceUpdate: Boolean): Observable<List<Repo>>
}