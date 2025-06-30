package com.app.bin_check.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bin_check.data.BankData
import com.app.bin_check.data.BinData
import com.app.bin_check.data.BinResponseState
import com.app.bin_check.data.CountryData
import com.app.bin_check.domain.usecases.GetBinInfoUseCase
import com.core.data.BinInfoDao
import com.core.data.BinInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BinCheckViewModel: ViewModel() {

    @Inject
    lateinit var getBinInfoUseCase: GetBinInfoUseCase

    @Inject
    lateinit var binInfoDao: BinInfoDao

    private val _binData = MutableSharedFlow<BinResponseState>()
    val binData: SharedFlow<BinResponseState> = _binData

    fun getBinInfo(bin: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseState = getBinInfoUseCase.execute(bin)
            _binData.emit(responseState)

            if (responseState is BinResponseState.AcceptState) {
                binInfoDao.insert(responseState.binData.toEntity(bin))
            }
        }
    }

    fun mapResponse(response: BinInfoResponse): BinData {
        val prepaid = response.prepaid?.let { if (it) "Yes" else "No" } ?: ""
        val scheme = response.scheme ?: ""
        val type = response.type ?: ""
        val brand = response.brand ?: ""

        val countryName = response.country?.name ?: ""
        val countryLat = response.country?.latitude?.toString() ?: ""
        val countryLong = response.country?.longitude?.toString() ?: ""

        val bankName = response.bank?.name ?: ""
        val bankCity = response.bank?.city ?: ""
        val bankUrl = response.bank?.url ?: ""
        val bankPhone = response.bank?.phone ?: ""

        val cardLength = response.number?.length?.toString() ?: ""

        val bank = BankData(
            name = "$bankName, $bankCity",
            url = bankUrl,
            phone = bankPhone
        )
        val country = CountryData(
            name = countryName,
            lat = countryLat,
            long = countryLong
        )

        return BinData(
            scheme,
            brand,
            cardLength,
            type,
            prepaid,
            country,
            bank
        )
    }
}