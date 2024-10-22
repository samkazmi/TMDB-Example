package com.app.data.remote.mappers

import com.app.data.remote.source.multiSearch.implementation.model.MultiSearch
import com.app.domain.model.multiSearch.MultiSearchBO
import okhttp3.MediaType.Companion.toMediaType

internal fun MultiSearch.toMultiSearchBO() = MultiSearchBO(
    id = id,
    name = title ?: name.orEmpty(),
    imagePath = backdropPath.orEmpty(),
    description = overview.orEmpty(),
    adult = adult ?: false,
    mediaType = MultiSearchBO.MediaTypeBO.stringToEnum(mediaType)
)
