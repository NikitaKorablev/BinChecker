package com.app.bin_check.data

data class BinData(
    var scheme: String,
    var brand: String,
    var cardLength: String,
    var type: String,
    var prepaid: String,
    var country: CountryData,
    var bank: BankData
)

data class CountryData(
    val name: String,
    val lat: String,
    val long: String
)

data class BankData(
    val name: String,
    val url: String,
    val phone: String
)