package com.example.composepoc.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class CoroutineBaseViewModel<VIEW_STATE: BaseViewState, VIEW_EVENT: BaseViewEvent> : BaseViewModel() {

    protected val _uiState : MutableLiveData<VIEW_STATE> = MutableLiveData()
    protected val _uiEvent : MutableLiveData<VIEW_EVENT> = MutableLiveData()
    val uiState : LiveData<VIEW_STATE> get() = _uiState
    val uiEVENT : LiveData<VIEW_EVENT> get() = _uiEvent

}