package com.app.data.remote.di

import com.app.data.remote.source.multiSearch.MultiSearchRemoteDataSource
import com.app.data.remote.source.multiSearch.implementation.MultiSearchRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {
    factory<MultiSearchRemoteDataSource> { MultiSearchRemoteDataSourceImpl(get()) }
}
