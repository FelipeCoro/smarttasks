package com.felipecoronado.smarttasks.data.network.service

import com.felipecoronado.smarttasks.data.network.dtos.TaskDto
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET()
    fun fetchAllTasks():Response<List<TaskDto>>
}