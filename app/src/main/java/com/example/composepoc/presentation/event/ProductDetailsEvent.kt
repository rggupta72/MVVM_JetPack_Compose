package com.example.composepoc.presentation.event

sealed class ProductDetailsEvent {
    data class ProductDetails(
        val productCode: Int,
        val title: String,
        val description: String,
    ) : ProductDetailsEvent()

    data object PractiseUi : ProductDetailsEvent()
}