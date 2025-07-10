package com.example.composepoc.data.di

import androidx.navigation.NavGraph
import androidx.navigation.NavHostController

/*
* Interface to get Application Navigation Graph
* */
interface ApplicationNavGraphProvider {
    fun getApplicationNavGraph(
        navController: NavHostController,
        startDestination: String
    ): NavGraph
}