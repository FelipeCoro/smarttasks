package com.felipecoronado.smarttasks.data.repositories

import com.felipecoronado.smarttasks.data.local.dao.TasksDao
import com.felipecoronado.smarttasks.data.local.entities.EntityResolvedStatus
import com.felipecoronado.smarttasks.data.local.entities.TaskEntity
import com.felipecoronado.smarttasks.data.network.dtos.TaskDto
import com.felipecoronado.smarttasks.data.network.service.RetrofitService
import com.felipecoronado.smarttasks.domain.mappers.toTaskModel
import com.felipecoronado.smarttasks.domain.repositories.ITasksRepository
import com.felipecoronado.smarttasks.ui.models.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val service: RetrofitService,
    private val database: TasksDao
) :
    ITasksRepository {
    private val ioDispatcher = Dispatchers.IO
    override suspend fun getAllTask(): Result<List<TaskModel>> {
        return withContext(ioDispatcher) {
            try {
                val response = service.fetchAllTasks()

                if (!response.isSuccessful) {
                    throw Exception("Response is not successful: ${response.code()}")
                }
                val responseBody = response.body() ?: throw Exception("Response body is null")

                val localTasks = database.getAllTasks()

                val localTaskMap = localTasks.associateBy { it.id }

                val finalTasks = responseBody.tasks.map { remoteTask ->
                    localTaskMap[remoteTask.id] ?: remoteTask.toTaskEntity()
                }

                backUpTasks(finalTasks)
                Result.success(finalTasks.map { it.toTaskModel() })

            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun getTaskDetails(taskId: String): Result<TaskModel> {
        return withContext(ioDispatcher) {
            try {
                if (database.getAllTasks().isEmpty()) {
                    getAllTask()
                }
                val task = database.getTask(taskId)
                Result.success(task.toTaskModel())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun updatedTaskStatus(taskStatus: Boolean, taskId: String): Result<TaskModel> {
        return withContext(ioDispatcher) {
            try {
                val task = database.getTask(taskId)
                val updatedTask =
                    TaskEntity(
                        task.id,
                        task.targetDate,
                        task.dueDate,
                        task.title,
                        task.description,
                        task.priority,
                        resolvedStatus = if (taskStatus) EntityResolvedStatus.RESOLVED
                        else EntityResolvedStatus.CANT_RESOLVE
                    )
                database.insertTask(updatedTask)
                Result.success(updatedTask.toTaskModel())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }


    private fun TaskDto.toTaskEntity(): TaskEntity {
        return TaskEntity(
            id,
            targetDate,
            dueDate ?: "",
            title,
            description,
            priority,
        )
    }

    private suspend fun backUpTasks(taskList: List<TaskEntity>) {
        withContext(ioDispatcher) {
            taskList.forEach { task ->
                database.insertTask(task)
            }
        }
    }
}
