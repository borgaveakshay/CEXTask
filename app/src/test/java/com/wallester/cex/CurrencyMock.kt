package com.wallester.cex

import com.wallester.cex.data.model.Account
import com.wallester.cex.data.model.CurrencyCode
import com.wallester.cex.data.model.CurrencyExchangeOffer

object CurrencyMock {

    val accountSender = Account(
        id = "1",
        currencyCode = CurrencyCode.EUR,
        availableBalance = 100.0
    )

    val accountReceiver = Account(
        id = "2",
        currencyCode = CurrencyCode.USD,
        availableBalance = 100.0
    )

    fun getCurrencyExchangeOffer() : CurrencyExchangeOffer {
        return CurrencyExchangeOffer(
            id = "1",
            fromAccountId = accountSender.id,
            toAccountId = accountReceiver.id,
            fromCurrencyCode = accountSender.currencyCode,
            toCurrencyCode = accountReceiver.currencyCode,
            totalAmountReserved = 100.0,
            amountToBeConverted = 100.0,
            exchangeRate = 1.14,
            feeAmount = 10.0,
            amountToBeTransferred = 90.0
        )

    }
}