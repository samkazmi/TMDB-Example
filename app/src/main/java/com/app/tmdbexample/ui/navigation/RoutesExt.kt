package com.app.tmdbexample.ui.navigation

import com.app.domain.model.multiSearch.MultiSearchBO

fun MultiSearchBO.toMediaDetailRoute() =
    DetailRoute(id, name, imagePath, description, mediaType.toString())

fun DetailRoute.toMultiSearchBO() =
    MultiSearchBO(id, name, imagePath, description, MultiSearchBO.MediaTypeBO.stringToEnum(mediaType))
