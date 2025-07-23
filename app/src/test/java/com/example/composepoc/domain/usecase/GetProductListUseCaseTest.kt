package com.example.composepoc.domain.usecase

import app.cash.turbine.test
import com.example.composepoc.core.common.UiState
import com.example.composepoc.domain.model.ProductItem
import com.example.composepoc.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi // For TestDispatchers and runTest
class GetProductListUseCaseTest {

    //Declare Mock, TestDispatcher, and Class Under Test
    private lateinit var mockRepository: Repository
    private lateinit var getProductListUseCase: GetProductListUseCase
    private val testDispatcher = UnconfinedTestDispatcher() // Executes tasks eagerly

    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher) // Replace Main dispatcher with TestDispatcher
        mockRepository = mock()
        getProductListUseCase = GetProductListUseCase(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        // Reset Main dispatcher to the original one
    }

    @Test
    fun `invoke when repository returns product list successfully should emit Loading then Success`() =
        runTest {

            // given
            val mockProductItemList = listOf(
                ProductItem(
                    1,
                    "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                    "Product A",
                    "Hi"
                )
            )
            whenever(mockRepository.getProductList()).thenReturn(mockProductItemList)

            // Act & Assert using Turbine
            getProductListUseCase.invoke().test {
                // 1. Expect Loading state
                val loadingState = awaitItem()
                Assert.assertTrue(loadingState is UiState.Loading<List<ProductItem>>)
                Assert.assertNull((loadingState as UiState.Loading<List<ProductItem>>).data) // As per your UseCase, Loading() is emitted without data

                // 2. Expect Success state with data
                val successState = awaitItem()
                Assert.assertTrue(successState is UiState.Success<List<ProductItem>>)
                assertEquals(
                    mockProductItemList,
                    (successState as UiState.Success<List<ProductItem>>).data
                )
                Assert.assertNull(successState.message) // Success state should not have a message
                // 3. Expect completion of the flow
                awaitComplete()
            }

            // Verify that repository.getProductList() was called once
            verify(mockRepository).getProductList()

        }

    @Test
    fun `invoke when repository throws exception should emit Loading then Error`() = runTest {
        // Arrange
        val errorMessage = "Network error"
        whenever(mockRepository.getProductList()).thenThrow(RuntimeException(errorMessage))

        // Act & Assert using Turbine
        getProductListUseCase.invoke().test {
            // 1. Expect Loading state
            val loadingState = awaitItem()
            Assert.assertTrue(loadingState is UiState.Loading<List<ProductItem>>)
            Assert.assertNull((loadingState as UiState.Loading<List<ProductItem>>).data) // As per your UseCase, Loading() is emitted without data


            // 2. Expect Error state with the message
            val errorState = awaitItem()
            Assert.assertTrue(errorState is UiState.Error)
            assertEquals(errorMessage, (errorState as UiState.Error).message)

            // 3. Expect completion of the flow
            awaitComplete()
        }
        // Verify that repository.getProductList() was called once
        verify(mockRepository).getProductList()
    }
}