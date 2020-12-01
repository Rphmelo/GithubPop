package com.rphmelo.githubpop.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.rphmelo.domain.entities.GitHubUser
import com.rphmelo.domain.entities.RepoPullRequest
import com.rphmelo.domain.usecases.RepoPullRequestUseCase
import com.rphmelo.githubpop.feature.viewModel.RepoPullRequestViewModel
import com.rphmelo.githubpop.feature.viewModel.ViewState
import com.rphmelo.githubpop.rules.RxSchedulersOverrideRule
import io.reactivex.Observable
import junit.framework.Assert.assertNull
import junit.framework.Assert.assertTrue
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

    @get:Rule
    var rxSchedulerRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Mock
    lateinit var repoPullRequestUseCase: RepoPullRequestUseCase

    private lateinit var viewModel: RepoPullRequestViewModel
    @Mock
    lateinit var observer: Observer<ViewState<List<RepoPullRequest>>>

    @Mock
    lateinit var observerStates: Observer<Pair<Int, Int>>

    @Mock
    lateinit var lifecycleOwner: LifecycleOwner

    lateinit var lifecycle: Lifecycle

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lifecycle = LifecycleRegistry(lifecycleOwner)
        viewModel = RepoPullRequestViewModel(repoPullRequestUseCase)
        viewModel.state.observeForever(observer)
        viewModel.pullStateCount.observeForever(observerStates)
    }

    @Test
    fun `Given ViewModel is instantiated When view model is initialized Then state should be Loading`() {
        val login = "teste"
        val name = "teste"
        whenever(repoPullRequestUseCase.getPullRequests(login, name, false)).thenReturn(null)
        assertNull(viewModel.state.value)
        assertTrue(viewModel.state.hasObservers())
    }

    @Test
    fun `Given ViewModel is instantiated When useCase return error Then state should be Failed Throwable`() {
        val login = "teste"
        val name = "teste"
        val throwable = Throwable()
        whenever(repoPullRequestUseCase.getPullRequests(login, name, false)).thenReturn(Observable.error(throwable))
        viewModel.getPullRequests(login, name, false)
        verify(observer).onChanged(ViewState.Loading)
        verify(observer).onChanged(ViewState.Failed(throwable))
    }

    @Test
    fun `Given ViewModel is instantiated When useCase return success Then state should be Success RepoPullRequest`() {
        val login = "teste"
        val name = "teste"
        val gitHubUser = GitHubUser(11, "https://www.avatar.com", "Rphmelo")
        val repoPullRequestFake = RepoPullRequest(1, "Java Repo", "A Java repo.", "open", gitHubUser)
        val repoPullRequestListFake: List<RepoPullRequest> = arrayListOf(repoPullRequestFake)
        whenever(repoPullRequestUseCase.getPullRequests(login, name, false)).thenReturn(Observable.just(repoPullRequestListFake))
        viewModel.getPullRequests(login, name)
        verify(observer).onChanged(ViewState.Loading)
        verify(observerStates).onChanged(Pair(1, 0))
        verify(observer).onChanged(ViewState.Success(repoPullRequestListFake))
    }
}