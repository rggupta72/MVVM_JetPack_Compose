package com.example.composepoc.presentation.viewmodel

import app.cash.turbine.test
import com.example.composepoc.MainDispatcherRule
import com.example.composepoc.core.common.UiState
import com.example.composepoc.domain.model.ProductItem
import com.example.composepoc.domain.usecase.GetProductListUseCase
import com.example.composepoc.navgraph.NavigationManager
import com.example.composepoc.presentation.state.ProductListState
import com.example.composepoc.utils.SharedEventBus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ProductListVewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var mockProductListUseCase: GetProductListUseCase
    private lateinit var mockNavigationManager: NavigationManager // Mocked, not used here
    private lateinit var sharedEventBus: SharedEventBus // Mocked, not used here
    private lateinit var viewModel: ProductListVewModel
    private val testDispatcher get() = mainDispatcherRule.testDispatcher // Convenience accessor
    @Before
    fun setUp() {
        mockProductListUseCase = mock()
        mockNavigationManager = mock()
        sharedEventBus = mock()
        // ViewModel is initialized in each test after mocking the use case
    }

    private fun initializeViewModel(useCaseFlow: Flow<UiState<List<ProductItem>>>) {
        whenever(mockProductListUseCase.invoke()).thenReturn(useCaseFlow)
        viewModel =
            ProductListVewModel(mockProductListUseCase, sharedEventBus, mockNavigationManager)
        // The init block's viewModelScope.launch will use the testDispatcher
    }

    @Test
    fun `initial state is correct before use case emits`() {
        // Arrange: Use a flow that won't emit immediately
        val nonEmittingFlow = MutableSharedFlow<UiState<List<ProductItem>>>()
        initializeViewModel(nonEmittingFlow.asSharedFlow())
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert: Check the state set by initState()
        val expectedInitialState = ProductListState()
        Assert.assertEquals(expectedInitialState, viewModel.uiState.value)
        verify(mockProductListUseCase).invoke() // Verify use case was
    }

    @Test
    fun `when useCase emits Loading, uiState reflects loading`() =
        runTest { // runTest uses the testDispatcher from the rule
            // Arrange
            val useCaseEmitter = MutableSharedFlow<UiState<List<ProductItem>>>()
            initializeViewModel(useCaseEmitter.asSharedFlow())

            // Act
            useCaseEmitter.emit(UiState.Loading())
            testDispatcher.scheduler.advanceUntilIdle() // Let the init block's coroutine process the emission

            // Assert
            viewModel.uiState.test {
                val emittedState = awaitItem() // Get the current state
                Assert.assertTrue("isLoading should be true", emittedState.isLoading)
                Assert.assertNull("Data should be null during loading", emittedState.data)
                Assert.assertNull("Error should be null during loading", emittedState.error)

                cancelAndConsumeRemainingEvents() // Clean up Turbine
            }
        }
}