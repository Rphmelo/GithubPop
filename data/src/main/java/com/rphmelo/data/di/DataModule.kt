package com.rphmelo.data.di

import com.rphmelo.data.repository.RepoRepository
import com.rphmelo.data.repository.RepoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<RepoRepository> {
        RepoRepositoryImpl(
            repoLocalDataSource = get(),
            repoPullRequestLocalDataSource = get(),
            repoRemoteDataSource = get(),
            repoPullRequestRemoteDataSource = get()
        )
    }
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, localDataModule)