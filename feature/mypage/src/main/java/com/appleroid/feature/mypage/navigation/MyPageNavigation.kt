package com.appleroid.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appleroid.feature.mypage.MyPageRoute


const val MY_PAGE_GRAPH_ROUTE_PATTERN = "my_page_graph"
const val MY_PAGE_ROUTE = "my_page_route"

fun NavController.navigationToMyPageGraph(navOptions: NavOptions? = null){
    this.navigate(MY_PAGE_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavGraphBuilder.myPageGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = MY_PAGE_GRAPH_ROUTE_PATTERN,
        startDestination = MY_PAGE_ROUTE,
    ){
        composable(route = MY_PAGE_ROUTE) {
            MyPageRoute()
        }
        nestedGraphs()
    }
}
