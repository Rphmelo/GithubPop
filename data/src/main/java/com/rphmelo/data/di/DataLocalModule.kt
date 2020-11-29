package com.rphmelo.data.di

import com.rphmelo.data.local.database.GitPopDatabase
import com.rphmelo.data.local.mapper.RepoMapper
import com.rphmelo.data.local.mapper.RepoPullRequestMapper
import com.rphmelo.data.local.source.RepoLocalDataSource
import com.rphmelo.data.local.source.RepoLocalDataSourceImpl
import com.rphmelo.data.local.source.RepoPullRequestLocalDataSource
import com.rphmelo.data.local.source.RepoPullRequestLocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {
    single { GitPopDatabase.createDataBase(androidContext()).repoDao() }
    single { GitPopDatabase.createDataBase(androidContext()).repoPullRequestDao() }
    factory { RepoMapper() }
    factory { RepoPullRequestMapper() }
    factory<RepoLocalDataSource> { RepoLocalDataSourceImpl( dao = get(), mapper = get()) }
    factory<RepoPullRequestLocalDataSource> { RepoPullRequestLocalDataSourceImpl( dao = get(), mapper = get()) }
}