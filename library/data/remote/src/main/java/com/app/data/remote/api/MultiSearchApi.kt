package com.app.data.remote.api

import com.app.data.remote.source.multiSearch.implementation.model.MultiSearchRequest
import com.app.data.remote.source.multiSearch.implementation.model.MultiSearchResponse

internal interface MultiSearchApi {

    suspend fun getContent(
        request: MultiSearchRequest,
    ): Result<MultiSearchResponse>
}
