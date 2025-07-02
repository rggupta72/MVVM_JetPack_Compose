package com.example.composepoc.core.common

import com.example.composepoc.utils.SharedUiEvent

sealed class UiState1<T>(val data: T?, val message: String? = null) {
    class Loading<T>(data: T?) : UiState1<T>(data)
    class Success<T>(data: T? = null) : UiState1<T>(data)
    class Error<T>(data: T? = null, message: String?) : UiState1<T>(data, message)
}