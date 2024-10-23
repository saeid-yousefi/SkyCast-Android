package com.sy.skycast.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sy.common_ui.theme.SkyCastTheme
import com.sy.onboarding_ui.navigation.OnBoardingScreens
import com.sy.skycast.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkyCastTheme {
                MainScreen()
            }
        }
    }
}

