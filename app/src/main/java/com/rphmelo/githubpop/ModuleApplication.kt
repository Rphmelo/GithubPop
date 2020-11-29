package com.rphmelo.githubpop

import android.app.Application
import com.rphmelo.data.di.dataModules
import com.rphmelo.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ModuleApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ModuleApplication)

            modules(domainModule + dataModules)
        }
    }
}