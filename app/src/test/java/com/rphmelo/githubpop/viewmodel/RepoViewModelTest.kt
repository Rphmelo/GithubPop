package com.rphmelo.githubpop.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.whenever
import com.rphmelo.domain.entities.GitHubUser
import com.rphmelo.domain.entities.Repo
import com.rphmelo.domain.usecases.RepoUseCase
import com.rphmelo.githubpop.feature.viewModel.RepoViewModel
import com.rphmelo.githubpop.feature.viewModel.ViewState
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RepoViewModelTest {
    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repoUseCase: RepoUseCase

    private val scheduler = Schedulers.io()

    private lateinit var viewModel: RepoViewModel
    @Mock
    lateinit var observer: Observer<ViewState<List<Repo>>>

    @Mock
    lateinit var lifecycleOwner: LifecycleOwner

    lateinit var lifecycle: Lifecycle

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lifecycle = LifecycleRegistry(lifecycleOwner)
        viewModel = RepoViewModel(repoUseCase, scheduler)
        viewModel.state.observeForever(observer)
    }

    @Test
    fun `Given ViewModel is instantiated When view model is initialized Then state should be Loading`() {
        val q = "teste"
        val pageNumber = 2
        whenever(repoUseCase.getRepos(q, pageNumber, true)).thenReturn(null)
        assert(viewModel.state.value == ViewState.Loading)
        assertTrue(viewModel.state.hasObservers())
    }

    @Test
    fun `Given ViewModel is instantiated When useCase return error Then state should be Failed Throwable`() {
        val q = "teste"
        val pageNumber = 2
        val throwable = Throwable()
        whenever(repoUseCase.getRepos(q, pageNumber, false)).thenReturn(Observable.error(throwable))
        viewModel.getRepos(q, pageNumber)
        assert(viewModel.state.value == ViewState.Failed(throwable))
        assertTrue(viewModel.state.hasObservers())
    }

    @Test
    fun `Given ViewModel is instantiated When useCase return success Then state should be Success Repo`() {
        val q = "teste"
        val pageNumber = 2
        val repo = Repo(1, "", "", GitHubUser(), "", 1,1)
        val repoList: List<Repo> = arrayListOf(repo)
        whenever(repoUseCase.getRepos(q, pageNumber, false)).thenReturn(Observable.just(repoList))
        viewModel.getRepos(q, pageNumber)
        assertEquals(ViewState.Loading, viewModel.state.value)
        assertTrue(viewModel.state.hasObservers())
    }
}