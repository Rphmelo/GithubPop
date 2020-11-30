package com.rphmelo.githubpop.feature.repo

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
import com.rphmelo.domain.entities.Repo
import com.rphmelo.githubpop.R
import com.rphmelo.githubpop.feature.repo.viewModel.RepoViewModel
import com.rphmelo.githubpop.feature.repo.viewModel.ViewState
import com.rphmelo.githubpop.feature.utils.StateViewDelegate
import com.rphmelo.githubpop.feature.utils.fragmentTransaction
import kotlinx.android.synthetic.main.fragment_repo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoFragment : Fragment() {

    private val viewModel: RepoViewModel by viewModel()
    private val repoListAdapter by lazy { RepoListAdapter() }

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
        setupViewModel()
    }

    private fun configureRepoList(repoList: List<Repo>) {
        stateView = SUCCESS
        with(rvRepoList) {
            val dividerItemDecoration = DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
            addItemDecoration(dividerItemDecoration)
            setHasFixedSize(true)
            adapter = repoListAdapter.apply {
                addAll(repoList, ::onRepoItemClick)
            }
        }
    }

    private fun onRepoItemClick(item: Repo) {
        fragmentTransaction(R.id.flContainer) {
            add(RepoPullRequestFragment.newInstance(item))
        }
    }

    private fun setError(throwable: Throwable) {
        stateView = ERROR
        Toast.makeText(context, throwable.message, Toast.LENGTH_LONG).show()
    }

    private fun setLoading() {
        stateView = LOADING
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

    private fun setupViewModel() {
        viewModel.getRepos("language:java", 1, true)

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
}