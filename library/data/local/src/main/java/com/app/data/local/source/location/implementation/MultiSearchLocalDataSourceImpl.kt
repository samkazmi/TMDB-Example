package com.app.data.local.source.location.implementation

import com.app.domain.model.utilities.DataState
import com.app.domain.model.multiSearch.MultiSearchBO
import com.app.data.local.source.location.MultiSearchLocalDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class MultiSearchLocalDataSourceImpl : MultiSearchLocalDataSource {

    private val _multiSearchState =
        MutableStateFlow<DataState<Map<MultiSearchBO.MediaTypeBO, List<MultiSearchBO>>>>(DataState.Initial())
    override val multiSearchState = _multiSearchState.asStateFlow()
    override var multiSearchCurrentPageIndex = 1
        private set

    override fun updateMultiSearchState(searchState: DataState<Map<MultiSearchBO.MediaTypeBO, List<MultiSearchBO>>>) {
        _multiSearchState.value = searchState
    }

    override fun updateMultiSearchStateCurrentPageIndex(currentPageIndex: Int) {
        multiSearchCurrentPageIndex = currentPageIndex
    }


}
