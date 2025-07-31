package com.wallester.cex.domain

typealias RootError = Error

sealed class Result<out T, out E : RootError> {
    data class Success<out T>(val value: T) : Result<T, Nothing>()
    data class Failure<out E : RootError>(val error: E) : Result<Nothing, E>()
    data object Loading : Result<Nothing, Nothing>()
}