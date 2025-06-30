package com.core.di

import android.content.Context
import androidx.room.Room
import com.core.data.AppDatabase
import com.core.data.BinInfoDao
import com.core.domain.services.BinInfoService
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class BinInfoNetwork {
    @Provides
    @Named("BinBaseUrl")
    fun provideBinBaseUrl(): String = "https://lookup.binlist.net/"

    @Provides
    @Singleton
    fun provideBinInfoService(
        @Named("BinBaseUrl") url: String
    ): BinInfoService {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinInfoService::class.java)
    }
}

@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "bin_checker_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBinInfoDao(database: AppDatabase): BinInfoDao {
        return database.binInfoDao()
    }
}