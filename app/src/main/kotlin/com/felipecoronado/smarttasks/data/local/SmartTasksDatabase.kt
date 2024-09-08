package com.felipecoronado.smarttasks.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.felipecoronado.smarttasks.data.local.dao.TasksDao
import com.felipecoronado.smarttasks.data.local.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class SmartTasksDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}