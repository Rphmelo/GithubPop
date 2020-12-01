package com.rphmelo.data.repository

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.rphmelo.data.local.source.RepoLocalDataSource
import com.rphmelo.data.remote.api.GitHubApi
import com.rphmelo.data.remote.mapper.RepoMapper
import com.rphmelo.data.remote.source.RepoRemoteDataSource
import com.rphmelo.data.rules.RxSchedulersOverrideRule
import com.rphmelo.domain.entities.GitHubUser
import com.rphmelo.domain.entities.Repo
import com.rphmelo.domain.repository.RepoRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RepoRepositoryTest {

    @Rule
    @JvmField var testSchedulerRule = RxSchedulersOverrideRule()

    @Mock
    lateinit var githubApi: GitHubApi

    @Mock
    lateinit var remoteDataSource: RepoRemoteDataSource

    @Mock
    lateinit var localDataSource: RepoLocalDataSource

    lateinit var repoRepository: RepoRepository

    private val mapper = RepoMapper()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repoRepository = RepoRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `Given getRepos is called When call is forceUpdate Then should call remoteDataSource`() {
        val gitHubUser = GitHubUser(11, "https://www.avatar.com", "Rphmelo")
        val repoFake = Repo(1, "GitPop", "GitPop/Rphmelo", gitHubUser, "An app to show repositories.", 20, 12)
        val repoListFake: List<Repo> = arrayListOf(repoFake)

        val q = "language:java"
        val pageNumber = 1
        whenever(remoteDataSource.getRepos(q, pageNumber)).thenReturn(Observable.just(repoListFake))

        repoRepository.getRepos(q, pageNumber, true)

        verify(remoteDataSource).getRepos(q, pageNumber)
    }

    @Test
    fun `Given getRepos is called When call is not forceUpdate Then should call localDataSource`() {
        val gitHubUser = GitHubUser(11, "https://www.avatar.com", "Rphmelo")
        val repoFake = Repo(1, "GitPop", "GitPop/Rphmelo", gitHubUser, "An app to show repositories.", 20, 12)
        val repoListFake: List<Repo> = arrayListOf(repoFake)

        val q = "language:java"
        val pageNumber = 1
        whenever(localDataSource.getRepos()).thenReturn(Observable.just(repoListFake))

        repoRepository.getRepos(q, pageNumber, false)

        verify(localDataSource).getRepos()
    }
}