package com.appleroid.mkung.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.appleroid.core.ui.TrackDisposableJank
import com.appleroid.feature.ask.navigation.ASK_ROUTE
import com.appleroid.feature.ask.navigation.navigationToAskGraph
import com.appleroid.feature.home.navigation.HOME_ROUTE
import com.appleroid.feature.home.navigation.navigationToHomeGraph
import com.appleroid.feature.mypage.navigation.MY_PAGE_ROUTE
import com.appleroid.feature.mypage.navigation.navigationToMyPageGraph
import com.appleroid.mkung.navigation.TopDestination
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberMKungState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
) : MKungAppState{
    NavigationTrackingSideEffect(navController)
    return remember(
        navController,
        coroutineScope
    ) {
        MKungAppState(
            navController,
            coroutineScope
        )
    }
}


@Stable
class MKungAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
){

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopDestination?
        @Composable get() = when (currentDestination?.route) {
            HOME_ROUTE -> TopDestination.HOME
            ASK_ROUTE -> TopDestination.ASK
            MY_PAGE_ROUTE -> TopDestination.MY_PAGE
            else -> null
        }

    val topDestinations : List<TopDestination> = TopDestination.entries

    fun navigateToTopDestination(topDestination: TopDestination) {
        trace("Navigation: ${topDestination.name}") {
            val topNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topDestination) {
                TopDestination.HOME -> navController.navigationToHomeGraph(topNavOptions)
                TopDestination.ASK -> navController.navigationToAskGraph(topNavOptions)
                TopDestination.MY_PAGE -> navController.navigationToMyPageGraph(topNavOptions)
            }
        }
    }

}


@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            metricsHolder.state?.putState("Navigation", destination.route.toString())
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}
