package com.rphmelo.domain.di

import com.rphmelo.domain.usecases.RepoPullRequestUseCase
import com.rphmelo.domain.usecases.RepoUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { RepoUseCase(repository = get()) }
    factory { RepoPullRequestUseCase(repository = get()) }
}

val domainModule = listOf(useCaseModule)