package com.wallester.cex.domain

import com.wallester.cex.data.model.Account
import com.wallester.cex.data.model.CurrencyExchangeOffer
import kotlinx.coroutines.flow.Flow

interface CurrencyExchangeDataSource {
    suspend fun createCurrencyExhange(
        sourceAccount: Account,
        targetAccount: Account,
        amount: Double
    ): Flow<Result<CurrencyExchangeOffer, RootError>>
}