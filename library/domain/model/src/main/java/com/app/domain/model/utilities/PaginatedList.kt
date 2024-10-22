package com.app.domain.model.utilities

data class PaginatedList<T>(
    val items: List<T>,
    val hasNextPage: Boolean
)
