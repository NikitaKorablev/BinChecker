package com.app.utils

import android.content.Context
import android.content.Intent
import com.app.bin_check.presentation.BinCheckActivity
import com.app.bin_check.utils.BinCheckRouter
import com.app.bin_info_viewer.presentation.BinInfoListActivity

class AppNavigationComponent: BinCheckRouter {
    fun moveToBinChecker(context: Context) {
        val intent = Intent(context, BinCheckActivity::class.java)
        context.startActivity(intent)
    }

    override fun moveToBinHistory(context: Context) {
        val intent = Intent(context, BinInfoListActivity::class.java)
        context.startActivity(intent)
    }
}