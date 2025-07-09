package com.example.composepoc.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.composepoc.core.common.UiState
import com.example.composepoc.domain.usecase.GetProductDetailUseCase
import com.example.composepoc.presentation.state.ProductDetailState
import com.example.composepoc.presentation.state.ProductDetailsEvent
import com.example.composepoc.utils.SharedEventBus
import com.example.composepoc.utils.SharedUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailVewModel @Inject constructor(
    private val productDetailUseCase: GetProductDetailUseCase,
    private val sharedEventBus: SharedEventBus
) : CoroutineBaseViewModel<ProductDetailState, ProductDetailsEvent>() {

    override fun initState() = ProductDetailState()

    init {
        observeSharedEvent()
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

    fun getProductDetailAPi(id: String) {
        productDetailUseCase.invoke(id).onEach {
            when (it) {
                is UiState.Loading -> {
                    updateState { state ->
                        state.copy(
                            isLoading = true
                        )
                    }
                }

                is UiState.Success -> {
                    updateState { state ->
                        state.copy(
                            data = it.data
                        )
                    }
                }

                is UiState.Error -> {
                    updateState { state ->
                        state.copy(
                            error = it.message.toString()
                        )
                    }
                }

                else -> {
                    // to do
                }
            }
        }.launchIn(viewModelScope)
    }


}