package com.appleroid.feature.nickname.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appleroid.feature.nickname.NickNameRoute

const val NICK_NAME_GRAPH_ROUTE_PATTERN = "nick_name_graph"
const val NICK_NAME_ROUTE = "nick_name_route"

fun NavController.navigationToNickNameGraph(navOptions: NavOptions? = null){
    this.navigate(NICK_NAME_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavGraphBuilder.nickNameGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = NICK_NAME_GRAPH_ROUTE_PATTERN,
        startDestination = NICK_NAME_ROUTE,
    ){
        composable(route = NICK_NAME_ROUTE) {
            NickNameRoute()
        }
        nestedGraphs()
    }
}
