package com.rphmelo.githubpop.feature.repo

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.rphmelo.design.extensions.gone
import com.rphmelo.design.extensions.visible
import com.rphmelo.design.utils.SpacesItemDecoration
import com.rphmelo.domain.entities.Repo
import com.rphmelo.githubpop.R
import com.rphmelo.githubpop.feature.repoPullRequest.RepoPullRequestFragment
import com.rphmelo.githubpop.feature.viewModel.RepoViewModel
import com.rphmelo.githubpop.feature.viewModel.ViewState
import com.rphmelo.githubpop.feature.utils.StateViewDelegate
import com.rphmelo.githubpop.feature.utils.fragmentTransaction
import com.rphmelo.githubpop.utils.EndlessScrollListener
import kotlinx.android.synthetic.main.fragment_repo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoFragment : Fragment() {

    private val viewModel: RepoViewModel by viewModel()
    private val repoListAdapter by lazy { RepoListAdapter() }
    private var rvRepoListState: Parcelable? = null

    private var stateView: Int by StateViewDelegate(
        LOADING to ::showLoading,
        SUCCESS to ::showList,
        ERROR to ::showError
    )

    companion object {
        const val LOADING = 1
        const val SUCCESS = 2
        const val ERROR = 3

        fun newInstance() = RepoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        setupViewModel()
    }

    private fun loadAdapterList(repoList: List<Repo>) {
        stateView = SUCCESS
        with(rvRepoList) {
            layoutManager?.onRestoreInstanceState(rvRepoListState)
            repoListAdapter.hideLoadingItem()
            adapter = repoListAdapter.apply {
                addAll(repoList.toMutableList(), ::onRepoItemClick)
            }
        }
    }

    private fun configureRecyclerView() {
        with(rvRepoList) {
            addItemDecoration(SpacesItemDecoration(8))
            setHasFixedSize(true)
            adapter = repoListAdapter
            addOnScrollListener(object : EndlessScrollListener(repoListAdapter) {
                override fun onLoadMore() {
                    rvRepoListState = rvRepoList.layoutManager?.onSaveInstanceState()
                    getRepos()
                }
            })
        }
    }

    private fun onRepoItemClick(item: Repo?) {
        fragmentTransaction(R.id.flContainer) {
            add(RepoPullRequestFragment.newInstance(item))
        }
    }

    private fun setError(throwable: Throwable) {
        stateView = ERROR
        Toast.makeText(context, throwable.message, Toast.LENGTH_LONG).show()
    }

    private fun setLoading() {
        if(repoListAdapter.getPageNumber() == 1) {
            stateView = LOADING
        }
    }

    private fun showLoading() {
        rvRepoList.gone()
        repoScreenLoading.visible()
    }

    private fun showList() {
        rvRepoList.visible()
        repoScreenLoading.gone()
    }

    private fun showError() {
        rvRepoList.gone()
        repoScreenLoading.gone()
    }

    private fun getRepos() {
        viewModel.getRepos("language:java", repoListAdapter.getPageNumber(), true)
    }

    private fun setupViewModel() {
        getRepos()

        viewModel.state.observe(this, Observer { viewState ->
            when(viewState) {
                is ViewState.Success -> {
                    loadAdapterList(viewState.data)
                }
                is ViewState.Loading -> {
                    setLoading()
                }
                is ViewState.Failed -> {
                    setError(viewState.throwable)
                }
            }
        })
    }
}