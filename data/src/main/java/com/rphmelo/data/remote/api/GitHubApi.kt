package com.rphmelo.data.remote.api

import com.rphmelo.data.remote.model.RepoPayload
import com.rphmelo.data.remote.model.RepoPullRequestPayload
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/repositories")
    fun getRepos(
        @Query("language") language: String,
        @Query("pageNumber") pageNumber: Int,
        @Query("sort") sort: String = "stars"
    ): Observable<List<RepoPayload>>

    @GET("repos/{fullName}/pulls")
    fun getRepoPullRequests(
        @Path("fullName") fullName: String
    ): Observable<List<RepoPullRequestPayload>>
}