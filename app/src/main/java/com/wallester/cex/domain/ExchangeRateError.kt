package com.wallester.cex.domain

enum class ExchangeRateError: Error {
    EXCHANGE_RATE_NOT_AVAILABLE, NETWORK_ERROR, INVALID_AMOUNT, INSUFFICIENT_FUNDS, UNKNOWN_ERROR
}