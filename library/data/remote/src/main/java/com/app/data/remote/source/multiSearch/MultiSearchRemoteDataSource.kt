package com.app.data.remote.source.multiSearch

import com.app.domain.model.multiSearch.MultiSearchBO
import com.app.domain.model.utilities.PaginatedResponse


interface MultiSearchRemoteDataSource {

    suspend fun loadMultiSearch(
        query: String,
        language: String,
        includeAdult: Boolean,
        pageIndex: Int,
    ): Result<PaginatedResponse<MultiSearchBO>>
}
