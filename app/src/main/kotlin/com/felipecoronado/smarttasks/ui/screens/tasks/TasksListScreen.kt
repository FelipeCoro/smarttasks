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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.felipecoronado.smarttasks.ui.utils.filterTasksByDate
import kotlinx.coroutines.delay
import java.time.LocalDate

@Composable()
fun TasksListScreen() {

    val viewModel: TasksListViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var currentDate by remember { mutableStateOf(LocalDate.now()) }

    LaunchedEffect(Unit) {
        delay(1000)
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
            var filteredTasks = filterTasksByDate(uiState.tasks, currentDate)

            val earliestDate =
                uiState.tasks.minOfOrNull { LocalDate.parse(it.targetDate) } ?: LocalDate.now()

            val latestDate =
                uiState.tasks.maxOfOrNull { LocalDate.parse(it.targetDate) } ?: LocalDate.now()

            Column {
                TopNavBar(
                    currentDate = currentDate,
                    earliestDate = earliestDate,
                    latestDate = latestDate,
                    tasks = uiState.tasks,
                    onDateChanged = { date ->
                        currentDate = date
                        filteredTasks = filterTasksByDate(uiState.tasks, date)
                    })
                if (filteredTasks.isEmpty()) {
                    NoTaskScreen(text = stringResource(R.string.no_tasks_today))
                } else {
                    Spacer(modifier = Modifier.height(18.dp))
                    LazyColumn {
                        val sortedTasks = filteredTasks.sortedByDescending { it.priority }
                        items(sortedTasks.size) { index ->
                            val task = filteredTasks[index]
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








