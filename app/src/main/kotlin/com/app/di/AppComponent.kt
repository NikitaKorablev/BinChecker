package com.app.di

import android.app.Application
import com.app.bin_check.di.BinCheckDeps
import com.app.bin_info_viewer.di.BinInfoViewerDeps
import com.app.presentation.MainActivity
import com.core.data.BinInfoDao
import com.core.di.DatabaseModule
import com.core.utils.Router
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class])
interface AppComponent: BinCheckDeps, BinInfoViewerDeps {
    fun inject(mainActivity: MainActivity)

    override val router: Router
    override val binInfoDao: BinInfoDao

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}