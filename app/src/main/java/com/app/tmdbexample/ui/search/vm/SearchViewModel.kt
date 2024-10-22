package com.app.tmdbexample.ui.search.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.model.multiSearch.MultiSearchBO
import com.app.domain.model.utilities.DataState
import com.app.domain.model.utilities.PaginatedRequestType
import com.app.domain.repository.LocaleRepository
import com.app.domain.repository.MultiSearchRepository
import com.app.tmdbexample.utils.DefaultDispatcherProvider
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Locale

@OptIn(FlowPreview::class)
class SearchViewModel(
    private val dispatcher: DefaultDispatcherProvider,
    private val multiSearchRepository: MultiSearchRepository,
    private val localeRepository: LocaleRepository,
) : ViewModel() {
    private val searchQuery = MutableStateFlow("")
    val state by lazy {
        combine(
            searchQuery,
            multiSearchRepository.multiSearchState,
            localeRepository.currentLocale,
            ::generateState
        ).distinctUntilChanged().flowOn(dispatcher.io)
    }


    init {
        searchQuery.debounce(300)
            .combine(localeRepository.currentLocale, transform = { query, locale ->
                query to locale
            }).onEach { pair ->
                searchItems(
                    pair.first,
                    pair.second.language,
                    PaginatedRequestType.FORCE_REFRESH
                )
            }.flowOn(dispatcher.io).launchIn(viewModelScope)
    }

    fun onSearchQueryChanged(newSearchQuery: String) {
        searchQuery.value = newSearchQuery
    }

    private fun generateState(
        searchQuery: String,
        searchResult: DataState<Map<MultiSearchBO.MediaTypeBO, List<MultiSearchBO>>>,
        locale: Locale, // it is here to update content to selected language
    ) = when (searchResult) {
        is DataState.Error -> State.Error(searchQuery, searchResult.throwable)
        is DataState.Initial -> State.Initial(searchQuery)
        is DataState.Loading -> State.Loading(searchQuery)
        is DataState.Value -> if (searchResult.data.isEmpty()) State.Empty(searchQuery) else State.Data(
            searchQuery, searchResult.data
        )
    }


    private suspend fun searchItems(
        query: String,
        language: String,
        paginatedRequestType: PaginatedRequestType,
    ) =
        multiSearchRepository.search(
            query = query,
            language = language,
            includeAdult = true,
            paginatedRequestType = paginatedRequestType
        )

    sealed class State {
        abstract val query: String

        data class Initial(override val query: String = "") : State()
        data class Loading(override val query: String) : State()
        data class Empty(override val query: String) : State()
        data class Error(override val query: String, val throwable: Throwable) : State()
        data class Data(
            override val query: String,
            val listItems: Map<MultiSearchBO.MediaTypeBO, List<MultiSearchBO>>,
        ) : State()
    }
}