package com.appleroid.mkung

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import com.appleroid.feature.nickname.navigation.NICK_NAME_GRAPH_ROUTE_PATTERN
import com.appleroid.feature.nickname.navigation.nickNameGraph
import com.appleroid.feature.phoneverify.navigation.PHONE_VERIFY_GRAPH_ROUTE_PATTERN
import com.appleroid.feature.phoneverify.navigation.phoneVerifyGraph
import com.appleroid.mkung.ui.rememberMKungState
import com.appleroid.mkung.ui.theme.MKungTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()

        setContent {
            MKungTheme {
                val appState = rememberMKungState()
                /*NavHost(
                    navController = appState.navController,
                    startDestination = PHONE_VERIFY_GRAPH_ROUTE_PATTERN) {
                    phoneVerifyGraph {}
                }*/

                NavHost(
                    navController = appState.navController,
                    startDestination = NICK_NAME_GRAPH_ROUTE_PATTERN) {
                    nickNameGraph {  }
                }

                /*CompositionLocalProvider(LocalLifecycleOwner provides LocalLifecycleOwner.current) {
                    MKungApp()
                }*/
            }
        }
    }
}
