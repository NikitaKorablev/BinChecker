package com.app.bin_check.data

import android.util.Log
import com.app.bin_check.domain.repositories.BinInfoRepositoryInterface
import com.core.domain.services.BinInfoService

class BinInfoRepositoryImpl(private val service: BinInfoService)
    : BinInfoRepositoryInterface {

    private val baseUrl = "https://lookup.binlist.net/"
    override suspend fun getBinInfo(bin: String): BinResponseState {
        return try {
            val response = service.getInfo(baseUrl + bin)
            BinResponseState.AcceptState(response)
        } catch (err: Exception) {
            val message = "Getting bin info was excepted:\n\t${err.message}"
            Log.e(TAG, message)
            BinResponseState.ExceptionState(message)
        }
    }

    companion object {
        const val TAG = "BIN_INFO_REPO_IMPL"
    }
}