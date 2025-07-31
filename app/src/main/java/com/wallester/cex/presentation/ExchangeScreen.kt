package com.wallester.cex.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.wallester.cex.data.model.Account
import com.wallester.cex.data.model.CurrencyCode
import kotlinx.coroutines.delay

@Composable
fun ExchangeScreen(
    viewModel: ExchangeRateViewModel = koinViewModel()
) {

    LaunchedEffect (amount){
        delay(300)
        viewModel.createCurrencyExchangeOffer(
            sourceAccount = Account("1", CurrencyCode.EUR),
            targetAccount = Account("2", CurrencyCode.USD),
            amount = 100.0
        )


    }

}