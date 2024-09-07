package com.felipecoronado.smarttasks.data.network.service

import com.felipecoronado.smarttasks.data.network.dtos.TaskList
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("/")
        suspend fun fetchAllTasks():Response<TaskList>
}