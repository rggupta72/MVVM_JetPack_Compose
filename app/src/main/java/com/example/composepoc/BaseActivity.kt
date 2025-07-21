package com.example.composepoc

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.composepoc.navgraph.NavigationManager
import com.example.composepoc.navgraph.Route
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    @Composable
    fun HandleDeepLink(mainNavHostController: NavHostController) {
        val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

        DisposableEffect(true) {
            lifecycleScope.launch {
                lifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                    navigationManager.routeId.collect {
                        Timber.d("Performing navigation for routrId: $it")
                        var navController = mainNavHostController
                        try {
                            navController.navigate(it)
                        } catch (e: Exception) {
                            Timber.d("Exception", e.toString())
                            navToHome(navController)
                        }
                    }
                }


            }
            onDispose {
                navigationManager.clearRoute()
            }
        }

        LaunchedEffect(true) {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                navigationManager.command.collect {
                    val navController = mainNavHostController
                    try {
                        if (it.destination.isNotEmpty()) {
                            navController.navigate(it.destination, it.navOptions)
                        }
                    } catch (e: Exception) {
                        Timber.d("Navigation Exception", e.toString())
                        navToHome(navController)
                    }

                }
            }
        }
    }

    private fun navToHome(navController: NavController) {
        if (navController.currentBackStackEntry == null) {
            // Navigation graph is not set
            // handle this case (e.g., set the graph or show an error)
            Timber.d("Navigation", "Navigaion graph is not set")
        } else {
            navController.navigate(Route.PRODUCT_LIST)
        }
    }

}