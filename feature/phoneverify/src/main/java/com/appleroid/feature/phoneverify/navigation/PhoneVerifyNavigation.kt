package com.appleroid.feature.phoneverify.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appleroid.feature.phoneverify.PhoneVerifyRoute

const val PHONE_VERIFY_GRAPH_ROUTE_PATTERN = "phone_verify_graph"
const val PHONE_VERIFY_ROUTE = "phone_verify_route"

fun NavController.navigationToPhoneVerifyGraph(navOptions: NavOptions? = null) {
    this.navigate(PHONE_VERIFY_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavGraphBuilder.phoneVerifyGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = PHONE_VERIFY_GRAPH_ROUTE_PATTERN,
        startDestination = PHONE_VERIFY_ROUTE,
    ) {
        composable(route = PHONE_VERIFY_ROUTE) {
            PhoneVerifyRoute()
        }
        nestedGraphs()
    }
}
