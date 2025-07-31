package com.wallester.cex.data.model

data class CurrencyExchangeOffer(
    val id: String,
    val fromAccountId: String,
    val toAccountId: String,
    val fromCurrencyCode: CurrencyCode,
    val toCurrencyCode: CurrencyCode,
    val totalAmountReserved: Double,
    val amountToBeConverted: Double,
    val exchangeRate: Double,
    val feeAmount: Double,
    val amountToBeTransferred: Double,
)