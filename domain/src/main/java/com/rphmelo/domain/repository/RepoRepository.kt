package com.rphmelo.domain.repository

import com.rphmelo.domain.entities.Repo
import com.rphmelo.domain.entities.RepoPullRequest
import io.reactivex.Observable

interface RepoRepository {
    fun getRepos(forceUpdate: Boolean): Observable<List<Repo>>
    fun getRepoPullRequests(fullName: String, forceUpdate: Boolean): Observable<List<RepoPullRequest>>
}
