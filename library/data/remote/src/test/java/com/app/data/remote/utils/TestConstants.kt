package com.app.data.remote.utils

import com.app.data.remote.mappers.toMultiSearchBO
import com.app.data.remote.source.multiSearch.implementation.model.MultiSearchRequest
import com.app.data.remote.source.multiSearch.implementation.model.MultiSearchResponse
import com.app.domain.model.multiSearch.MultiSearchBO
import com.app.domain.model.utilities.PaginatedResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.parsing.ParseException

internal val successReq =
    MultiSearchRequest(query = "abc", pageIndex = 1, includeAdult = false, language = "en-US")
internal val failReq =
    MultiSearchRequest(query = "efg", pageIndex = 2, includeAdult = true, language = "ar")
val multiRes = MultiSearchResponse(page = 1, results = listOf(), totalPages = 1, totalResults = 100)
val successResponse = Result.success(multiRes)
fun <T> dataRemoteErrorResponse(httpStatusCode: HttpStatusCode) =
    Result.failure<T>(ParseException(httpStatusCode.description))

val searchRemoteFailureResponse404 =
    dataRemoteErrorResponse<MultiSearchResponse>(HttpStatusCode.NotFound)
val searchRemoteFailureResponse401 =
    dataRemoteErrorResponse<MultiSearchResponse>(HttpStatusCode.Unauthorized)

val paginatedSuccessResponse = Result.success(
    PaginatedResponse(
        multiRes.results.map { it.toMultiSearchBO() },
        multiRes.totalPages
    )
)
val paginatedFailureResponse =
    Result.failure<PaginatedResponse<MultiSearchBO>>(ParseException(HttpStatusCode.Unauthorized.description))