package com.app.data.domain.di

import com.app.data.domain.repository.LocaleRepositoryImpl
import com.app.data.domain.repository.MultiSearchRepositoryImpl
import com.app.domain.repository.LocaleRepository
import com.app.domain.repository.MultiSearchRepository
import org.koin.dsl.module

val domainModule = module {
    single<LocaleRepository> { LocaleRepositoryImpl(get()) }
    factory<MultiSearchRepository> { MultiSearchRepositoryImpl(get(), get()) }
}
