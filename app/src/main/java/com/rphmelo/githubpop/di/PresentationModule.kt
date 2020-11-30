package com.rphmelo.githubpop.di

import com.rphmelo.githubpop.feature.repo.viewModel.BaseViewModel
import com.rphmelo.githubpop.feature.repo.viewModel.RepoViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { BaseViewModel() }
    viewModel { RepoViewModel(useCase = get(), uiScheduler = AndroidSchedulers.mainThread()) }
}