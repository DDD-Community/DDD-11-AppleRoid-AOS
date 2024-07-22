package com.appleroid.feature.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appleroid.feature.splash.SplashRoute

const val SPLASH_GRAPH_ROUTE_PATTERN = "splash_up_graph"
const val SPLASH_ROUTE = "splash_up_route"

fun NavController.navigationToSplashGraph(navOptions: NavOptions? = null) {
    this.navigate(SPLASH_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavGraphBuilder.splashGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = SPLASH_GRAPH_ROUTE_PATTERN,
        startDestination = SPLASH_ROUTE,
    ) {
        composable(route = SPLASH_ROUTE) {
            SplashRoute()
        }
        nestedGraphs()
    }
}
