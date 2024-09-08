package com.felipecoronado.smarttasks.domain.mappers

import com.felipecoronado.smarttasks.data.local.entities.TaskEntity
import com.felipecoronado.smarttasks.data.network.dtos.TaskDto
import com.felipecoronado.smarttasks.ui.models.TaskModel


fun TaskDto.toTaskModel(): TaskModel {
    return TaskModel(
        id,
        targetDate,
        dueDate ?: "",
        title,
        description,
        priority
    )
}


fun TaskEntity.toTaskModel(): TaskModel {
    return TaskModel(
        id,
        targetDate,
        dueDate,
        title,
        description,
        priority
    )
}

