package com.app.domain.repository

import com.app.domain.model.multiSearch.MultiSearchBO
import com.app.domain.model.utilities.DataState
import com.app.domain.model.utilities.PaginatedRequestType
import kotlinx.coroutines.flow.StateFlow

interface MultiSearchRepository {

    val multiSearchState: StateFlow<DataState<Map<MultiSearchBO.MediaTypeBO, List<MultiSearchBO>>>>

    suspend fun search(
        query: String,
        language: String,
        includeAdult: Boolean,
        paginatedRequestType: PaginatedRequestType,
    )
}
