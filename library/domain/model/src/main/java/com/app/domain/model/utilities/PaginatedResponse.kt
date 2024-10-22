package com.app.domain.model.utilities

data class PaginatedResponse<T>(
    val items: List<T>,
    val totalPageCount: Int
)
