package com.core.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BinInfoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun binInfoDao(): BinInfoDao
}