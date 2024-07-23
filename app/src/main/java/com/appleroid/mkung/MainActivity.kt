package com.appleroid.mkung

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.appleroid.feature.signup.navigation.SIGN_UP_GRAPH_ROUTE_PATTERN
import com.appleroid.feature.signup.navigation.navigationToSignUpGraph
import com.appleroid.feature.signup.navigation.signUpGraph
import com.appleroid.mkung.ui.MKungApp
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
                NavHost(
                    navController = appState.navController,
                    startDestination = SIGN_UP_GRAPH_ROUTE_PATTERN) {
                    signUpGraph {

                    }
                }

                /*CompositionLocalProvider(LocalLifecycleOwner provides LocalLifecycleOwner.current) {
                    MKungApp()
                }*/
            }
        }
    }
}
