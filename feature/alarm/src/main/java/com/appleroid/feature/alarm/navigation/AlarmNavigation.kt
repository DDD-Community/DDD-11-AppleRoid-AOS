package com.appleroid.feature.alarm.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appleroid.feature.alarm.AlarmRoute

const val ALARM_GRAPH_ROUTE_PATTERN = "alarm_graph"
const val ALARM_ROUTE = "alarm_route"

fun NavController.navigationToAlarmGraph(navOptions: NavOptions? = null){
    this.navigate(ALARM_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavGraphBuilder.alarmGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = ALARM_GRAPH_ROUTE_PATTERN,
        startDestination = ALARM_ROUTE,
    ){
        composable(route = ALARM_ROUTE) {
            AlarmRoute()
        }
        nestedGraphs()
    }
}
