package com.app.data.domain.repository

import com.app.data.local.source.location.MultiSearchLocalDataSource
import com.app.data.remote.source.multiSearch.MultiSearchRemoteDataSource
import com.app.domain.model.multiSearch.MultiSearchBO
import com.app.domain.model.utilities.DataState
import com.app.domain.model.utilities.PaginatedRequestType
import com.app.domain.repository.MultiSearchRepository

internal class MultiSearchRepositoryImpl(
    private val remoteDataSource: MultiSearchRemoteDataSource,
    private val localDataSource: MultiSearchLocalDataSource,
) : MultiSearchRepository {

    override val multiSearchState = localDataSource.multiSearchState

    override suspend fun search(
        query: String,
        language: String,
        includeAdult: Boolean,
        paginatedRequestType: PaginatedRequestType,
    ) {
        if (query.isBlank()) {
            localDataSource.updateMultiSearchState(DataState.Initial())
        } else {
            if (localDataSource.multiSearchState.value !is DataState.Loading || paginatedRequestType == PaginatedRequestType.FORCE_REFRESH) {
                localDataSource.updateMultiSearchState(DataState.Loading(data = localDataSource.multiSearchState.value.data))
                val pageIndex = when (paginatedRequestType) {
                    PaginatedRequestType.FORCE_REFRESH -> 1
                    PaginatedRequestType.NEXT_PAGE -> localDataSource.multiSearchCurrentPageIndex + 1
                }.also(localDataSource::updateMultiSearchStateCurrentPageIndex)
                remoteDataSource.loadMultiSearch(
                    query = query.trim(),
                    language = language,
                    includeAdult = includeAdult,
                    pageIndex = pageIndex
                ).onSuccess { paginatedResponse ->
                    val newItems =
                        paginatedResponse.items.filter {
                            it.mediaType != MultiSearchBO.MediaTypeBO.PERSON
                        }.groupBy { item -> item.mediaType }
                    val currentItems = when (paginatedRequestType) {
                        PaginatedRequestType.FORCE_REFRESH -> emptyMap()
                        PaginatedRequestType.NEXT_PAGE -> localDataSource.multiSearchState.value.data
                            ?: emptyMap()
                    }
                    localDataSource.updateMultiSearchState(DataState.Value(currentItems + newItems))
                }.onFailure { exception ->
                    localDataSource.updateMultiSearchState(
                        DataState.Error(
                            data = localDataSource.multiSearchState.value.data,
                            throwable = exception
                        )
                    )
                }
            }
        }
    }
}
