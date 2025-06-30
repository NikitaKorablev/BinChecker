package com.core.domain.services

import com.core.data.BinInfoResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface BinInfoService {
    @GET
    suspend fun getInfo(
        @Url url: String
    ): BinInfoResponse
}