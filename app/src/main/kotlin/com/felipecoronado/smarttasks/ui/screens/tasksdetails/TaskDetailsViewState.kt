package com.felipecoronado.smarttasks.ui.screens.tasksdetails

import com.felipecoronado.smarttasks.ui.models.TaskModel

data class TaskDetailsViewState(
    val task:TaskModel = TaskModel(),
    val loading: Boolean = true,
    val error: String? = null
)