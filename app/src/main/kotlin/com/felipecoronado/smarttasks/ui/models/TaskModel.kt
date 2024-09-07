package com.felipecoronado.smarttasks.ui.models

data class TaskModel(
    val id: String,
    val targetDate: String,
    val dueDate: String,
    val title: String,
    val description: String,
    val priority: Int = 0
)
