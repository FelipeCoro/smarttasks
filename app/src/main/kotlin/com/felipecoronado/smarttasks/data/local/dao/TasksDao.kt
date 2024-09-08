package com.felipecoronado.smarttasks.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.felipecoronado.smarttasks.data.local.entities.TaskEntity

@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    @Query("SELECT * FROM tasks_list WHERE id = :id")
    suspend fun getTask(id: String): TaskEntity

    @Query("SELECT * FROM tasks_list")
    suspend fun getAllTasks(): List<TaskEntity>

    @Query("DELETE FROM tasks_list")
    suspend fun clearTaskList()
}