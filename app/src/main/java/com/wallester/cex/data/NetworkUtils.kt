package com.wallester.cex.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlin.coroutines.cancellation.CancellationException

inline fun <T> Flow<T>.catchWithCancellation(
    crossinline catchBlock: suspend FlowCollector<T>.(Throwable) -> Unit
): Flow<T> {
    return catch {
        catchBlock(it)
        if (it is CancellationException) throw it
    }
}