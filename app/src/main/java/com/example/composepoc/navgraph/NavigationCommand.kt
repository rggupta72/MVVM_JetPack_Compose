package com.example.composepoc.navgraph

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptions

interface NavigationCommand {
    val argument: List<NamedNavArgument>
    val navOptions: NavOptions?
    val destination: String
    val screenName: String
    val saveStatePopupResult: String
}