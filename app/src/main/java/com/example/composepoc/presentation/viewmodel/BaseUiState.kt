package com.example.composepoc.presentation.viewmodel

data class ProductState(val data: String) : BaseViewState

sealed class ProductEvent: BaseViewEvent {
    object Loading : ProductEvent()
    data class Success(val data: String) : ProductEvent()
    data class Error(val message: String) : ProductEvent()
}