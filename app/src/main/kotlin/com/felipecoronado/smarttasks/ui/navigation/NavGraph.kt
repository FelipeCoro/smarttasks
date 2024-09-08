package com.felipecoronado.smarttasks.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.felipecoronado.smarttasks.ui.composables.SplashScreen
import com.felipecoronado.smarttasks.ui.screens.tasks.TasksListScreen
import com.felipecoronado.smarttasks.ui.screens.tasksdetails.TaskDetailScreen


@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashScreen.route) {
        composable(SplashScreen.route) {
            SplashScreen(navigateToTasksListScreen = { navController.navigate(TasksList.route) })
        }

        composable(TasksList.route) {
            TasksListScreen(navigateToTaskDetailScreen = { taskId ->
                navController.navigate("${TaskDetails.route}/$taskId") {
                    launchSingleTop = true
                }
            })
        }

        composable(
            route = "${TaskDetails.route}/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.StringType })
        ) { backStackEntry ->
            TaskDetailScreen(
                taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            ) {
                navController.navigate(TasksList.route)
            }
        }
    }
}
