package com.app.data.remote.source.multiSearch.implementation

import com.app.data.remote.api.MultiSearchApi
import com.app.data.remote.mappers.toMultiSearchBO
import com.app.data.remote.source.multiSearch.MultiSearchRemoteDataSource
import com.app.data.remote.source.multiSearch.implementation.model.MultiSearchRequest
import com.app.domain.model.utilities.PaginatedResponse

internal class MultiSearchRemoteDataSourceImpl(private val api: MultiSearchApi) :
    MultiSearchRemoteDataSource {

    override suspend fun loadMultiSearch(
        query: String,
        language: String,
        includeAdult: Boolean,
        pageIndex: Int,
    ) = api.getContent(
        MultiSearchRequest(
            query = query,
            language = language,
            pageIndex = pageIndex
        )
    ).mapCatching { response ->
        PaginatedResponse(
            items = response.results.map { it.toMultiSearchBO() },
            totalPageCount = response.totalPages
        )
    }
}
