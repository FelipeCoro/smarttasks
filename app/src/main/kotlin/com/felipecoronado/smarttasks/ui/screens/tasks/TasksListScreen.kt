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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

        !uiState.error.isNullOrEmpty() -> {
            NoTaskScreen(stringResource(R.string.error_loading_tasks))
        }

        else -> {
            val today = LocalDate.now()
            val todayTasks = uiState.tasks.filter { task ->
                val taskDate = LocalDate.parse(task.targetDate, DateTimeFormatter.ISO_DATE)
                taskDate.isEqual(today)
            }
            Column {
                TopNavBar()
                if (todayTasks.isEmpty()) {
                    NoTaskScreen(text = stringResource(R.string.no_tasks_today))
                } else {
                    Spacer(modifier = Modifier.height(18.dp))
                    LazyColumn {
                        items(todayTasks.size) { index ->
                            val task = todayTasks[index]
                            Box(
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = 18.dp
                                )
                            ) {
                                TaskItem(task)
                            }
                        }
                    }
                }
            }
        }
    }
}







