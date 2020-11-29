package com.rphmelo.data.repository

import com.rphmelo.domain.entities.Repo
import com.rphmelo.domain.entities.RepoPullRequest
import io.reactivex.Observable

interface RepoRepository {
    fun getRepos(language: String, pageNumber: Int, forceUpdate: Boolean): Observable<List<Repo>>
    fun getRepoPullRequests(fullName: String, forceUpdate: Boolean): Observable<List<RepoPullRequest>>
}