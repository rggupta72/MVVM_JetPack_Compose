package com.example.composepoc.navgraph

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor() {

    companion object {

    }

    /*Channel to capture the NavigationCommand object*/
    private var _commands = Channel<NavigationCommand>()
    val command = _commands.receiveAsFlow()

    fun navigate(direction: NavigationCommand) {
        _commands.trySend(direction)
    }

    fun navigateUp() = navigate(NavigationBase.NAVIGATE_UP)

    fun popUpBackStack() = navigate(NavigationBase.POP_BACK_STACK)
}