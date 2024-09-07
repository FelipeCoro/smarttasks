package com.felipecoronado.smarttasks.data.network.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskDto(
    val id: String,
    @SerialName("TargetDate")
    val targetDate: String,
    @SerialName("DueDate")
    val dueDate: String? = "",
    @SerialName("Title")
    val title: String,
    @SerialName("Description")
    val description: String,
    @SerialName("Priority")
    val priority: Int = 0
)

@Serializable
data class TaskList(
    val tasks: List<TaskDto>
)
