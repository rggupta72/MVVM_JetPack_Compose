package com.example.composepoc.presentation.viewmodel

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.example.composepoc.adapter.HealthNeedItemViewState
import com.example.composepoc.adapter.getGmwHubViewStateList
import com.example.composepoc.core.common.UiState
import com.example.composepoc.domain.usecase.GetProductListUseCase
import com.example.composepoc.navgraph.Arguments
import com.example.composepoc.navgraph.NavigationManager
import com.example.composepoc.navgraph.Route
import com.example.composepoc.presentation.state.HealthNeedsState
import com.example.composepoc.presentation.state.ProductDetailsEvent
import com.example.composepoc.presentation.state.ProductListState
import com.example.composepoc.utils.SharedEventBus
import com.example.composepoc.utils.SharedUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListVewModel @Inject constructor(
    private val productListUseCase: GetProductListUseCase,
    private val sharedEventBus: SharedEventBus,
    private val navigationManager: NavigationManager
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

    override fun onEvent(event: ProductDetailsEvent) {
        when (event) {
            is ProductDetailsEvent.ProductList -> {
                navigationManager.navigate(Route.PRACTISE_UI+ "?${Arguments.USER_ID} = ${event.productCode}" +"?${Arguments.TITLE} = ${event.title}"+"?${Arguments.DESCRIPTION} = ${event.description}")
            }
            is ProductDetailsEvent.PractiseUi -> {
                navigationManager.navigate(Route.DYNAMIC_UI)
            }
        }

    }

    fun updateArgsBundle(args : Bundle?){
        args?.getInt(Arguments.USER_ID)?.let {
            updateState { state -> state.copy(productId = it) }
        }

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