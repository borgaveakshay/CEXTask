package com.wallester.cex.data

import com.wallester.cex.data.model.Account
import com.wallester.cex.data.model.CurrencyExchangeOffer
import com.wallester.cex.domain.CurrencyExchangeDataSource
import com.wallester.cex.domain.ExchangeRateError
import com.wallester.cex.domain.Result
import com.wallester.cex.domain.RootError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class CurrencyExchangeRepository(
    private val currencyExchangeNetworkDataSource: CurrencyExchangeNetworkDataSource,

    ) : CurrencyExchangeDataSource {
    override suspend fun createCurrencyExhange(
        sourceAccount: Account,
        targetAccount: Account,
        amount: Double
    ): Flow<Result<CurrencyExchangeOffer, RootError>> =
        flow<Result<CurrencyExchangeOffer, RootError>> {
            currencyExchangeNetworkDataSource.createCurrencyExchangeOffer(
                sourceAccount = sourceAccount,
                targetAccount = targetAccount,
                amount = amount
            ).also {
                it.offer?.let { offer ->
                    emit(Result.Success(value = offer))
                } ?: run {
                    emit(Result.Failure(error = ExchangeRateError.EXCHANGE_RATE_NOT_AVAILABLE))
                }
            }

        }.onStart {
            emit(Result.Loading)
        }.catchWithCancellation {
            emit(Result.Failure(error = it.getErrorStatus()))
        }
}