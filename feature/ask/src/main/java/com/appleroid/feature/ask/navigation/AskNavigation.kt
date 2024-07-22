package com.appleroid.feature.ask.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appleroid.feature.ask.AskRoute

const val ASK_GRAPH_ROUTE_PATTERN = "ask_graph"
const val ASK_ROUTE = "ask_route"

fun NavController.navigationToAskGraph(navOptions: NavOptions? = null){
    this.navigate(ASK_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavGraphBuilder.askGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = ASK_GRAPH_ROUTE_PATTERN,
        startDestination = ASK_ROUTE,
    ){
        composable(route = ASK_ROUTE) {
            AskRoute()
        }
        nestedGraphs()
    }
}
