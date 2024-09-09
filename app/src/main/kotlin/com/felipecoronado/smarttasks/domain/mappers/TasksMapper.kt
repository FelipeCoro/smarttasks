package com.felipecoronado.smarttasks.domain.mappers

import com.felipecoronado.smarttasks.data.local.entities.EntityResolvedStatus
import com.felipecoronado.smarttasks.data.local.entities.TaskEntity
import com.felipecoronado.smarttasks.ui.models.ResolvedStatus
import com.felipecoronado.smarttasks.ui.models.TaskModel


fun TaskEntity.toTaskModel(): TaskModel {
    return TaskModel(
        id,
        targetDate,
        dueDate,
        title,
        description,
        priority,
        when (resolvedStatus) {
            EntityResolvedStatus.RESOLVED -> ResolvedStatus.RESOLVED
            EntityResolvedStatus.UNRESOLVED -> ResolvedStatus.UNRESOLVED
            EntityResolvedStatus.CANT_RESOLVE -> ResolvedStatus.CANT_RESOLVE
        }
    )
}

