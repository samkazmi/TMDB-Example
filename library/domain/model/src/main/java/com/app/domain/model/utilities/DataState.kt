package com.app.domain.model.utilities

sealed class DataState<T> {

    abstract val data: T?

    data class Initial<T>(
        override val data: T? = null
    ) : DataState<T>()

    data class Error<T>(
        override val data: T?,
        val throwable: Throwable
    ) : DataState<T>()

    data class Loading<T>(
        override val data: T?
    ) : DataState<T>()

    data class Value<T>(
        override val data: T
    ) : DataState<T>()
}
