package com.example.composepoc.presentation.state

sealed class ProductDetailsEvent {
    data class ProductDetails(val productCode: Int) : ProductDetailsEvent()
}