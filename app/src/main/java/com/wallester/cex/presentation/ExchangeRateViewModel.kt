package com.wallester.cex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wallester.cex.data.model.Account
import com.wallester.cex.domain.CreateCurrencyExchanceOfferUseCase
import com.wallester.cex.domain.ExchangeRateError
import com.wallester.cex.domain.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExchangeRateViewModel(
    private val createCurrencyExchanceOfferUseCase: CreateCurrencyExchanceOfferUseCase
) : ViewModel() {

    private val _exchangeRateState = MutableStateFlow(ExchangeRateState())
    val exchangeRateState = _exchangeRateState.asStateFlow()


    fun createCurrencyExchangeOffer(
        sourceAccount: Account,
        targetAccount: Account,
        amount: Double
    ) = viewModelScope.launch {
        createCurrencyExchanceOfferUseCase(
            sourceAccount = sourceAccount,
            targetAccount = targetAccount,
            amount = amount
        ).collect { result ->
            when (result) {
                is Result.Failure -> {
                    _exchangeRateState.update {
                        it.copy(
                            error = result.error as ExchangeRateError,
                            isLoading = false
                        )
                    }

                }
                Result.Loading -> {
                    _exchangeRateState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _exchangeRateState.update {
                        it.copy(
                            uiState = ExchangeRateUiState(
                                sourceAccount = sourceAccount,
                                receiverAccount = targetAccount,
                                offer = result.value
                            ),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}