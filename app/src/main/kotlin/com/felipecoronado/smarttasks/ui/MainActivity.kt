package com.felipecoronado.smarttasks.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.felipecoronado.smarttasks.ui.screens.tasks.TasksListScreen
import com.felipecoronado.smarttasks.ui.theme.SmartTasksTheme
import com.felipecoronado.smarttasks.ui.theme.YellowMain
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            SmartTasksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = YellowMain
                ) {
                   // LoadingScreen()
                    TasksListScreen()
                }
            }
        }
    }
}
