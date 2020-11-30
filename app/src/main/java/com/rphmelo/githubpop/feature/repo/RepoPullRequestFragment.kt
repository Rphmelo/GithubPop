package com.rphmelo.githubpop.feature.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rphmelo.githubpop.R
import kotlinx.android.synthetic.main.activity_repo.*

class RepoPullRequestFragment : Fragment() {

    private val repoName by lazy { arguments?.getString(REPO_NAME_KEY) }

    companion object {
        private const val REPO_NAME_KEY = "REPO_NAME_KEY"

        fun newInstance(repoName: String) = RepoPullRequestFragment().apply {
            arguments = Bundle().apply {
                putString(REPO_NAME_KEY, repoName)
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
            title = repoName ?: "Repository name"
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }
}