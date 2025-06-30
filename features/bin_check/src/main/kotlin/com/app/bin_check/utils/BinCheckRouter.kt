package com.app.bin_check.utils

import android.content.Context
import com.core.utils.Router

interface BinCheckRouter: Router {
    fun moveToBinHistory(context: Context)
}