package com.felipecoronado.smarttasks.ui.screens.tasksdetails

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.felipecoronado.smarttasks.R
import com.felipecoronado.smarttasks.ui.composables.LoadingScreen
import com.felipecoronado.smarttasks.ui.composables.NoTaskScreen
import com.felipecoronado.smarttasks.ui.composables.TaskCommentDialog
import com.felipecoronado.smarttasks.ui.composables.TaskDetailsItem
import com.felipecoronado.smarttasks.ui.composables.TasksDetailsTopNavBar
import com.felipecoronado.smarttasks.ui.theme.YellowMain

@Composable
fun TaskDetailScreen(
    taskId: String,
    navigateBack: () -> Unit,
) {

    val viewModel = hiltViewModel<TaskDetailsViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var showDialog by remember { mutableStateOf(false) }

    var resolvedStatus = false

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
                TaskDetailsItem(uiState.tasks) { status ->
                    resolvedStatus = status
                    showDialog = true

                }
            }
        }
    }

    if (showDialog) {
        Column(
            Modifier
                .background(YellowMain)
                .fillMaxSize()) {
            TaskCommentDialog { leaveComment ->
                showDialog = false
                viewModel.updateTaskStatus(resolvedStatus, taskId, leaveComment)
            }
        }

    }
}


