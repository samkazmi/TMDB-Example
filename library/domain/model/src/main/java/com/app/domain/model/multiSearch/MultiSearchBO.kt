package com.app.domain.model.multiSearch

data class MultiSearchBO(
    val id: Int,
    val name: String,
    val imagePath: String,
    val description: String,
    val adult: Boolean,
    val mediaType: MediaTypeBO,
) {
    enum class MediaTypeBO(val value: String) {
        MOVIE("movie"),
        TV("tv"),
        XXX("xxx"),
        PERSON("person"),
        OTHER("other");

        companion object {
            fun stringToEnum(value: String): MediaTypeBO {
                return entries.find {
                    it.value == value
                } ?: OTHER
            }
        }
    }

    companion object {
        val EMPTY = MultiSearchBO(-1, "", "", "", false, MediaTypeBO.OTHER)
    }
}