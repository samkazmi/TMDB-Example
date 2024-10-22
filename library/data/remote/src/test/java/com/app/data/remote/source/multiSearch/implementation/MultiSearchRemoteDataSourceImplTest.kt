package com.app.data.remote.source.multiSearch.implementation

import com.app.data.remote.api.MultiSearchApi
import com.app.data.remote.source.multiSearch.MultiSearchRemoteDataSource
import com.app.data.remote.utils.failReq
import com.app.data.remote.utils.paginatedFailureResponse
import com.app.data.remote.utils.paginatedSuccessResponse
import com.app.data.remote.utils.searchRemoteFailureResponse401
import com.app.data.remote.utils.successReq
import com.app.data.remote.utils.successResponse
import com.app.tmdb.remote.utils.MainTestDispatcherRule
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MultiSearchRemoteDataSourceImplTest {

    private lateinit var multiSearchApi: MultiSearchApi
    private lateinit var remoteDataSource: MultiSearchRemoteDataSource

    @get:Rule
    val mainDispatcherRule = MainTestDispatcherRule()

    @Before
    fun setUp() {
        multiSearchApi = mockk()
        remoteDataSource = MultiSearchRemoteDataSourceImpl(multiSearchApi)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testGetContent_withValidRequest_isSuccessful() = runTest {
        coEvery {
            multiSearchApi.getContent(successReq)
        }.answers {
            successResponse
        }
        Truth.assertThat(
            remoteDataSource.loadMultiSearch(
                query = successReq.query,
                language = successReq.language ?: "en",
                includeAdult = successReq.includeAdult,
                pageIndex = successReq.pageIndex ?: 1
            )
        ).isEqualTo(paginatedSuccessResponse)
    }

//    @Test
//    fun testGetContent_withInvalidRequest_isFailure() = runTest {
//        coEvery {
//            multiSearchApi.getContent(failReq)
//        }.answers {
//            searchRemoteFailureResponse401
//        }
//        Truth.assertThat(
//            remoteDataSource.loadMultiSearch(
//                query = failReq.query,
//                language = failReq.language ?: "en",
//                includeAdult = failReq.includeAdult,
//                pageIndex = failReq.pageIndex ?: 2
//            )
//        ).isEqualTo(paginatedFailureResponse)
//    }
}
