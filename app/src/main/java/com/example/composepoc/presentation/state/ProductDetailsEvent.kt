package com.example.composepoc.presentation.state

sealed class ProductDetailsEvent {
    data class PractiseUi(val productCode: Int) : ProductDetailsEvent()
}