package com.felipecoronado.smarttasks.ui.screens.tasks

import com.felipecoronado.smarttasks.ui.models.TaskModel

data class TasksListViewState(
    val tasks: List<TaskModel> = emptyList(),
    val loading: Boolean = true,
    val error: String? = null
)