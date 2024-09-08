package com.felipecoronado.smarttasks.ui.screens.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.felipecoronado.smarttasks.R
import com.felipecoronado.smarttasks.ui.composables.LoadingScreen
import com.felipecoronado.smarttasks.ui.composables.NoTaskScreen
import com.felipecoronado.smarttasks.ui.composables.TaskItem
import com.felipecoronado.smarttasks.ui.composables.TopNavBar

@Composable()
fun TasksListScreen() {

    val viewModel: TasksListViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        viewModel.getAllTasks()
    }

    when {
        uiState.loading -> {
            LoadingScreen()
        }

        uiState.tasks.isEmpty() -> {
            NoTaskScreen(stringResource(R.string.no_tasks_today))
        }

        !uiState.error.isNullOrEmpty() -> {
            NoTaskScreen(stringResource(R.string.error_loading_tasks))
        }

        else -> {
            Column {
                TopNavBar()
                Spacer(modifier = Modifier.height(18.dp))
                LazyColumn {
                    items(uiState.tasks.size) { index ->
                        val task = uiState.tasks[index]

                        Box(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 18.dp)
                        ) {
                            TaskItem(task)
                        }
                    }
                }
            }
        }
    }
}







