package com.app.data.remote.source.multiSearch.implementation.model

internal data class MultiSearchRequest(
    val query: String = "",
    val pageIndex: Int? = null,
    val includeAdult:Boolean = false,
    val language: String? = null
) {

    fun toParams() = buildList<Pair<String, Comparable<*>>> {
        add(KEY_QUERY to query)
        add(KEY_INCLUDE_ADULT to includeAdult)
        pageIndex?.let { add(KEY_PAGE_INDEX to it) }
        language?.let { add(KEY_LANGUAGE to it) }
    }

    companion object {
        private const val KEY_QUERY = "query"
        private const val KEY_PAGE_INDEX = "page"
        private const val KEY_INCLUDE_ADULT = "include_adult"
        private const val KEY_LANGUAGE = "language"
    }
}