package com.example.composepoc.navgraph

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor(
    val coroutineScope: CoroutineScope,
) {

    companion object {
        const val ROUTE_CHANNEL_CAPACITY = 10
    }

    /*Channel to capture the NavigationCommand object*/
    private var _commands = Channel<NavigationCommand>()
    val command = _commands.receiveAsFlow()

    /*Channel to capture the router string, This must watch composable first parameter i.e. routeId*/
    private var _routeId = Channel<String>(capacity = ROUTE_CHANNEL_CAPACITY)
    val routeId = _routeId.receiveAsFlow().shareIn(coroutineScope, SharingStarted.Lazily, 1)
        .transform { route ->
            Timber.tag("NavIntercept").d("Incoming route:$route")
            emit(route)
        }

    fun navigate(route: String) {
        coroutineScope.launch {
            _routeId.trySend(route)
        }

    }

    fun clearRoute(){
        _routeId.trySend("")
    }

    fun navigate(direction: NavigationCommand) {
        _commands.trySend(direction)
    }

    fun navigateUp() = navigate(NavigationBase.NAVIGATE_UP)

    fun popUpBackStack() = navigate(NavigationBase.POP_BACK_STACK)
}