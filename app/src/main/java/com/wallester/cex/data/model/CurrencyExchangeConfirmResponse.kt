package com.wallester.cex.data.model

data class CurrencyExchangeConfirmResponse(
    val offerId: String,
    val success: Boolean,
    val message: String?,
)