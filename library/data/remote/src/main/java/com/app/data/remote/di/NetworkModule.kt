package com.app.data.remote.di

import com.app.data.remote.api.MultiSearchApi
import com.app.data.remote.api.implementation.MultiSearchApiImpl
import com.app.data.remote.client.implementation.ApiClientImpl
import com.app.data.remote.client.ApiClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

fun networkModule(
    baseUrl: String,
    apiKey: String,
    isDebug: Boolean,
) = module {
    single<HttpClientEngine> { OkHttp.create() }
    single<ApiClient> {
        ApiClientImpl(
            isDebug = isDebug,
            apiKey = apiKey,
            baseUrl = baseUrl,
            engine = get()
        )
    }
    single<MultiSearchApi> { MultiSearchApiImpl(get()) }
}
