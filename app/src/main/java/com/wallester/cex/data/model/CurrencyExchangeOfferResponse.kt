package com.wallester.cex.data.model

data class CurrencyExchangeOfferResponse(
    val success: Boolean,
    val offer: CurrencyExchangeOffer?,
    val message: String?,
)
