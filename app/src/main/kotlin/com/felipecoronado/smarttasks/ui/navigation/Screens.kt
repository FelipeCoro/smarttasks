package com.felipecoronado.smarttasks.ui.navigation


const val TASK_ID = "taskId"

sealed class Screens(val route: String)
data object TasksList : Screens(route = "tasks_list_screen")
data object TaskDetails : Screens(route = "tasks_details_screen/{$TASK_ID}")
