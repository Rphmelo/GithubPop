package com.rphmelo.githubpop.feature.repo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rphmelo.githubpop.R
import com.rphmelo.githubpop.feature.utils.fragmentTransaction
import kotlinx.android.synthetic.main.activity_repo.*

class RepoActivity : AppCompatActivity() {

    companion object {
        fun start(source: Activity) {
            val intent = Intent(source, RepoActivity::class.java)
            source.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)
        loadRepoFragment()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.flContainer)

        (currentFragment as? RepoPullRequestFragment)?.let {
            toolbar.apply {
                title = getString(R.string.repo_screen_title)
                navigationIcon = null
            }
        }

        fragmentTransaction { pop { finish() } }
    }

    private fun loadRepoFragment() {
        fragmentTransaction(R.id.flContainer) {
            add(RepoFragment.newInstance())
        }
    }
}
