package com.appleroid.feature.report.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appleroid.feature.report.ReportRoute

const val REPORT_GRAPH_ROUTE_PATTERN = "report_graph"
const val REPORT_ROUTE = "report_route"

fun NavController.navigationToReportGraph(navOptions: NavOptions? = null){
    this.navigate(REPORT_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavGraphBuilder.reportGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = REPORT_GRAPH_ROUTE_PATTERN,
        startDestination = REPORT_ROUTE,
    ){
        composable(route = REPORT_ROUTE) {
            ReportRoute()
        }
        nestedGraphs()
    }
}