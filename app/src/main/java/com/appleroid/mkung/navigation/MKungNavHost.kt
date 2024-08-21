package com.appleroid.mkung.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.appleroid.feature.ask.navigation.askGraph
import com.appleroid.feature.home.navigation.homeGraph
import com.appleroid.feature.home.navigation.navigationToHomeGraph
import com.appleroid.feature.join.navigation.JOIN_GRAPH_ROUTE_PATTERN
import com.appleroid.feature.join.navigation.joinGraph
import com.appleroid.feature.mypage.navigation.myPageGraph
import com.appleroid.feature.report.navigation.navigationToReportGraph
import com.appleroid.feature.report.navigation.reportGraph
import com.appleroid.mkung.ui.MKungAppState

@Composable
fun MKungNavHost(
    mKungAppState: MKungAppState,
    modifier: Modifier = Modifier,
    startDestination: String = JOIN_GRAPH_ROUTE_PATTERN
){
    val navController = mKungAppState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ){
        joinGraph(joinCompleteClicked = navController::navigationToHomeGraph) {}
        homeGraph(reportBtnClicked = navController::navigationToReportGraph) { }
        askGraph{ }
        myPageGraph{ }
        reportGraph(backBtnClicked = navController::navigationToHomeGraph) { }
    }
}