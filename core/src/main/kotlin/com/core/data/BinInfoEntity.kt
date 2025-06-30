package com.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin_info")
data class BinInfoEntity(
    @PrimaryKey val bin: String,
    val numberLength: String = "0",
    val numberLuhn: String = "false",
    val scheme: String = "Unknown",
    val type: String = "Unknown",
    val brand: String = "Unknown",
    val prepaid: String = "Unknown",
    val countryNumeric: String = "Unknown",
    val countryAlpha2: String = "Unknown",
    val countryName: String = "Unknown",
    val countryEmoji: String = "Unknown",
    val countryCurrency: String = "Unknown",
    val countryLatitude: String = "0",
    val countryLongitude: String = "0",
    val bankName: String = "Unknown",
    val bankUrl: String = "Unknown",
    val bankPhone: String = "Unknown",
    val bankCity: String = "Unknown"
)