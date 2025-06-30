package com.app.bin_check.data

import com.core.data.BinInfoResponse

sealed class BinResponseState {
    data class AcceptState(val binData: BinInfoResponse): BinResponseState()
    data class ExceptionState(val message: String): BinResponseState()
}