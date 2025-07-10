package com.example.composepoc

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.example.composepoc.navgraph.NavigationManager
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    @Composable
    fun HandleDeepLink(mainNavHostController: NavHostController) {
        val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

        LaunchedEffect(true) {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                navigationManager.command.collect {
                    try {
                        var navController = mainNavHostController
                        if(it.destination.isNotEmpty()) {
                            navController.navigate(it.destination, it.navOptions)
                        }
                    } catch (e: Exception){
                        Log.d("Exception",e.toString())
                    }

                }
            }
        }
    }

}