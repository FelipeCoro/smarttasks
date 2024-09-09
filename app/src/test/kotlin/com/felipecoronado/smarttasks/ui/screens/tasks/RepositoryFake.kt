package com.felipecoronado.smarttasks.ui.screens.tasks

import com.felipecoronado.smarttasks.domain.repositories.ITasksRepository
import com.felipecoronado.smarttasks.ui.models.TaskModel

class RepositoryFake : ITasksRepository {


    val taskList = listOf(
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
        return Result.success(taskList.first { it.id == taskId })
    }
}