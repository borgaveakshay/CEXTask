package com.wallester.cex.domain

import com.wallester.cex.CurrencyMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CreateCurrencyExchanceOfferUseCaseTest {

    private lateinit var createCurrencyExchanceOfferUseCase: CreateCurrencyExchanceOfferUseCase
    private val currencyExchangeDataSource = mockk<CurrencyExchangeDataSource>()

    @Before
    fun setUp() {
        createCurrencyExchanceOfferUseCase =
            CreateCurrencyExchanceOfferUseCase(currencyExchangeDataSource)
    }

    @Test
    fun `when create exchange offer is called, it should call the data source`() = runTest {
        //GIVEN
        val givenOffer = CurrencyMock.getCurrencyExchangeOffer()
        coEvery {
            currencyExchangeDataSource.createCurrencyExhange(
                any(),
                any(),
                any()
            )
        } returns flowOf(Result.Success(givenOffer))
        //WHEN
        val result = createCurrencyExchanceOfferUseCase(
            CurrencyMock.accountSender,
            CurrencyMock.accountReceiver,
            100.0
        )
        //THEN
        result.collect { result ->
            when {
                result is Result.Success -> {
                    assert(result.value == givenOffer)
                }
            }
        }
    }

    @Test
    fun `when create exchange offer is called, it should return error`() = runTest {
        //GIVEN
        val expectedError = ExchangeRateError.NETWORK_ERROR
        coEvery {
            currencyExchangeDataSource.createCurrencyExhange(
                any(),
                any(),
                any()
            )
        } returns flowOf(Result.Failure(expectedError))
        //WHEN
        val result = createCurrencyExchanceOfferUseCase(
            CurrencyMock.accountSender,
            CurrencyMock.accountReceiver,
            100.0
        )
        //THEN
        result.collect { result ->
            when {
                result is Result.Failure -> {
                    assert(result.error == expectedError)
                }
            }
        }

    }
}