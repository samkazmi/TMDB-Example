package com.app.data.local.di

import com.app.data.local.source.locale.LocaleLocalDataSource
import com.app.data.local.source.locale.LocaleLocalDataSourceImpl
import com.app.data.local.source.location.MultiSearchLocalDataSource
import com.app.data.local.source.location.implementation.MultiSearchLocalDataSourceImpl
import org.koin.dsl.module

val localDataSourceModule = module {
    single<MultiSearchLocalDataSource> { MultiSearchLocalDataSourceImpl() }
    single<LocaleLocalDataSource> { LocaleLocalDataSourceImpl(get()) }
}
