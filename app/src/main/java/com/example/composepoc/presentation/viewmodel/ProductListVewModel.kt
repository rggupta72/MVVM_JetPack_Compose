package com.example.composepoc.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepoc.adapter.BaseRecyclerViewItemState
import com.example.composepoc.adapter.HealthNeedItemViewState
import com.example.composepoc.adapter.HealthNeedsViewType
import com.example.composepoc.adapter.getGmwHubViewStateList
import com.example.composepoc.utils.SharedEventBus
import com.example.composepoc.utils.SharedUiEvent
import com.example.composepoc.core.common.UiState
import com.example.composepoc.domain.usecase.GetProductListUseCase
import com.example.composepoc.presentation.state.HealthNeedsState
import com.example.composepoc.presentation.state.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _productList = mutableStateOf(ProductListState())
    val productList: State<ProductListState> get() = _productList

    private val _healthNeedsList = mutableStateOf(HealthNeedsState())
    val healthNeedsList: State<HealthNeedsState> get() = _healthNeedsList

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