package com.felipecoronado.smarttasks.ui.screens.tasksdetails

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.felipecoronado.smarttasks.R
import com.felipecoronado.smarttasks.ui.composables.LoadingScreen
import com.felipecoronado.smarttasks.ui.composables.NoTaskScreen
import com.felipecoronado.smarttasks.ui.composables.TaskDetailsItem
import com.felipecoronado.smarttasks.ui.composables.TasksDetailsTopNavBar

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
    when {
        uiState.loading -> {
            LoadingScreen()
        }

        !uiState.error.isNullOrEmpty() -> {
            NoTaskScreen(stringResource(R.string.error_loading_task))
        }

        else -> {
            Column {
                TasksDetailsTopNavBar { navigateBack() }
                TaskDetailsItem(uiState.tasks) { resolvedStatus ->
                    viewModel.updateTaskStatus(resolvedStatus, taskId)
                }
            }
        }
    }
}

