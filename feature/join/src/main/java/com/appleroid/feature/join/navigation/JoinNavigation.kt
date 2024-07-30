package com.appleroid.feature.join.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appleroid.feature.join.JoinRoute


const val JOIN_GRAPH_ROUTE_PATTERN = "join_graph"
const val JOIN_ROUTE = "join_route"

fun NavController.navigationToJoinGraph(navOptions: NavOptions? = null){
    this.navigate(JOIN_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavGraphBuilder.joinGraph(
    joinCompleteClicked: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = JOIN_GRAPH_ROUTE_PATTERN,
        startDestination = JOIN_ROUTE,
    ){
        composable(route = JOIN_ROUTE) {
            JoinRoute(joinCompleteClicked = joinCompleteClicked)
        }
        nestedGraphs()
    }
}
