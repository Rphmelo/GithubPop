package com.rphmelo.data.remote

import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.whenever
import com.rphmelo.data.remote.api.GitHubApi
import com.rphmelo.data.remote.mapper.RepoPullRequestMapper
import com.rphmelo.data.remote.model.GitHubUserPayload
import com.rphmelo.data.remote.model.RepoPullRequestPayload
import com.rphmelo.data.remote.source.RepoPullRequestRemoteDataSource
import com.rphmelo.data.remote.source.RepoPullRequestRemoteDataSourceImpl
import com.rphmelo.data.rules.RxSchedulersOverrideRule
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RepoPullRequestRemoteDataSourceTest {

    @Rule
    @JvmField var testSchedulerRule = RxSchedulersOverrideRule()

    @Mock
    lateinit var githubApi: GitHubApi

    lateinit var dataSource: RepoPullRequestRemoteDataSource

    private val mapper = RepoPullRequestMapper()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dataSource = RepoPullRequestRemoteDataSourceImpl(githubApi, mapper)
    }

    @Test
    fun `Given remote data is requested When call getRepos Then should call githubApi`() {
        val gitHubUser = GitHubUserPayload(11, "https://www.avatar.com", "Rphmelo")
        val repoPullRequestFake = RepoPullRequestPayload(1, "Java Repo", "A Java repo.", "open", gitHubUser)
        val repoPullRequestListFake: List<RepoPullRequestPayload> = arrayListOf(repoPullRequestFake)

        val login = "Rphmelo"
        val name = "Rphmelo"
        whenever(githubApi.getRepoPullRequests(login, name)).thenReturn(Observable.just(repoPullRequestListFake))

        dataSource.getRepoPullRequests(login, name)

        inOrder(githubApi) {
            verify(githubApi).getRepoPullRequests(login, name)
        }
    }
}