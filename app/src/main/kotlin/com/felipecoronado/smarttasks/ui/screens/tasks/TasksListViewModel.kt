package com.felipecoronado.smarttasks.ui.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipecoronado.smarttasks.domain.repositories.ITasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(private val repository: ITasksRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(TasksListViewState())
    val uiState = _uiState.asStateFlow()

    fun getAllTasks() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            try {
                val result = repository.getAllTask()
                _uiState.update {
                    it.copy(
                        tasks = result.getOrThrow(),
                        loading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message, loading = false) }
            }
        }
    }
}