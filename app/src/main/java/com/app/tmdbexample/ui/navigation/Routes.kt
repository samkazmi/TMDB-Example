package com.app.tmdbexample.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute

@Serializable
data class DetailRoute(
    val id: Int,
    val name: String,
    val imagePath: String,
    val description: String,
    val mediaType: String,
)

@Serializable
data object PlayerRoute