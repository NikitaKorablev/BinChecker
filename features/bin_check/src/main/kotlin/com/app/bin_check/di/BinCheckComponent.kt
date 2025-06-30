package com.app.bin_check.di

import com.app.bin_check.presentation.BinCheckActivity
import com.app.bin_check.presentation.BinCheckViewModel
import com.core.data.BinInfoDao
import com.core.utils.Router
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BinCheckModule::class], dependencies = [BinCheckDeps::class])
interface BinCheckComponent {
    fun inject(binCheckActivity: BinCheckActivity)
    fun inject(viewModel: BinCheckViewModel)

    @Component.Builder
    interface Builder {
        fun deps(deps: BinCheckDeps): Builder
        fun build(): BinCheckComponent
    }
}

interface BinCheckDeps {
    val router: Router
    val binInfoDao: BinInfoDao
}

interface BinCheckDepsProvider {
    fun getBinCheckComponent(): BinCheckComponent
}