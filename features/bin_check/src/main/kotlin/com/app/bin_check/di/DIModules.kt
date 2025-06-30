package com.app.bin_check.di

import com.app.bin_check.data.BinInfoRepositoryImpl
import com.app.bin_check.domain.repositories.BinInfoRepositoryInterface
import com.app.bin_check.domain.usecases.GetBinInfoUseCase
import com.core.di.BinInfoNetwork
import com.core.domain.services.BinInfoService
import dagger.Module
import dagger.Provides

@Module(includes = [BinInfoNetwork::class])
class BinCheckModule {
    @Provides
    fun provideBinInfoRepositoryImpl(
        binInfoService: BinInfoService
    ): BinInfoRepositoryInterface {
        return BinInfoRepositoryImpl(binInfoService)
    }

    @Provides
    fun provideGetBinInfoUseCase(
        binInfoRepository: BinInfoRepositoryInterface
    ) = GetBinInfoUseCase(binInfoRepository)
}