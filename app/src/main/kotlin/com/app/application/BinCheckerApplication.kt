package com.app.application

import android.app.Application
import com.app.bin_check.di.BinCheckComponent
import com.app.bin_check.di.BinCheckDepsProvider
import com.app.bin_check.di.DaggerBinCheckComponent
import com.app.bin_info_viewer.di.BinInfoViewerComponent
import com.app.bin_info_viewer.di.BinInfoViewerDepsProvider
import com.app.bin_info_viewer.di.DaggerBinInfoViewerComponent
import com.app.di.AppComponent
import com.app.di.DaggerAppComponent

class BinCheckerApplication: Application(), BinCheckDepsProvider, BinInfoViewerDepsProvider {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun getBinCheckComponent(): BinCheckComponent {
        return DaggerBinCheckComponent.builder().deps(appComponent).build()
    }

    override fun getBinInfoComponent(): BinInfoViewerComponent {
        return DaggerBinInfoViewerComponent.builder().deps(appComponent).build()
    }
}