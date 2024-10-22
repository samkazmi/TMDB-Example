package com.app.data.remote.client

import io.ktor.util.reflect.TypeInfo

internal interface ApiClient {

    suspend fun <Response> get(
        path: String,
        body: Any? = null,
        params: List<Pair<String, Comparable<*>?>> = emptyList(),
        responseType: TypeInfo
    ): Result<Response>
}
