package com.felipecoronado.smarttasks.ui.screens.tasks

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.felipecoronado.smarttasks.ui.composables.LoadingScreen
import com.felipecoronado.smarttasks.ui.models.TaskModel


@Composable()
fun TasksListScreen() {

    val viewModel: TasksListViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        viewModel.getAllTasks()
    }

    if (uiState.loading) {
        LoadingScreen()
    } else {
        LazyColumn {
            items(uiState.tasks.size) { index ->
                val task = uiState.tasks[index]
                TaskItem(task)
            }
        }
    }
}

@Composable
fun TaskItem(task: TaskModel) {
    // Define how each task item should be displayed
    Text(text = task.id)
    Text(text = task.dueDate)
    Text(text = task.targetDate)
}




