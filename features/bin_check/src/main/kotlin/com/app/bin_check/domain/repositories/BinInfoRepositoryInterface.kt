package com.app.bin_check.domain.repositories

import com.app.bin_check.data.BinResponseState

interface BinInfoRepositoryInterface {
    suspend fun getBinInfo(bin: String): BinResponseState
}