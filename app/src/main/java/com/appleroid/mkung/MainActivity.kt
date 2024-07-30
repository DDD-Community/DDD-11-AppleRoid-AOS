package com.appleroid.mkung

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.appleroid.core.designsystem.component.theme.MKungTheme
import com.appleroid.mkung.ui.MKungApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()

        setContent {
            MKungTheme {
                CompositionLocalProvider(LocalLifecycleOwner provides LocalLifecycleOwner.current) {
                    MKungApp()
                }
            }
        }
    }
}
