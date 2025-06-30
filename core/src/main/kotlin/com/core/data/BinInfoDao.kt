package com.core.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BinInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(binInfo: BinInfoEntity)

    @Query("SELECT * FROM bin_info")
    fun getAll(): List<BinInfoEntity>

    @Query("SELECT * FROM bin_info WHERE bin = :bin")
    suspend fun getByBin(bin: String): BinInfoEntity?
}