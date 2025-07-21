package com.example.composepoc.presentation.event

sealed class LoginEvent {
    data object ProductList : LoginEvent()
}