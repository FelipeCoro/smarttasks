package com.felipecoronado.smarttasks.domain.repositories

import com.felipecoronado.smarttasks.ui.models.TaskModel

interface ITasksRepository {
    suspend fun getAllTask():Result<List<TaskModel>>
}