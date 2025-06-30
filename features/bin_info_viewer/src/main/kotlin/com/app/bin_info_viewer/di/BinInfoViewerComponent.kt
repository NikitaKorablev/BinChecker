package com.app.bin_info_viewer.di

import com.app.bin_info_viewer.presentation.BinInfoListActivity
import com.app.bin_info_viewer.presentation.BinInfoViewModel
import com.core.data.BinInfoDao
import com.core.utils.Router
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BinInfoViewerModule::class], dependencies = [BinInfoViewerDeps::class])
interface BinInfoViewerComponent {
    fun inject(binInfoListActivity: BinInfoListActivity)
    fun inject(viewModel: BinInfoViewModel)

    @Component.Builder
    interface Builder {
        fun deps(deps: BinInfoViewerDeps): Builder
        fun build(): BinInfoViewerComponent
    }
}

interface BinInfoViewerDeps {
    val binInfoDao: BinInfoDao
}

interface BinInfoViewerDepsProvider {
    fun getBinInfoComponent(): BinInfoViewerComponent
}