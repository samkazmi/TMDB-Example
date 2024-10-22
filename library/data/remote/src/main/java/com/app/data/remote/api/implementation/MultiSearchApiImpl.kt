package com.app.data.remote.api.implementation

import com.app.data.remote.api.MultiSearchApi
import com.app.data.remote.client.ApiClient
import com.app.data.remote.source.multiSearch.implementation.model.MultiSearchRequest
import com.app.data.remote.source.multiSearch.implementation.model.MultiSearchResponse
import io.ktor.util.reflect.typeInfo

internal class MultiSearchApiImpl(
    private val client: ApiClient
) : MultiSearchApi {

    companion object{
        const val MULTI_SEARCH_PATH = "search/multi"
    }
    override suspend fun getContent(request: MultiSearchRequest) = client.get<MultiSearchResponse>(
        path = MULTI_SEARCH_PATH,
        params = request.toParams(),
        responseType = typeInfo<MultiSearchResponse>()
    )
}
