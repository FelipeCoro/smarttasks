package com.felipecoronado.smarttasks.data.mappers

import com.felipecoronado.smarttasks.data.local.entities.TaskEntity
import com.felipecoronado.smarttasks.data.network.dtos.TaskDto

fun TaskDto.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id,
        targetDate,
        dueDate ?: "",
        title,
        description,
        priority,
    )
}