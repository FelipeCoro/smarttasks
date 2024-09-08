package com.felipecoronado.smarttasks.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felipecoronado.smarttasks.R
import com.felipecoronado.smarttasks.ui.models.TaskModel
import com.felipecoronado.smarttasks.ui.theme.AmsiTypography
import com.felipecoronado.smarttasks.ui.theme.BeigeMain
import com.felipecoronado.smarttasks.ui.theme.RedMain
import com.felipecoronado.smarttasks.ui.theme.YellowMain
import com.felipecoronado.smarttasks.ui.utils.formatDueDate
import com.felipecoronado.smarttasks.ui.utils.getDaysLeft

@Preview
@Composable
fun TaskDetailsItem() {
    val task = TaskModel(
        "",
        "2024-08-25",
        "2024-09-25",
        "Task title",
        "Setup Jenkins environment fSetup Jenkins environment for SomSetup Jenkins environment for SomSetup Jenkins environment for SomSetup Jenkins environment for SomSetup Jenkins environment for SomSetup Jenkins environment for SomSetup Jenkins environment for Somor SomeCoolApp. Feel free to ask Jeff for help (jeffthemighty@example.com)",
        5
    )

    var formattedDueDate = stringResource(id = R.string.no_due_date)
    var daysLeft = stringResource(id = R.string.no_days_left)

    if (task.dueDate.isNotEmpty()) {
        formattedDueDate = formatDueDate(task.dueDate)
        daysLeft = getDaysLeft(task.dueDate, task.targetDate)
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(YellowMain)
            .fillMaxSize()
            .padding(start = 14.dp, end = 14.dp, top = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painterResource(id = R.drawable.ic_task_details),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .width(400.dp)
                    .height(405.dp),
                contentScale = ContentScale.FillBounds
            )


            HorizontalDivider(
                thickness = 1.5.dp,
                color = BeigeMain,
                modifier = Modifier
                    .padding(start = 14.dp, end = 14.dp, bottom = 60.dp)
                    .align(Alignment.BottomCenter)
            )
            Text(
                text = "Task resolved state",
                color = YellowMain,
                style = AmsiTypography.titleMedium,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 14.dp, bottom = 20.dp)
            )

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(start = 14.dp, end = 14.dp, top = 50.dp)
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
                        style = AmsiTypography.titleMedium,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = daysLeft,
                        color = RedMain,
                        style = AmsiTypography.titleMedium
                    )
                }
                HorizontalDivider(
                    thickness = 1.5.dp,
                    color = BeigeMain,
                    modifier = Modifier.padding(bottom = 14.dp)
                )
                Text(
                    text = if (task.description.length > 164) task.description.take(164) + "..." else task.description,
                    style = AmsiTypography.bodyLarge
                )

            }
        }
    }
}