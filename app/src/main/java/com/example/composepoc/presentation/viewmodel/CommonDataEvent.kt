package com.example.composepoc.presentation.viewmodel

sealed class CommonDataEvent {
    data object DynamicUi : CommonDataEvent()
    data class DummyUi(val abc: Int) : CommonDataEvent()
}