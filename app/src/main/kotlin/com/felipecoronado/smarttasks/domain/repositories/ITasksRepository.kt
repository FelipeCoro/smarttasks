package com.felipecoronado.smarttasks.domain.repositories

import com.felipecoronado.smarttasks.ui.models.TaskModel

interface ITasksRepository {
    suspend fun getAllTask(): Result<List<TaskModel>>
    suspend fun getTaskDetails(taskId: String): Result<TaskModel>
    suspend fun updatedTaskStatus(taskStatus: Boolean, taskId:String): Result<TaskModel>


}