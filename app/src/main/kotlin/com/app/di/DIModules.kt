package com.app.di

import android.app.Application
import android.content.Context
import com.app.utils.AppNavigationComponent
import com.core.utils.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [UtilsModule::class])
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
}

@Module
class UtilsModule {
    @Provides
    @Singleton
    fun provideRouter(): Router {
        return AppNavigationComponent()
    }
}