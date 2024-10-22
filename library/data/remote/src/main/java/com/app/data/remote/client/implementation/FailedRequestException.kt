package com.app.data.remote.client.implementation

import com.app.data.remote.client.implementation.ErrorResponse

class FailedRequestException(
    private val errorResponse: ErrorResponse?
) : RuntimeException() {

    override val message: String? = errorResponse?.message
}
