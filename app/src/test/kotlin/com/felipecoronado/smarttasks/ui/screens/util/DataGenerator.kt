package com.felipecoronado.smarttasks.ui.screens.util

import com.felipecoronado.smarttasks.ui.models.ResolvedStatus
import com.felipecoronado.smarttasks.ui.models.TaskModel


val task1 = TaskModel(
    id = "1",
    targetDate = "2024-09-10",
    dueDate = "2024-09-15",
    title = "Complete Project Report",
    description = "Finish the final report for the project and submit it to the manager.",
    priority = 1,
    resolvedStatus = ResolvedStatus.CANT_RESOLVE
)

val task2 = TaskModel(
    id = "2",
    targetDate = "2024-09-12",
    dueDate = "2024-09-20",
    title = "Team Meeting",
    description = "Organize and conduct the weekly team meeting.",
    priority = 2,
    resolvedStatus = ResolvedStatus.RESOLVED
)

val task3 = TaskModel(
    id = "3",
    targetDate = "2024-09-14",
    dueDate = "2024-09-18",
    title = "Code Review",
    description = "Review the code for the new feature implementation.",
    priority = 3,
    resolvedStatus = ResolvedStatus.UNRESOLVED
)


fun task(): TaskModel {
    return TaskModel(
        id = "1",
        targetDate = "2024-09-10",
        dueDate = "2024-09-15",
        title = "Complete Project Report",
        description = "Finish the final report for the project and submit it to the manager.",
        priority = 1,
        resolvedStatus = ResolvedStatus.UNRESOLVED
    )
}
