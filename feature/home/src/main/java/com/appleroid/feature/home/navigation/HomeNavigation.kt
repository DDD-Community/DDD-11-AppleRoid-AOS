package com.appleroid.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appleroid.feature.home.HomeRoute


const val HOME_GRAPH_ROUTE_PATTERN = "home_graph"
const val HOME_ROUTE = "home_route"

fun NavController.navigationToHomeGraph(navOptions: NavOptions? = null){
    this.navigate(HOME_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavGraphBuilder.homeGraph(
    reportBtnClicked: () -> Unit,
    alarmBtnClicked: () -> Unit,
    askBtnClicked: () -> Unit,
    myPageBtnClicked: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = HOME_GRAPH_ROUTE_PATTERN,
        startDestination = HOME_ROUTE,
    ){
        composable(route = HOME_ROUTE) {
            HomeRoute(
                reportBtnClicked = reportBtnClicked,
                alarmBtnClicked = alarmBtnClicked,
                askBtnClicked = askBtnClicked,
                myPageBtnClicked = myPageBtnClicked
            )
        }
        nestedGraphs()
    }
}
