package com.app.data.remote.client.implementation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("status_message") val message: String? = null,
    @SerialName("status_code") val statusCode: Int? = null,
)
