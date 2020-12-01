package com.rphmelo.data.di

import android.util.Log
import com.rphmelo.data.BuildConfig
import com.rphmelo.data.remote.api.GitHubApi
import com.rphmelo.data.remote.mapper.RepoMapper
import com.rphmelo.data.remote.mapper.RepoPullRequestMapper
import com.rphmelo.data.remote.source.RepoPullRequestRemoteDataSource
import com.rphmelo.data.remote.source.RepoPullRequestRemoteDataSourceImpl
import com.rphmelo.data.remote.source.RepoRemoteDataSource
import com.rphmelo.data.remote.source.RepoRemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {
    factory { okHttpClient() }
    single { createWebService<GitHubApi>(
        okHttpClient = get(),
        url =  BuildConfig.API_URL
    ) }
    factory { RepoMapper() }
    factory { RepoPullRequestMapper() }
    factory<RepoRemoteDataSource> { RepoRemoteDataSourceImpl(api = get(), mapper = get()) }
    factory<RepoPullRequestRemoteDataSource> { RepoPullRequestRemoteDataSourceImpl(api = get(), mapper = get()) }
}

fun okHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
        if(BuildConfig.DEBUG) {
            addInterceptor(loggingInterceptor())
        }
    }.build()
}


fun loggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor { Log.d("API REQUEST", it) }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(url)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}