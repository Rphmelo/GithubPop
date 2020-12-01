package com.rphmelo.githubpop.feature.repoPullRequest

import android.os.Bundle
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
import com.rphmelo.domain.entities.RepoPullRequest
import com.rphmelo.githubpop.R
import com.rphmelo.githubpop.feature.viewModel.RepoPullRequestViewModel
import com.rphmelo.githubpop.feature.viewModel.ViewState
import com.rphmelo.githubpop.feature.utils.StateViewDelegate
import kotlinx.android.synthetic.main.activity_repo.*
import kotlinx.android.synthetic.main.fragment_repo_pull_request.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoPullRequestFragment : Fragment() {

    private val repoItem by lazy { arguments?.getSerializable(REPO_ITEM_KEY) as? Repo }
    private val repoPullRequestListAdapter by lazy { RepoPullRequestListAdapter() }
    private val viewModel: RepoPullRequestViewModel by viewModel()

    private var stateView: Int by StateViewDelegate(
        LOADING to ::showLoading,
        SUCCESS to ::showResult,
        ERROR to ::showError,
        EMPTY to ::showEmptyState
    )

    companion object {
        const val LOADING = 1
        const val SUCCESS = 2
        const val ERROR = 3
        const val EMPTY = 4

        private const val REPO_ITEM_KEY = "REPO_ITEM_KEY"

        fun newInstance(repoItem: Repo?) = RepoPullRequestFragment().apply {
            arguments = Bundle().apply {
                putSerializable(REPO_ITEM_KEY, repoItem)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_pull_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setupViewModel()
    }

    private fun configureRepoList(repoPullRequestList: List<RepoPullRequest>) {
        if(repoPullRequestList.isEmpty()) {
            stateView = EMPTY
            return
        }

        stateView = SUCCESS
        with(rvRepoPullRequestList) {
            addItemDecoration(SpacesItemDecoration(8))
            setHasFixedSize(true)
            adapter = repoPullRequestListAdapter.apply {
                addAll(repoPullRequestList)
            }
        }
    }

    private fun setError(throwable: Throwable) {
        stateView =
            ERROR
        Toast.makeText(context, throwable.message, Toast.LENGTH_LONG).show()
    }

    private fun setLoading() {
        stateView =
            LOADING
    }

    private fun showLoading() {
        rvRepoPullRequestList.gone()
        tvRepoStates.gone()
        repoPullRequestScreenLoading.visible()
        esRepoPullRequest.gone()
    }

    private fun showResult() {
        tvRepoStates.visible()
        rvRepoPullRequestList.visible()
        repoPullRequestScreenLoading.gone()
        esRepoPullRequest.gone()
    }

    private fun showError() {
        rvRepoPullRequestList.gone()
        repoPullRequestScreenLoading.gone()
        esRepoPullRequest.gone()
    }

    private fun showEmptyState() {
        esRepoPullRequest.visible()
        tvRepoStates.gone()
        rvRepoPullRequestList.gone()
        repoPullRequestScreenLoading.gone()
    }

    private fun setupViewModel() {
        viewModel.getPullRequests(repoItem?.owner?.login ?: "Rphmelo", repoItem?.name  ?: "GithubPop", true)

        viewModel.state.observe(this, Observer { viewState ->
            when(viewState) {
                is ViewState.Success -> {
                    configureRepoList(viewState.data)
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

    private fun setToolbar() {
        activity?.toolbar?.apply {
            title = repoItem?.name ?: "Repository name"
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }
}