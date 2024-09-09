package com.felipecoronado.smarttasks.ui.models

data class TaskModel(
    val id: String = "",
    val targetDate: String = "",
    val dueDate: String = "",
    val title: String = "",
    val description: String = "",
    val priority: Int = 0,
    val resolvedStatus: ResolvedStatus = ResolvedStatus.UNRESOLVED
)

enum class ResolvedStatus {
    UNRESOLVED,
    RESOLVED,
    CANT_RESOLVE
}