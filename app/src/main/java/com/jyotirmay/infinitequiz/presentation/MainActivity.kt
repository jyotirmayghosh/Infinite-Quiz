package com.jyotirmay.infinitequiz.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jyotirmay.infinitequiz.ui.theme.InfiniteQuizTheme
import com.jyotirmay.infinitequiz.navgraph.NavGraph
import com.jyotirmay.infinitequiz.navgraph.Route
import com.jyotirmay.infinitequiz.ui.theme.Purple80
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            InfiniteQuizTheme {
                NavGraph(startDestination = Route.HomeScreen.route)
            }
        }
    }
}