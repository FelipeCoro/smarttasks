package com.felipecoronado.smarttasks.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.felipecoronado.smarttasks.R
import com.felipecoronado.smarttasks.ui.models.TaskModel
import com.felipecoronado.smarttasks.ui.theme.AmsiTypography
import com.felipecoronado.smarttasks.ui.theme.BeigeMain
import com.felipecoronado.smarttasks.ui.theme.RedMain
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

@Composable
fun TaskItem(task: TaskModel) {

    var formattedDueDate = stringResource(id = R.string.no_due_date)
    var daysLeft = stringResource(id = R.string.no_days_left)

    if (task.dueDate.isNotEmpty()) {
        //Parse due date to "Sep-01-2024" format
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy")
        val date = LocalDate.parse(task.dueDate, inputFormatter)
        formattedDueDate = date.format(outputFormatter)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

        //Get days left in "16" format
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dueDate = LocalDate.parse(task.dueDate, formatter)
        val targetDate = LocalDate.parse(task.targetDate, formatter)
        daysLeft = ChronoUnit.DAYS.between(targetDate, dueDate).toString()
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(
                Color.White,
                RoundedCornerShape(6.dp)
            )
            .wrapContentHeight()
            .padding(horizontal = 14.dp)
    ) {
        Text(
            text = task.title,
            color = RedMain,
            style = AmsiTypography.titleLarge,
            modifier = Modifier.padding(top = 14.dp, bottom = 10.dp)
        )
        HorizontalDivider(
            thickness = 1.5.dp,
            color = BeigeMain,
            modifier = Modifier.padding(bottom = 14.dp)
        )
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            Text(
                text = stringResource(id = R.string.due_date),
                style = AmsiTypography.bodyLarge,

                )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.days_left),
                style = AmsiTypography.bodyLarge
            )

        }
        Row(modifier = Modifier.padding(bottom = 14.dp)) {
            Text(
                text = formattedDueDate,
                color = RedMain,
                style = AmsiTypography.titleLarge,

                )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = daysLeft,
                color = RedMain,
                style = AmsiTypography.titleLarge
            )
        }

    }
}