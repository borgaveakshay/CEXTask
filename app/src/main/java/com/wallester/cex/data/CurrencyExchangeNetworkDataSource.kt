package com.wallester.cex.data

import com.wallester.cex.data.model.Account
import com.wallester.cex.data.model.CurrencyExchangeConfirmResponse
import com.wallester.cex.data.model.CurrencyExchangeOfferResponse

interface CurrencyExchangeNetworkDataSource {

    suspend fun createCurrencyExchangeOffer(
        sourceAccount: Account,
        targetAccount: Account,
        amount: Double
    ): CurrencyExchangeOfferResponse

    suspend fun confirm(offerId: String): CurrencyExchangeConfirmResponse
}