package com.felipecoronado.smarttasks.ui.screens.tasksdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TaskDetailScreen(
    taskId: String,
    navigateBack: () -> Unit,
) {
    
    Text(text = taskId)
}