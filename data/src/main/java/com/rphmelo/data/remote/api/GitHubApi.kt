package com.rphmelo.data.remote.api

import com.rphmelo.data.remote.model.RepoPullRequestPayload
import com.rphmelo.data.remote.model.ResponsePayload
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("/search/repositories")
    fun getRepos(
        @Query("q") q: String,
        @Query("page") pageNumber: Int,
        @Query("sort") sort: String = "stars"
    ): Observable<ResponsePayload>

    @GET("/repos/{login}/{name}/pulls")
    fun getRepoPullRequests(
        @Path("login") login: String,
        @Path("name") name: String
    ): Observable<List<RepoPullRequestPayload>>
}