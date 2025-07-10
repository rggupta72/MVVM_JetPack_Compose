package com.example.composepoc.navgraph

import org.hamcrest.Description

object Arguments {
    const val USER_ID = "userId"
    const val TITLE = "title"
    const val DESCRIPTION = "description"
}

object ScreenName {
    const val POP_BACK_STACK = "pop_back_stack"
    const val POP_BACK_STACK_WITH_NAV = "pop_back_stack_with_nav"
    const val NAVIGATE_UP = "navigate_up"
    const val POP_BACK_STACK_WITH_NAV_WITH_ARG = "pop_back_stack_with_nav_with_arg"


    const val HOME = "home"
}

object Route {
    const val PRODUCT_LIST = "productList"
    const val PRACTISE_UI = "practiseUi"
    const val Dummy_DYNAMIC_UI = "dummyDynamicUi"
    const val Dummy_UI = "dummyUi"
    const val DYNAMIC_UI = "dynamicUi"

    object Control {
        const val POP_BACK_STACK = "close"
        const val POP_BACK_STACK_WITH_NAV = "pop_backstack_with_nav"
        const val POP_BACK_UP_TO = "pop_back_up_to"
        const val POP_BACK_UP_TO_HOME = "pop_back_up_to_home"
        const val POP_UP_TO_CURRENT_WITH_NAVIGATE = "pop_up_to_current_with_navigate"
        const val NAVIGATE_UP = "navigate_up"
        const val POP_BACK_STACK_WITH_NAV_WITH_ARG = "pop_back_stack_with_nav_with_arg"

    }
}