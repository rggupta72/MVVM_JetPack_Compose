package com.example.composepoc.presentation.viewmodel

import com.example.composepoc.navgraph.NavigationManager
import com.example.composepoc.navgraph.Route
import com.example.composepoc.presentation.event.LoginEvent
import com.example.composepoc.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : CoroutineBaseViewModel<LoginState, LoginEvent>() {

    override fun initState()=  LoginState()

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.ProductList -> {
                navigationManager.navigate(Route.PRODUCT_LIST)
            }
        }
    }

}