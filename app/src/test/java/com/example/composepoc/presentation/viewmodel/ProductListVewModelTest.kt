package com.example.composepoc.presentation.viewmodel

import com.example.composepoc.MainDispatcherRule
import com.example.composepoc.core.common.UiState
import com.example.composepoc.domain.model.ProductItem
import com.example.composepoc.domain.usecase.GetProductListUseCase
import com.example.composepoc.navgraph.Arguments
import com.example.composepoc.navgraph.NavigationManager
import com.example.composepoc.navgraph.Route
import com.example.composepoc.presentation.event.ProductDetailsEvent
import com.example.composepoc.presentation.state.ProductListState
import com.example.composepoc.utils.SharedEventBus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOf
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
    fun `when useCase emits Loading, uiState reflects loading`() { // runTest uses the testDispatcher from the rule
        // Arrange
        val useCaseEmitter = MutableSharedFlow<UiState<List<ProductItem>>>()
        initializeViewModel(useCaseEmitter.asSharedFlow())

        // Act
        viewModel.startLoading()
        testDispatcher.scheduler.advanceUntilIdle() // Let the init block's coroutine process the emission

        val expectedInitialState = ProductListState(true, null, "", 0)
        Assert.assertEquals(expectedInitialState, viewModel.uiState.value)
        verify(mockProductListUseCase).invoke() // Verify use case was
    }

    @Test
    fun `when useCase emits Success, uiState reflects success`() { // runTest uses the testDispatcher from the rule
        val expectedProductItemList = listOf(
            ProductItem(
                1,
                "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                "Product A",
                "Hi"
            )
        )
        // Arrange
        val useCaseEmitter = MutableSharedFlow<UiState<List<ProductItem>>>()
        initializeViewModel(useCaseEmitter.asSharedFlow())
        whenever(mockProductListUseCase.invoke()).thenReturn(
            flowOf(
                UiState.Success(
                    expectedProductItemList
                )
            )
        )
        // Act
        testDispatcher.scheduler.advanceUntilIdle() // Let the init block's coroutine process the emission

        val expectedInitialState =
            ProductListState(isLoading = false, expectedProductItemList, "", 0)
        Assert.assertEquals(expectedInitialState, viewModel.uiState.value)
        verify(mockProductListUseCase).invoke() // Verify use case was
    }

    @Test
    fun `when useCase emits Error, uiState reflects error`() { // runTest uses the testDispatcher from the rule
        // Arrange
        val useCaseEmitter = MutableSharedFlow<UiState<List<ProductItem>>>()
        initializeViewModel(useCaseEmitter.asSharedFlow())
        val errorMessage = "Network error"
        whenever(mockProductListUseCase.invoke()).thenReturn(
            flowOf(
                UiState.Error(
                    message = errorMessage
                )
            )
        )
        // Act
        testDispatcher.scheduler.advanceUntilIdle() // Let the init block's coroutine process the emission

        val expectedInitialState =
            ProductListState(isLoading = false, null, errorMessage, 0)
        Assert.assertEquals(expectedInitialState, viewModel.uiState.value)
        verify(mockProductListUseCase).invoke() // Verify use case was
    }

    @Test
    fun `onEvent ProductDetails - navigates to PractiseUI with correct arguments`() {
        val useCaseEmitter = MutableSharedFlow<UiState<List<ProductItem>>>()
        initializeViewModel(useCaseEmitter.asSharedFlow())
        // Arrange
        val productCode = 123
        val title = "Test Product"
        val description = "This is a test description."
        val event = ProductDetailsEvent.ProductDetails(productCode, title, description)

        val expectedRoute = "${Route.PRACTISE_UI}?${Arguments.USER_ID} = ${productCode}?${Arguments.TITLE} = ${title}?${Arguments.DESCRIPTION} = ${description}"

        // Act
        viewModel.onEvent(event)

        // Assert
        // Verify that navigationManager.navigate() was called with the expected route string
        verify(mockNavigationManager).navigate(expectedRoute)
    }

    @Test
    fun `onEvent ProductDetails - navigates to DYNAMIC_UI with correct arguments`() {
        val useCaseEmitter = MutableSharedFlow<UiState<List<ProductItem>>>()
        initializeViewModel(useCaseEmitter.asSharedFlow())
        // Arrange
        val event = ProductDetailsEvent.PractiseUi

        val expectedRoute = Route.DYNAMIC_UI

        // Act
        viewModel.onEvent(event)

        // Assert
        // Verify that navigationManager.navigate() was called with the expected route string
        verify(mockNavigationManager).navigate(expectedRoute)
    }
}