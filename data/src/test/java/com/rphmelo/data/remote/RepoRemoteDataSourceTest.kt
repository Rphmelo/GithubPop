package com.rphmelo.data.remote

import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.whenever
import com.rphmelo.data.remote.api.GitHubApi
import com.rphmelo.data.remote.mapper.RepoMapper
import com.rphmelo.data.remote.model.GitHubUserPayload
import com.rphmelo.data.remote.model.RepoPayload
import com.rphmelo.data.remote.model.ResponsePayload
import com.rphmelo.data.remote.source.RepoRemoteDataSource
import com.rphmelo.data.remote.source.RepoRemoteDataSourceImpl
import com.rphmelo.data.rules.RxSchedulersOverrideRule
import io.reactivex.Observable
import okhttp3.Response
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RepoRemoteDataSourceTest {

    @Rule
    @JvmField var testSchedulerRule = RxSchedulersOverrideRule()

    @Mock
    lateinit var githubApi: GitHubApi

    lateinit var dataSource: RepoRemoteDataSource

    private val mapper = RepoMapper()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dataSource = RepoRemoteDataSourceImpl(githubApi, mapper)
    }

    @Test
    fun `Given remote data is requested When call getRepos Then should call githubApi`() {
        val gitHubUser = GitHubUserPayload(11, "https://www.avatar.com", "Rphmelo")
        val repoFake = RepoPayload(1, "GitPop", "GitPop/Rphmelo", "An app to show repositories.", 20, 12, gitHubUser)
        val repoListFake: List<RepoPayload> = arrayListOf(repoFake)
        val responsePayload = ResponsePayload(totalCount = 20, incompleteResults = false, items = repoListFake)

        val q = "language:java"
        val pageNumber = 1
        whenever(githubApi.getRepos(q, pageNumber)).thenReturn(Observable.just(responsePayload))

        dataSource.getRepos(q, pageNumber)

        inOrder(githubApi) {
            verify(githubApi).getRepos(q, pageNumber)
        }
    }
}