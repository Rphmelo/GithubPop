package com.rphmelo.githubpop.feature.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
    private var stateView: Int by StateViewDelegate(
        STATE_VIEW_LOADING to ::showLoading,
        STATE_VIEW_SUCCESS to ::showList,
        STATE_VIEW_ERROR to ::showError
    )

    companion object {
        const val STATE_VIEW_LOADING = 1
        const val STATE_VIEW_SUCCESS = 2
        const val STATE_VIEW_ERROR = 3

        fun newInstance() = RepoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun loadRepoPullRequestFragment(repoName: String) {
        fragmentTransaction(R.id.flContainer) {
            add(RepoPullRequestFragment.newInstance(repoName))
        }
    }

    private fun configureRepoList(repoList: List<Repo>) {
        stateView = STATE_VIEW_SUCCESS
        Toast.makeText(context, repoList[0].name, Toast.LENGTH_LONG).show()
    }

    private fun setError(throwable: Throwable) {
        stateView = STATE_VIEW_ERROR
        Toast.makeText(context, throwable.message, Toast.LENGTH_LONG).show()
    }

    private fun setLoading() {
        stateView = STATE_VIEW_LOADING
    }

    private fun showLoading() {
        repoScreenLoading.visible()
    }

    private fun showList() {
        repoScreenLoading.gone()
    }

    private fun showError() {
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