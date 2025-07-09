package com.example.composepoc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class CoroutineBaseViewModel<VIEW_STATE, VIEW_EVENT> : ViewModel() {

    protected abstract fun initState(): VIEW_STATE
    protected val _uiState = MutableStateFlow(initState())

    val uiState = _uiState.asStateFlow()

    open fun onEvent(event: VIEW_EVENT) = Unit

    protected fun updateState(newState: (currentState:VIEW_STATE ) -> VIEW_STATE){
        _uiState.update{ state->
            newState(state)
        }
    }


}