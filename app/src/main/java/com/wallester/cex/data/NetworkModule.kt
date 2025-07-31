package com.wallester.cex.data

import com.wallester.cex.domain.CurrencyExchangeDataSource
import org.koin.dsl.module

val networModule = module {

    single<CurrencyExchangeNetworkDataSource> {
        FakeCurrencyExchangeNetworkDataSource()
    }

    single<CurrencyExchangeDataSource> {
        CurrencyExchangeRepository(get())
    }


}