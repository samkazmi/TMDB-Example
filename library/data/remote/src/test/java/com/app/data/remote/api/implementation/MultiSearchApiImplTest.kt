package com.app.data.remote.api.implementation

import com.app.data.remote.api.MultiSearchApi
import com.app.data.remote.client.ApiClient
import com.app.data.remote.source.multiSearch.implementation.model.MultiSearchResponse
import com.app.data.remote.utils.failReq
import com.app.data.remote.utils.searchRemoteFailureResponse401
import com.app.data.remote.utils.successReq
import com.app.data.remote.utils.successResponse
import com.app.tmdb.remote.utils.MainTestDispatcherRule
import com.google.common.truth.Truth
import io.ktor.util.reflect.typeInfo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MultiSearchApiImplTest {

    private lateinit var client: ApiClient
    private lateinit var multiSearchApi: MultiSearchApi

    @get:Rule
    val mainDispatcherRule = MainTestDispatcherRule()

    @Before
    fun setUp() {
        client = mockk<ApiClient>(relaxed = true)
        multiSearchApi = MultiSearchApiImpl(client)
    }

    @Test
    fun testContentApiCall_withValidRequestAndResponse_isSuccessful() = runTest {
        coEvery {
            client.get<MultiSearchResponse>(
                path = MultiSearchApiImpl.MULTI_SEARCH_PATH,
                params = successReq.toParams(),
                responseType = typeInfo<MultiSearchResponse>()
            )
        }.returns(successResponse)
        Truth.assertThat(multiSearchApi.getContent(successReq)).isEqualTo(successResponse)
    }

    @Test
    fun testContentApiCall_withInvalidRequestAndResponse_isFailure() = runTest {
        coEvery {
            client.get<MultiSearchResponse>(
                path = MultiSearchApiImpl.MULTI_SEARCH_PATH,
                params = failReq.toParams(),
                responseType = typeInfo<MultiSearchResponse>()
            )
        }.returns(searchRemoteFailureResponse401)
        Truth.assertThat(multiSearchApi.getContent(failReq))
            .isEqualTo(searchRemoteFailureResponse401)
    }
}
