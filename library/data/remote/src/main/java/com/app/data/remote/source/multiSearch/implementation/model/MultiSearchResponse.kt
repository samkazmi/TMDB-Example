package com.app.data.remote.source.multiSearch.implementation.model

import com.app.data.remote.utilities.Pagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias MultiSearchResponse = Pagination<MultiSearch>

@Serializable
data class MultiSearch(
    val id: Int,
    val overview: String? = null,
    val popularity: Double? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val adult: Boolean? = null,
    val gender: Int? = null,
    val name: String? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("genre_ids") val genreIds: List<Int>? = null,
    @SerialName("known_for") val knownFor: List<MultiSearch>? = null,
    @SerialName("known_for_department") val knownForDepartment: String? = null,
    @SerialName("media_type") val mediaType: String,
    @SerialName("original_language") val originalLanguage: String? = null,
    @SerialName("original_name") val originalName: String? = null,
    @SerialName("original_title") val originalTitle: String? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("profile_path") val profilePath: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("vote_average") val voteAverage: Float? = null,
    @SerialName("vote_count") val voteCount: Int? = null,
)

