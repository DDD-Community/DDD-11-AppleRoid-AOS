package com.appleroid.feature.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appleroid.feature.signup.SignUpRoute

const val SIGN_UP_GRAPH_ROUTE_PATTERN = "sign_up_graph"
const val SIGN_UP_ROUTE = "sign_up_route"

fun NavController.navigationToSignUpGraph(navOptions: NavOptions? = null) {
    this.navigate(SIGN_UP_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavGraphBuilder.signUpGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = SIGN_UP_GRAPH_ROUTE_PATTERN,
        startDestination = SIGN_UP_ROUTE,
    ) {
        composable(route = SIGN_UP_ROUTE) {
            SignUpRoute()
        }
        nestedGraphs()
    }
}
