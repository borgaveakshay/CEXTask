package com.wallester.cex.data

import com.wallester.cex.domain.ExchangeRateError
import java.io.IOException


fun Throwable.getErrorStatus(): ExchangeRateError {
    return when (this) {
        is IOException -> ExchangeRateError.NETWORK_ERROR
        else -> ExchangeRateError.UNKNOWN_ERROR

    }
}