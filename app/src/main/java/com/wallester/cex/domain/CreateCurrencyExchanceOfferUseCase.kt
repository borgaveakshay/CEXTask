package com.wallester.cex.domain

import com.wallester.cex.data.CurrencyExchangeNetworkDataSource
import com.wallester.cex.data.model.Account

class CreateCurrencyExchanceOfferUseCase(
    private val curerncyExchangeDataSource: CurrencyExchangeDataSource
) {

    suspend operator fun invoke(sourceAccount: Account, targetAccount: Account, amount: Double) =
        curerncyExchangeDataSource.createCurrencyExhange(
            sourceAccount = sourceAccount,
            targetAccount = targetAccount,
            amount = amount
        )
}