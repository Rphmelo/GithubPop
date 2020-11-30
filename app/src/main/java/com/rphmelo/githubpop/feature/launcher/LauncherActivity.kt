package com.rphmelo.githubpop.feature.launcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rphmelo.githubpop.R
import com.rphmelo.githubpop.feature.repo.RepoActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RepoActivity.start(this)
    }
}
