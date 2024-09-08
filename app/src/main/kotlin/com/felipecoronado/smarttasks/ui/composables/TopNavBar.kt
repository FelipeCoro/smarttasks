package com.felipecoronado.smarttasks.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.felipecoronado.smarttasks.R
import com.felipecoronado.smarttasks.ui.screens.tasks.TaskCategory
import com.felipecoronado.smarttasks.ui.theme.AmsiTypography
import com.felipecoronado.smarttasks.ui.theme.YellowMain


@Composable
fun TopNavBar(onCategorySelected: (TaskCategory) -> Unit) {
    var selectedCategory by remember { mutableStateOf(TaskCategory.TODAY) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(YellowMain)
            .padding(top = 12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_chevron_left),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    selectedCategory = when (selectedCategory) {
                        TaskCategory.TODAY -> TaskCategory.PAST
                        TaskCategory.UPCOMING -> TaskCategory.TODAY
                        else -> TaskCategory.PAST
                    }
                    onCategorySelected(selectedCategory)
                }
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = when (selectedCategory) {
                TaskCategory.PAST -> stringResource(R.string.past)
                TaskCategory.TODAY -> stringResource(R.string.today)
                TaskCategory.UPCOMING -> stringResource(R.string.upcoming)
            },
            color = Color.White,
            style = AmsiTypography.titleMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_chevron_right),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    selectedCategory = when (selectedCategory) {
                        TaskCategory.PAST -> TaskCategory.TODAY
                        TaskCategory.TODAY -> TaskCategory.UPCOMING
                        else -> TaskCategory.UPCOMING
                    }
                    onCategorySelected(selectedCategory)
                }
        )
    }
}
