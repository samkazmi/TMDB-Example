package com.app.tmdbexample.di

import com.app.tmdbexample.utils.DefaultCoroutineDispatcherProvider
import com.app.tmdbexample.utils.DefaultDispatcherProvider
import org.koin.dsl.module

val systemModule = module {
    single<DefaultDispatcherProvider> { DefaultCoroutineDispatcherProvider() }
}
