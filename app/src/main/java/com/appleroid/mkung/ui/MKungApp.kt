package com.appleroid.mkung.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.appleroid.core.designsystem.component.NavigationBarItem
import com.appleroid.core.designsystem.theme.GREY04
import com.appleroid.core.designsystem.theme.GREY06
import com.appleroid.mkung.navigation.MKungNavHost
import com.appleroid.mkung.navigation.TopDestination

@Composable
fun MKungApp(
    appState: MKungAppState = rememberMKungState(),
){
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        contentColor = Color.Black,
        containerColor = Color.Black,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if(appState.currentDestination.isTopDestinationInHierarchy(TopDestination.HOME) ||
                appState.currentDestination.isTopDestinationInHierarchy(TopDestination.ASK) ||
                appState.currentDestination.isTopDestinationInHierarchy(TopDestination.MY_PAGE))
            {
                BottomBar(
                    destinations = appState.topDestinations,
                    onNavigateToDestination = appState::navigateToTopDestination,
                    currentDestination = appState.currentDestination,
                    modifier = Modifier.testTag("BottomBar")
                )
            }
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .consumeWindowInsets(it)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                )
        ) {
            MKungNavHost(
                mKungAppState = appState,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


@Composable
private fun BottomBar(
    destinations: List<TopDestination>,
    onNavigateToDestination: (TopDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .background(GREY06)
    ){
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(GREY04)
        )

        NavigationBar(
            containerColor = GREY06,
        ) {
            destinations.forEach { destination ->
                val selected = currentDestination.isTopDestinationInHierarchy(destination)
                NavigationBarItem(
                    selected = selected,
                    onClick = { onNavigateToDestination(destination) },
                    icon = {
                        Icon(
                            painter = painterResource(destination.unselectedIcon),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(destination.selectedIcon),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    label = { Text(stringResource(destination.iconTextId)) }
                )
            }
        }
    }

}



private fun NavDestination?.isTopDestinationInHierarchy(destination: TopDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false