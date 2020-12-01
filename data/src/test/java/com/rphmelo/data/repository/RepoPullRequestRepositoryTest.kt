package com.rphmelo.data.repository

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.rphmelo.data.local.source.RepoPullRequestLocalDataSource
import com.rphmelo.data.remote.mapper.RepoMapper
import com.rphmelo.data.remote.source.RepoPullRequestRemoteDataSource
import com.rphmelo.data.rules.RxSchedulersOverrideRule
import com.rphmelo.domain.entities.GitHubUser
import com.rphmelo.domain.entities.RepoPullRequest
import com.rphmelo.domain.repository.RepoPullRequestRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RepoPullRequestRepositoryTest {

    @Rule
    @JvmField var testSchedulerRule = RxSchedulersOverrideRule()

    @Mock
    lateinit var remoteDataSource: RepoPullRequestRemoteDataSource

    @Mock
    lateinit var localDataSource: RepoPullRequestLocalDataSource

    private lateinit var repoPullRequestRepository: RepoPullRequestRepository

    private val mapper = RepoMapper()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repoPullRequestRepository = RepoPullRequestRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `Given getRepos is called When call is forceUpdate Then should call remoteDataSource`() {
        val gitHubUser = GitHubUser(11, "https://www.avatar.com", "Rphmelo")
        val repoPullRequestFake = RepoPullRequest(1, "Java Repo", "A Java repo.", "open", gitHubUser)
        val repoPullRequestListFake: List<RepoPullRequest> = arrayListOf(repoPullRequestFake)

        val login = "Rphmelo"
        val name = "GithubPop"
        whenever(remoteDataSource.getRepoPullRequests(login, name)).thenReturn(Observable.just(repoPullRequestListFake))

        repoPullRequestRepository.getRepoPullRequests(login, name, true)

        verify(remoteDataSource).getRepoPullRequests(login, name)
    }

    @Test
    fun `Given getRepos is called When call is not forceUpdate Then should call localDataSource`() {
        val gitHubUser = GitHubUser(11, "https://www.avatar.com", "Rphmelo")
        val repoPullRequestFake = RepoPullRequest(1, "Java Repo", "A Java repo.", "open", gitHubUser)
        val repoPullRequestListFake: List<RepoPullRequest> = arrayListOf(repoPullRequestFake)

        val login = "Rphmelo"
        val name = "GithubPop"
        whenever(localDataSource.getRepoPullRequests()).thenReturn(Observable.just(repoPullRequestListFake))

        repoPullRequestRepository.getRepoPullRequests(login, name, false)

        verify(localDataSource).getRepoPullRequests()
    }
}