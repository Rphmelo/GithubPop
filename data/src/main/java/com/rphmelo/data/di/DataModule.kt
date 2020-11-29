package com.rphmelo.data.di

import com.rphmelo.data.repository.RepoPullRequestRepositoryImpl
import com.rphmelo.data.repository.RepoRepositoryImpl
import com.rphmelo.domain.repository.RepoPullRequestRepository
import com.rphmelo.domain.repository.RepoRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<RepoRepository> {
        RepoRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
    factory<RepoPullRequestRepository> {
        RepoPullRequestRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, localDataModule)