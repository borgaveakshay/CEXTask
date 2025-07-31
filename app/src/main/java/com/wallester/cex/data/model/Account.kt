package com.wallester.cex.data.model
data class Account(
    val id: String,
    val currencyCode: CurrencyCode,
    val availableBalance: Double,
)