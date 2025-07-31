package com.wallester.cex.presentation

import androidx.compose.runtime.Immutable
import com.wallester.cex.data.model.Account
import com.wallester.cex.data.model.CurrencyExchangeOffer
import com.wallester.cex.domain.ExchangeRateError


data class ExchangeRateUiState(
    val sourceAccount: Account,
    val receiverAccount: Account,
    val offer: CurrencyExchangeOffer? = null,

    )

@Immutable
data class ExchangeRateState(
    val isLoading: Boolean = false,
    val uiState: ExchangeRateUiState? = null,
    val error: ExchangeRateError? = null
)