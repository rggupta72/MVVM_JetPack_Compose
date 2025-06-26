package com.example.composepoc.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.composepoc.core.common.Event
import com.example.composepoc.core.common.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class DummyArrayViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _productListVewModel = MutableLiveData<Event<NavigationEvent>>()
    public val productListVewModel: LiveData<Event<NavigationEvent>>
        get() = _productListVewModel

    init {
        Log.d("NavArgument", savedStateHandle.get<String>("argument") ?: "")
    }
}