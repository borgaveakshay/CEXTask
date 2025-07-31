package com.wallester.cex.data

import com.wallester.cex.data.model.Account
import com.wallester.cex.data.model.CurrencyExchangeConfirmResponse
import com.wallester.cex.data.model.CurrencyExchangeOffer
import com.wallester.cex.data.model.CurrencyExchangeOfferResponse
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class FakeCurrencyExchangeNetworkDataSource : CurrencyExchangeNetworkDataSource {

    override suspend fun createCurrencyExchangeOffer(
        sourceAccount: Account,
        targetAccount: Account,
        amount: Double
    ): CurrencyExchangeOfferResponse {
        delay(1.seconds)

        val feeAmount = amount * 0.01 // 1% fee
        val amountToBeConverted = amount - feeAmount

        // mock exchange rate: ie: EUR/USD = 1.14
        val exchangeRate = Random.nextDouble(1.0, 2.0)

        val amountToBeTransferred = amountToBeConverted * exchangeRate

        val offer = CurrencyExchangeOffer(
            id = System.currentTimeMillis().toString(),
            fromAccountId = sourceAccount.id,
            toAccountId = targetAccount.id,
            fromCurrencyCode = sourceAccount.currencyCode,
            toCurrencyCode = targetAccount.currencyCode,
            totalAmountReserved = amount,
            amountToBeConverted = amountToBeConverted,
            exchangeRate = exchangeRate,
            feeAmount = feeAmount,
            amountToBeTransferred = amountToBeTransferred,
        )

        return CurrencyExchangeOfferResponse(
            offer = offer,
            success = true,
            message = null
        )
    }

    override suspend fun confirm(
        offerId: String
    ): CurrencyExchangeConfirmResponse {
        delay(1.seconds)
        return CurrencyExchangeConfirmResponse(
            offerId = offerId,
            success = true,
            message = null,
        )
    }
}


