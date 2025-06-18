package com.example.composepoc.utils

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton


sealed class SharedUiEvent{
    data class ShowMessage(val message: String) : SharedUiEvent()
    data object RefreshData : SharedUiEvent()
}

@Singleton
class SharedEventBus @Inject constructor(){

    private val _events = MutableSharedFlow<SharedUiEvent>() // private mutable shared flow
    val events = _events.asSharedFlow() // publicly exposed as read-only shared flow

    suspend fun produceEvent(event: SharedUiEvent) {
        _events.emit(event) // suspends until all subscribers receive it
    }
}


