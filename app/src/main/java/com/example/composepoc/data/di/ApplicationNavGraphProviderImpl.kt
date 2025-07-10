package com.example.composepoc.data.di

import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.createGraph
import com.example.composepoc.navgraph.NavGraphRoute
import com.example.composepoc.navgraph.NavigationBase
import com.example.composepoc.navgraph.ReuseComposable
import com.example.composepoc.view.listingScreen

class ApplicationNavGraphProviderImpl : ApplicationNavGraphProvider {
    override fun getApplicationNavGraph(
        navController: NavHostController,
        startDestination: String
    ): NavGraph {
        return navController.createGraph(startDestination = startDestination) {
            launcherGraph(navController)
        }
    }
}

fun NavGraphBuilder.launcherGraph(navHostController: NavHostController)
//    navigation(
//        route = NavGraphRoute.HOME,
//        startDestination = NavigationBase.PRODUCT_LIST.destination
//    )
    {
        ReuseComposable(route = NavigationBase.PRODUCT_LIST.destination) {
            listingScreen(
                "", ""
            ) {

            }
        }
    }

