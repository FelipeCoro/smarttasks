package com.felipecoronado.smarttasks.domain.di

import com.felipecoronado.smarttasks.data.repositories.TaskRepositoryImpl
import com.felipecoronado.smarttasks.domain.repositories.ITasksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsNetworkRepository(networkRepository: TaskRepositoryImpl)
            : ITasksRepository
}