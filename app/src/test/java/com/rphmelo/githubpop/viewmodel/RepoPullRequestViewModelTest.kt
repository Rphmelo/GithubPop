package com.rphmelo.githubpop.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.whenever
import com.rphmelo.domain.entities.GitHubUser
import com.rphmelo.domain.entities.RepoPullRequest
import com.rphmelo.domain.repository.RepoPullRequestRepository
import com.rphmelo.domain.usecases.RepoPullRequestUseCase
import com.rphmelo.githubpop.feature.viewModel.RepoPullRequestViewModel
import com.rphmelo.githubpop.feature.viewModel.ViewState
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RepoPullRequestViewModelTest {
    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repoPullRequestRepository: RepoPullRequestRepository

    private val scheduler = Schedulers.io()
    lateinit var repoPullRequestUseCase: RepoPullRequestUseCase

    private lateinit var viewModel: RepoPullRequestViewModel
    @Mock
    lateinit var observer: Observer<ViewState<List<RepoPullRequest>>>

    @Mock
    lateinit var lifecycleOwner: LifecycleOwner

    lateinit var lifecycle: Lifecycle

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repoPullRequestUseCase = RepoPullRequestUseCase(repoPullRequestRepository, scheduler)
        lifecycle = LifecycleRegistry(lifecycleOwner)
        viewModel = RepoPullRequestViewModel(repoPullRequestUseCase, scheduler)
        viewModel.state.observeForever(observer)
    }

    @Test
    fun `Given ViewModel is instantiated When view model is initialized Then state should be Loading`() {
        val login = "teste"
        val name = "teste"
        whenever(repoPullRequestRepository.getRepoPullRequests(login, name, false)).thenReturn(null)
        assert(viewModel.state.value == ViewState.Loading)
        Assert.assertTrue(viewModel.state.hasObservers())
    }

    @Test
    fun `Given ViewModel is instantiated When useCase return error Then state should be Failed Throwable`() {
        val login = "teste"
        val name = "teste"
        val throwable = Throwable()
        whenever(repoPullRequestRepository.getRepoPullRequests(login, name, false)).thenReturn(Observable.error(throwable))
        viewModel.getPullRequests(login, name)
        Assert.assertEquals(viewModel.state.value, ViewState.Loading)
        Assert.assertTrue(viewModel.state.hasObservers())
    }

    @Test
    fun `Given ViewModel is instantiated When useCase return success Then state should be Success RepoPullRequest`() {
        val login = "teste"
        val name = "teste"
        val gitHubUser = GitHubUser(11, "https://www.avatar.com", "Rphmelo")
        val repoPullRequestFake = RepoPullRequest(1, "Java Repo", "A Java repo.", "open", gitHubUser)
        val repoPullRequestListFake: List<RepoPullRequest> = arrayListOf(repoPullRequestFake)
        whenever(repoPullRequestRepository.getRepoPullRequests(login, name, false)).thenReturn(Observable.just(repoPullRequestListFake))
        viewModel.getPullRequests(login, name)
        Assert.assertEquals(ViewState.Loading, viewModel.state.value)
        Assert.assertTrue(viewModel.state.hasObservers())
    }
}