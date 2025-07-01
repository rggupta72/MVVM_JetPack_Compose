package com.example.composepoc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepoc.adapter.HealthNeedItemViewState
import com.example.composepoc.adapter.getGmwHubViewStateList
import com.example.composepoc.core.common.UiState
import com.example.composepoc.domain.usecase.GetProductListUseCase
import com.example.composepoc.presentation.state.HealthNeedsState
import com.example.composepoc.presentation.state.ProductListState
import com.example.composepoc.utils.SharedEventBus
import com.example.composepoc.utils.SharedUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListVewModel @Inject constructor(
    private val productListUseCase: GetProductListUseCase,
    private val sharedEventBus: SharedEventBus
) : ViewModel() {

    private val _productList = MutableStateFlow(ProductListState())
    val productList = _productList.asStateFlow()

    private val _healthNeedsList = MutableStateFlow(HealthNeedsState())
    val healthNeedsList = _healthNeedsList.asStateFlow()

    init {
        productListUseCase.invoke().onEach {
            when (it) {
                is UiState.Loading -> {
                    _productList.value = ProductListState(isLoading = true)
                }

                is UiState.Success -> {
                    sharedEventBus.produceEvent(SharedUiEvent.ShowMessage("Event Trigger"))
                    _productList.value = ProductListState(data = it.data)
                }

                is UiState.Error -> {
                    _productList.value = ProductListState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
        observeSharedEvent()
    }

    fun getProductList() {

    }

    fun getListOfHealthNeedsData() {

        val healthNeedsData = listOf(
            HealthNeedItemViewState(
                label = "Pizza",
                ada = "Pizza",
                key = "Pizza"
            ),
            HealthNeedItemViewState(
                label = "Burger",
                ada = "Burger",
                key = "Burger"
            )
        )


        _healthNeedsList.value =
            HealthNeedsState(getGmwHubViewStateList(healthNeedsData ?: emptyList()))

    }

    private fun observeSharedEvent() {
        viewModelScope.launch {
            sharedEventBus.events.collectLatest { event ->
                when (event) {
                    is SharedUiEvent.ShowMessage -> {
                        println("abc ${event.message}")
                    }

                    SharedUiEvent.RefreshData -> {
                        println("abc")
                    }
                }

            }
        }
    }

}