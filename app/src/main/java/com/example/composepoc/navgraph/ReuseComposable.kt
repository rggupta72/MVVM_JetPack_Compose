package com.example.composepoc.navgraph

import android.annotation.SuppressLint
import android.app.Activity
import android.view.WindowManager
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.ReuseComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    enterTransition: (
    @JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
    )? = null,
    exitTransition: (
    @JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
    )? = null,
    popEnterTransition: (
    @JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
    )? =
        enterTransition,
    popExitTransition: (
    @JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
    )? =
        exitTransition,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
    ) { navBackStackEntry ->

        // Safely get the activity
        val context = LocalContext.current
        val activity = remember(context) { context as? Activity } // Use remember for stability

        if (activity != null) { // Only manage flags if requested and activity is available
            ComposableLifecycleEffectWithActivity(activity = activity) { event ->
                when (event) {
                    Lifecycle.Event.ON_START -> {
                        activity.window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
                        // Log.d("FLAG_SECURE", "ON_START: FLAG_SECURE set for $route")
                    }

                    Lifecycle.Event.ON_STOP -> {
                        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
                        // Log.d("FLAG_SECURE", "ON_STOP: FLAG_SECURE cleared for $route")
                    }

                    else -> Unit
                    // Handle other lifecycle events if needed
                }
            }
        }
        //
        //        // *** THIS WAS THE MISSING PART ***
        //        // You need to call the content lambda that was passed to ReuseComposable
        content(navBackStackEntry)
    }
}


/**
 * A Composable that observes lifecycle events and provides the Activity.
 * This version is more specific to the FLAG_SECURE use case.
 */
@Composable
private fun ComposableLifecycleEffectWithActivity(
    activity: Activity, // Pass the activity directly
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (Lifecycle.Event) -> Unit, // Simplified event callback
) {
    DisposableEffect(
        lifecycleOwner,
        activity
    ) { // Add activity as a key if its instance could change
        val observer = LifecycleEventObserver { _, event ->
            onEvent(event)
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            // Important: Clear flags if the composable is disposed while ON_START was called
// but ON_STOP hasn't been called yet (e.g., quick navigation away).
// This check ensures we only clear if it was potentially set by this effect.

            if (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                // Check if the flag is currently set by any source before clearing.
                // This is a bit more robust but might be overkill if you are sure only this code manages it.
                if ((activity.window.attributes.flags and WindowManager.LayoutParams.FLAG_SECURE) != 0) {
                    // activity.window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
                    // Log.d("FLAG_SECURE", "ON_DISPOSE: FLAG_SECURE potentially cleared for ${activity.componentName.shortClassName}")
                }
            }
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
