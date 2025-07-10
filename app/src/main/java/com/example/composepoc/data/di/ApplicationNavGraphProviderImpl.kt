package com.example.composepoc.data.di

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.navigation
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import com.example.composepoc.navgraph.Arguments
import com.example.composepoc.navgraph.NavigationBase
import com.example.composepoc.navgraph.ReuseComposable
import com.example.composepoc.navgraph.Route
import com.example.composepoc.navgraph.getSharedViewModel
import com.example.composepoc.presentation.viewmodel.CommonViewModel
import com.example.composepoc.presentation.viewmodel.ProductListVewModel
import com.example.composepoc.view.DynamiCUi
import com.example.composepoc.view.dummyUi
import com.example.composepoc.view.listingScreen
import com.example.composepoc.view.practise1

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
        val viewModel: ProductListVewModel = hiltViewModel()
        listingScreen(
            viewModel::onEvent,
            viewModel
        )
    }

    ReuseComposable(
        route = Route.PRACTISE_UI + "?${Arguments.USER_ID}={USER_ID}"+"?${Arguments.TITLE}={TITLE}"+"?${Arguments.DESCRIPTION}={DESCRIPTION}",
        arguments =
        listOf(
            navArgument(Arguments.USER_ID) {
                type = NavType.IntType
                defaultValue = 0
                nullable = false
            }
        )
    ) {
        val viewModel: ProductListVewModel = hiltViewModel()
        practise1(viewModel::onEvent, viewModel, it.arguments)
    }

    navigation(
        startDestination = Route.Dummy_UI,
        route = Route.Dummy_DYNAMIC_UI
    ) {
        ReuseComposable(route = Route.Dummy_UI) {
            val viewModel = it.getSharedViewModel<CommonViewModel>(navController = navHostController)
            dummyUi(
                viewModel::onEvent,
                viewModel
            )
        }
        ReuseComposable(route = Route.DYNAMIC_UI) {
            val viewModel = it.getSharedViewModel<CommonViewModel>(navController = navHostController)
            DynamiCUi(
                viewModel::onEvent,
                viewModel
            )
        }

    }


}

