package com.app.tmdbexample.di

import android.app.Application
import com.app.data.domain.di.domainModule
import com.app.data.local.di.localDataSourceModule
import com.app.data.locale.di.localeModule
import com.app.data.remote.di.networkModule
import com.app.data.remote.di.remoteDataSourceModule
import com.app.tmdbexample.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object ServiceLocator {

    fun initialize(application: Application) = startKoin {
        androidContext(application.applicationContext)
        modules(
            localDataSourceModule,
            networkModule(
                baseUrl = BuildConfig.BASE_URL,
                apiKey = BuildConfig.API_KEY,
                isDebug = BuildConfig.DEBUG
            ),
            remoteDataSourceModule,
            domainModule,
            presentationModule,
            systemModule,
            localeModule(application)
        )
    }
}
