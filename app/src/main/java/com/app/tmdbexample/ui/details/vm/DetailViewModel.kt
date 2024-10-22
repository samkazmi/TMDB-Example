package com.app.tmdbexample.ui.details.vm

import androidx.lifecycle.ViewModel
import com.app.domain.model.multiSearch.MultiSearchBO
import com.app.tmdbexample.utils.extensions.pushEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel : ViewModel() {

    private val _state: MutableStateFlow<MovieDetailsState> = MutableStateFlow(MovieDetailsState())
    val state = _state.asStateFlow()

    fun updateState(item: MultiSearchBO) {
        _state.pushEvent(state.value.copy(multiSearchBO = item))
    }
}

data class MovieDetailsState(
    val multiSearchBO: MultiSearchBO = MultiSearchBO.EMPTY,
)