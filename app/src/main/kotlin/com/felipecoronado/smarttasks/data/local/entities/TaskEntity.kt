package com.felipecoronado.smarttasks.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_list")
data class TaskEntity(
    @PrimaryKey
    val id: String,
    val targetDate: String,
    val dueDate: String,
    val title: String,
    val description: String,
    val priority: Int
)
