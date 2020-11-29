package com.rphmelo.domain.di

import com.rphmelo.domain.usecases.RepoPullRequestUseCase
import com.rphmelo.domain.usecases.RepoUseCase
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

val useCaseModule = module {
    factory { RepoUseCase(repository = get(), scheduler = Schedulers.io()) }
    factory { RepoPullRequestUseCase(repository = get(), scheduler = Schedulers.io()) }
}

val domainModule = listOf(useCaseModule)