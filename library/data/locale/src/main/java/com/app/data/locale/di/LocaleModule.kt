package com.app.data.locale.di

import android.app.Application
import com.app.data.locale.ApplicationLocaleManager
import org.koin.dsl.module

fun localeModule(application: Application) = module {
    single { ApplicationLocaleManager(application) }
}
