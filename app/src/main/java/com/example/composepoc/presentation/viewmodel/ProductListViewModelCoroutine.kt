package com.example.composepoc.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.composepoc.PublishSubjectEvent
import com.example.composepoc.PublishSubjectNotifier
import com.example.composepoc.core.common.UiState
import com.example.composepoc.domain.usecase.GetProductListUseCase
import com.example.composepoc.utils.SharedEventBus
import com.example.composepoc.utils.SharedUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class ProductListViewModelCoroutine @Inject constructor(
    private val productListUseCase: GetProductListUseCase,
    private val sharedEventBus: SharedEventBus
) :
    CoroutineBaseViewModel<ProductState, ProductEvent>() {

    fun initObserver() {
        launchJob {
            PublishSubjectNotifier.listen(PublishSubjectEvent::class.java)
                .observeOn(AndroidSchedulers.mainThread()).subscribe {
                    it?.let {
                        when (it) {
                            is PublishSubjectEvent.AppointmentsEvent -> {
                                // to do
                            }

                            else -> {
                                // to do
                            }
                        }
                    }
                }
        }

    }

    fun getApiCalled() {
        viewModelScope.launch(Dispatchers.Main) {
            _uiEvent.value = ProductEvent.Loading
            try {
                val abc = productListUseCase.getApi()
                when (abc) {
                    is UiState.Loading -> {
                        _uiEvent.value = ProductEvent.Loading
                    }

                    is UiState.Success -> {
                        sharedEventBus.produceEvent(SharedUiEvent.ShowMessage("Event Trigger"))
                        _uiState.value = ProductState(data = abc.data!![0].title)
                    }

                    is UiState.Error -> {
                        _uiEvent.value = ProductEvent.Error(message = abc.message.toString())
                    }
                }

            } catch (e: Exception) {

            }
        }
    }
}