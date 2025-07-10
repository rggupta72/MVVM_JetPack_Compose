package com.example.composepoc.presentation.viewmodel

import com.example.composepoc.navgraph.NavigationManager
import com.example.composepoc.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : CoroutineBaseViewModel<CommonDataState, CommonDataEvent>() {
    override fun initState() = CommonDataState()

    override fun onEvent(event: CommonDataEvent) {
        when (event) {
            is CommonDataEvent.DummyUi -> {
                navigationManager.navigate(Route.Dummy_UI)
            }

            is CommonDataEvent.DynamicUi -> {
                navigationManager.navigate(Route.DYNAMIC_UI)
            }
        }
    }

}