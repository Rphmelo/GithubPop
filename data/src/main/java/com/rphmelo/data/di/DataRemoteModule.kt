package com.rphmelo.data.di

import com.rphmelo.data.remote.api.GitHubApi
import com.rphmelo.data.remote.mapper.RepoMapper
import com.rphmelo.data.remote.mapper.RepoPullRequestMapper
import com.rphmelo.data.remote.source.RepoPullRequestRemoteDataSource
import com.rphmelo.data.remote.source.RepoPullRequestRemoteDataSourceImpl
import com.rphmelo.data.remote.source.RepoRemoteDataSource
import com.rphmelo.data.remote.source.RepoRemoteDataSourceImpl
import okhttp3.OkHttpClient
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {
    factory { providesOkHttpClient() }
    single { createWebService<GitHubApi>(
        okHttpClient = get(),
        url =  BuildConfig.FLAVOR
    ) }
    factory { RepoMapper() }
    factory { RepoPullRequestMapper() }
    factory<RepoRemoteDataSource> { RepoRemoteDataSourceImpl(api = get(), mapper = get()) }
    factory<RepoPullRequestRemoteDataSource> { RepoPullRequestRemoteDataSourceImpl(api = get(), mapper = get()) }
}

fun providesOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient , url: String): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(url)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}