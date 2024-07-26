package com.appleroid.feature.mbti.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appleroid.feature.mbti.MbtiRoute

const val MBTI_GRAPH_ROUTE_PATTERN = "mbti_graph"
const val MBTI_ROUTE = "mbti_route"

fun NavController.navigationToMbtiGraph(navOptions: NavOptions? = null){
    this.navigate(MBTI_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavGraphBuilder.mbtiGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = MBTI_GRAPH_ROUTE_PATTERN,
        startDestination = MBTI_ROUTE,
    ){
        composable(route = MBTI_ROUTE) {
            MbtiRoute()
        }
        nestedGraphs()
    }
}
