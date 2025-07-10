package com.example.composepoc.presentation.state

sealed class ProductDetailsEvent {
    data class ProductList(
        val productCode: Int,
        val title: String,
        val description: String,
    ) : ProductDetailsEvent()

    data object PractiseUi : ProductDetailsEvent()
}