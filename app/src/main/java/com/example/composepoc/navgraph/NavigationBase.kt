package com.example.composepoc.navgraph

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument

class BaseNavigationCommand(
    override val argument: List<NamedNavArgument> = emptyList(),
    override val navOptions: NavOptions = NavOptions.Builder().build(),
    override val destination: String = "",
    override val screenName: String = "",
    override val saveStatePopupResult: String = "",
) : NavigationCommand

/* Nested nav Graph routes*/
object NavGraphRoute {
    const val HOME = "NavGraphRouteHome"
}

object NavigationBase {
    val POP_BACK_STACK = BaseNavigationCommand(
        destination = Route.Control.POP_BACK_STACK,
        screenName = ScreenName.POP_BACK_STACK,
    )

    val NAVIGATE_UP = BaseNavigationCommand(
        destination = Route.Control.NAVIGATE_UP,
        screenName = ScreenName.NAVIGATE_UP,
    )

    fun popBackStackWithNav(
        route: String,
        inclusive: Boolean,
        saveData: Boolean,
    ): BaseNavigationCommand {
        return BaseNavigationCommand(
            destination = Route.Control.POP_BACK_STACK_WITH_NAV,
            screenName = ScreenName.POP_BACK_STACK_WITH_NAV,
            navOptions = NavOptions.Builder().setPopUpTo(route, inclusive, saveData).build(),
        )
    }

    fun popBackStackWithNavArguments(
        route: String,
        inclusive: Boolean,
        saveData: Boolean,
        saveStateResult: String,
    ): BaseNavigationCommand {
        return BaseNavigationCommand(
            destination = Route.Control.POP_BACK_STACK_WITH_NAV,
            screenName = ScreenName.POP_BACK_STACK_WITH_NAV,
            navOptions = NavOptions.Builder().setPopUpTo(route, inclusive, saveData).build(),
            saveStatePopupResult = saveStateResult,
        )
    }

    val PRODUCT_LIST =
        BaseNavigationCommand(
            destination = Route.PRODUCT_LIST,
            screenName = ScreenName.HOME
        )

    val PRODUCT_DETAILS =
        BaseNavigationCommand(
            destination = Route.PRODUCT_LIST,
            screenName = ScreenName.HOME,
            argument = listOf(
                navArgument(name = Arguments.USER_ID) {
                    type = NavType.StringType
                    nullable = true
                }
            )
        )

    val PRACTISE_UI = BaseNavigationCommand(
        destination = Route.PRACTISE_UI,
        screenName = ScreenName.HOME,
    )

    val DYNAMIC_UI = BaseNavigationCommand(
        destination = Route.DYNAMIC_UI,
        screenName = ScreenName.HOME,
    )


}