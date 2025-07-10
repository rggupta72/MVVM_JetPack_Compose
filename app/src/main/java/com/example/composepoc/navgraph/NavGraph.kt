package com.example.composepoc.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composepoc.domain.DataManager
import com.example.composepoc.presentation.viewmodel.ProductListVewModel
import com.example.composepoc.view.DynamiCUi
import com.example.composepoc.view.dummyUi
import com.example.composepoc.view.listingScreen
import com.example.composepoc.view.practise1


@Composable
fun Navgraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavRoute.Home.path) {
        addHomeScreen(navController, this)
        addProfileScreen(navController, this)
        addSettingScreen(navController, this)
        addDynamicScreen(navController, this)
    }
}

fun addHomeScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {

    navGraphBuilder.composable(route = NavRoute.Home.path) {
        val viewModel : ProductListVewModel = hiltViewModel()
        listingScreen(
            viewModel::onEvent,
            viewModel
        )
    }

}

fun addProfileScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(route = NavRoute.Profile.path) {
        practise1 {
            navController.navigate(NavRoute.Setting.path)
        }

    }
}

fun addSettingScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(route = NavRoute.Setting.path) {
        dummyUi(DataManager.data) {
            navController.navigate(NavRoute.DynamicUi.path)
        }
    }
}

fun addDynamicScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(route = NavRoute.DynamicUi.path) {
        DynamiCUi()
    }
}