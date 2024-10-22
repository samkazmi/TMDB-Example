package com.app.tmdbexample.ui.navigation

import com.app.domain.model.multiSearch.MultiSearchBO

fun MultiSearchBO.toMediaDetailRoute() =
    DetailRoute(id, name, imagePath, description, adult, mediaType.toString())

fun DetailRoute.toMultiSearchBO() =
    MultiSearchBO(id, name, imagePath, description, adult, MultiSearchBO.MediaTypeBO.stringToEnum(mediaType))
