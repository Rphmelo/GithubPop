package com.rphmelo.githubpop.di

import com.rphmelo.githubpop.feature.viewModel.BaseViewModel
import com.rphmelo.githubpop.feature.viewModel.RepoPullRequestViewModel
import com.rphmelo.githubpop.feature.viewModel.RepoViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { BaseViewModel() }
    viewModel { RepoViewModel(useCase = get(), uiScheduler = AndroidSchedulers.mainThread()) }
    viewModel { RepoPullRequestViewModel(useCase = get(), uiScheduler = AndroidSchedulers.mainThread()) }
}