package com.felipecoronado.smarttasks.ui.screens.util

import com.felipecoronado.smarttasks.domain.repositories.ITasksRepository
import com.felipecoronado.smarttasks.ui.models.ResolvedStatus
import com.felipecoronado.smarttasks.ui.models.TaskModel

class RepositoryFake : ITasksRepository {


    val taskList = mutableListOf(
        task1, task2, task3
    )

    override suspend fun getAllTask(): Result<List<TaskModel>> {
        return Result.success(taskList)
    }

    override suspend fun getTaskDetails(taskId: String): Result<TaskModel> {
        return Result.success(taskList.first { it.id == taskId })
    }

    override suspend fun updatedTaskStatus(
        taskStatus: Boolean,
        taskId: String,
        userComment: String
    ): Result<TaskModel> {
        val taskIndex = taskList.indexOfFirst { it.id == taskId }
        val updatedTask = taskList[taskIndex].copy(
            resolvedStatus = if (taskStatus) ResolvedStatus.RESOLVED else ResolvedStatus.UNRESOLVED
        )
        taskList[taskIndex] = updatedTask
        return Result.success(updatedTask)
    }
}