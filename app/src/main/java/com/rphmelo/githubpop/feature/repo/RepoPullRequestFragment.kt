package com.rphmelo.githubpop.feature.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rphmelo.domain.entities.Repo
import com.rphmelo.githubpop.R
import kotlinx.android.synthetic.main.activity_repo.*

class RepoPullRequestFragment : Fragment() {

    private val repoItem by lazy { arguments?.getSerializable(REPO_ITEM_KEY) as? Repo }

    companion object {
        private const val REPO_ITEM_KEY = "REPO_ITEM_KEY"

        fun newInstance(repoItem: Repo) = RepoPullRequestFragment().apply {
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
    }

    private fun setToolbar() {
        activity?.toolbar?.apply {
            title = repoItem?.name ?: "Repository name"
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }
}