package com.felipecoronado.smarttasks.data.di

import android.content.Context
import androidx.room.Room
import com.felipecoronado.smarttasks.data.local.SmartTasksDatabase
import com.felipecoronado.smarttasks.data.local.dao.TasksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        SmartTasksDatabase::class.java,
        "database"
    ).build()

    @Singleton
    @Provides
    fun provideRecognizedUsersDao(dataBase: SmartTasksDatabase): TasksDao =
        dataBase.tasksDao()

}