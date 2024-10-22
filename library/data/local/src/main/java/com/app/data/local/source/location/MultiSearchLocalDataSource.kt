package com.app.data.local.source.location

import com.app.domain.model.utilities.DataState
import com.app.domain.model.multiSearch.MultiSearchBO
import kotlinx.coroutines.flow.StateFlow

interface MultiSearchLocalDataSource {

    val multiSearchState: StateFlow<DataState<Map<MultiSearchBO.MediaTypeBO, List<MultiSearchBO>>>>
    val multiSearchCurrentPageIndex: Int

    fun updateMultiSearchState(searchState: DataState<Map<MultiSearchBO.MediaTypeBO, List<MultiSearchBO>>>)
    fun updateMultiSearchStateCurrentPageIndex(currentPageIndex: Int)
}
