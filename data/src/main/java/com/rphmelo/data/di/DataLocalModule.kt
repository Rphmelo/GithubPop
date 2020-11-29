package com.rphmelo.data.di

import android.content.Context
import androidx.room.Room
import com.rphmelo.data.BuildConfig
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
    single { createDataBase(androidContext()) }
    single { get<GitPopDatabase>().repoDao() }
    single { get<GitPopDatabase>().repoPullRequestDao() }
    factory { RepoMapper() }
    factory { RepoPullRequestMapper() }
    factory<RepoLocalDataSource> { RepoLocalDataSourceImpl( dao = get(), mapper = get()) }
    factory<RepoPullRequestLocalDataSource> { RepoPullRequestLocalDataSourceImpl( dao = get(), mapper = get()) }
}

private fun createDataBase(context: Context) : GitPopDatabase {
    return Room
        .databaseBuilder(context, GitPopDatabase::class.java, BuildConfig.GIT_POP_DB_NAME)
        .build()
}