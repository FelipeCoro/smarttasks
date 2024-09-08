package com.felipecoronado.smarttasks.ui.screens.tasksdetails

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TaskDetailScreen(
    taskId: String,
    navigateBack: () -> Unit,
) {

    val viewModel = hiltViewModel<TaskDetailsViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler { navigateBack() }
    LaunchedEffect(key1 = Unit) {
        viewModel.getTaskDetails(taskId)
    }

    BackHandler { navigateBack() }
    Button(onClick = { navigateBack() }) {

    }
    Text(text = taskId)
}