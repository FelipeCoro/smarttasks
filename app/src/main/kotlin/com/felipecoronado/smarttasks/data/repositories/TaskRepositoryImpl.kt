package com.felipecoronado.smarttasks.data.repositories

import com.felipecoronado.smarttasks.data.local.dao.TasksDao
import com.felipecoronado.smarttasks.data.local.entities.TaskEntity
import com.felipecoronado.smarttasks.data.network.dtos.TaskList
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

                backUpTasks(response.body()!!)
                Result.success(responseBody.tasks.map { it.toTaskModel() })

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


    private suspend fun backUpTasks(taskList: TaskList) {
        withContext(ioDispatcher) {
            taskList.tasks.forEach { task ->
                val taskEntity = TaskEntity(
                    task.id,
                    task.targetDate,
                    task.dueDate ?: "",
                    task.title,
                    task.description,
                    task.priority
                )
                database.insertTask(taskEntity)
            }
        }
    }
}
