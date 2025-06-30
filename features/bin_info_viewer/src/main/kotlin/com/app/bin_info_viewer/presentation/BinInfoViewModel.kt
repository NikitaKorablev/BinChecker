package com.app.bin_info_viewer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.data.BinInfoDao
import com.core.data.BinInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BinInfoViewModel: ViewModel() {
    @Inject
    lateinit var binInfoDao: BinInfoDao

    private val _binHistory = MutableSharedFlow<List<BinInfoEntity>>()
    val binHistory: SharedFlow<List<BinInfoEntity>> = _binHistory

    fun updateBinHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            val history = binInfoDao.getAll()
            _binHistory.emit(history)
        }
    }
}