package com.example.composepoc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepoc.adapter.HealthNeedItemViewState
import com.example.composepoc.adapter.getGmwHubViewStateList
import com.example.composepoc.core.common.UiState
import com.example.composepoc.domain.usecase.GetProductListUseCase
import com.example.composepoc.presentation.state.HealthNeedsState
import com.example.composepoc.presentation.state.ProductDetailState
import com.example.composepoc.presentation.state.ProductDetailsEvent
import com.example.composepoc.presentation.state.ProductListState
import com.example.composepoc.utils.SharedEventBus
import com.example.composepoc.utils.SharedUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
) : CoroutineBaseViewModel<ProductListState, ProductDetailsEvent>() {

    override fun initState() = ProductListState()

    private val _healthNeedsList = MutableStateFlow(HealthNeedsState())
    val healthNeedsList = _healthNeedsList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            productListUseCase.invoke().collectLatest {
                when (it) {
                    is UiState.Loading -> {
                        updateState { state ->
                            state.copy(isLoading = true)
                        }
                    }

                    is UiState.Success -> {
                        sharedEventBus.produceEvent(SharedUiEvent.ShowMessage("Event Trigger"))
                        updateState { state ->
                            state.copy(data = it.data)
                        }
                    }

                    is UiState.Error -> {
                        updateState { state ->
                            state.copy(error = it.message.toString())
                        }
                    }
                }
            }
        }
        observeSharedEvent()
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