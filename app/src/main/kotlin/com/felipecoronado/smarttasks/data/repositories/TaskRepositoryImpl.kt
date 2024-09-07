package com.felipecoronado.smarttasks.data.repositories

import com.felipecoronado.smarttasks.data.network.service.RetrofitService
import com.felipecoronado.smarttasks.domain.mappers.toTaskModel
import com.felipecoronado.smarttasks.ui.models.TaskModel
import com.felipecoronado.smarttasks.domain.repositories.ITasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val service: RetrofitService) :
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

                Result.success(responseBody.map { it.toTaskModel() })

            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
