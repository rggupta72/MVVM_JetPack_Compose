package com.example.composepoc.navgraph

sealed class NavRoute(val path: String) {

    object Home : NavRoute("home")

    object Profile : NavRoute("profile")

    object Setting : NavRoute("setting")

}