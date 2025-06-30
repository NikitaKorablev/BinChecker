package com.app.bin_check.domain.usecases

import com.app.bin_check.data.BinResponseState
import com.app.bin_check.domain.repositories.BinInfoRepositoryInterface

class GetBinInfoUseCase(private val binInfoRepository: BinInfoRepositoryInterface) {

    suspend fun execute(bin: String): BinResponseState {
        if (!binIsValid(bin))
            return BinResponseState.ExceptionState("Bin is invalid.")

        return binInfoRepository.getBinInfo(bin)
    }

    private fun binIsValid(bin: String): Boolean {
        if (bin.isEmpty()) return false
        if (bin.length < MIN_BIN_LENGTH) return false
        if (!bin.matches(Regex("\\d+"))) return false
        return true
    }


    companion object {
        const val MIN_BIN_LENGTH = 6
    }
}