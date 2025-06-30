package com.core.data

data class BinInfoResponse(
    val number: Number? = null,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: Boolean? = null,
    val country: Country? = null,
    val bank: Bank? = null
) {
    fun toEntity(bin: String): BinInfoEntity {
        return BinInfoEntity(
            bin = bin,
            numberLength = number?.length?.toString() ?: "0",
            numberLuhn = number?.luhn?.toString() ?: "false",
            scheme = scheme ?: "Unknown",
            type = type ?: "Unknown",
            brand = brand ?: "Unknown",
            prepaid = prepaid?.let { if (it) "Yes" else "No" } ?: "Unknown",
            countryNumeric = country?.numeric ?: "Unknown",
            countryAlpha2 = country?.alpha2 ?: "Unknown",
            countryName = country?.name ?: "Unknown",
            countryEmoji = country?.emoji ?: "Unknown",
            countryCurrency = country?.currency ?: "Unknown",
            countryLatitude = country?.latitude?.toString() ?: "0",
            countryLongitude = country?.longitude?.toString() ?: "0",
            bankName = bank?.name ?: "Unknown",
            bankUrl = bank?.url ?: "Unknown",
            bankPhone = bank?.phone ?: "Unknown",
            bankCity = bank?.city ?: "Unknown"
        )
    }
}

data class Number(
    val length: Int? = null,
    val luhn: Boolean? = null
)

data class Country(
    val numeric: String? = null,
    val alpha2: String? = null,
    val name: String? = null,
    val emoji: String? = null,
    val currency: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null
)

data class Bank(
    val name: String? = null,
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null
)