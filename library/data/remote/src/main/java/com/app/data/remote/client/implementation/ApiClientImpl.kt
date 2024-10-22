package com.app.data.remote.client.implementation

import android.util.Log
import com.app.data.remote.client.ApiClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.MessageLengthLimitingLogger
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.typeInfo
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

internal class ApiClientImpl(
    private val baseUrl: String,
    isDebug: Boolean,
    apiKey: String,
    engine: HttpClientEngine
) : ApiClient {

    @OptIn(ExperimentalSerializationApi::class)
    private val httpClient = HttpClient(engine) {
        developmentMode = isDebug
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    explicitNulls = false
                    prettyPrint = true
                    serializersModule
                }
            )
        }
        if (isDebug) {
            install(Logging) {
                logger = MessageLengthLimitingLogger(delegate = object : Logger {
                    override fun log(message: String) {
                        Log.d("ApiClient", message)
                    }
                })
                level = LogLevel.ALL
            }
        }
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(apiKey, "")
                }
            }
        }
    }

    override suspend fun <T> get(
        path: String,
        body: Any?,
        params: List<Pair<String, Comparable<*>?>>,
        responseType: TypeInfo,
    ) = call<T>(body, params, responseType) {
        httpClient.get("${baseUrl}$path", it)
    }

    private suspend fun <T> call(
        body: Any?,
        params: List<Pair<String, Comparable<*>?>> = listOf(),
        responseType: TypeInfo,
        method: suspend (block: HttpRequestBuilder.() -> Unit) -> HttpResponse,
    ): Result<T> = runCatching {
        method {
            setBody(body)
            contentType(ContentType.Application.Json)
            params.forEach {
                parameter(it.first, it.second)
            }
        }.let { response ->
            return if (response.status.isSuccess()) {
                Result.success(response.body(responseType))
            } else {
                Result.failure(
                    FailedRequestException(
                        response.body(typeInfo<ErrorResponse>())
                    )
                )
            }
        }
    }.getOrElse {
        Result.failure(it)
    }
}
