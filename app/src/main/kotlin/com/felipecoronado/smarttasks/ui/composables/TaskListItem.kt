package com.felipecoronado.smarttasks.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.felipecoronado.smarttasks.R
import com.felipecoronado.smarttasks.ui.models.ResolvedStatus
import com.felipecoronado.smarttasks.ui.models.TaskModel
import com.felipecoronado.smarttasks.ui.theme.AmsiTypography
import com.felipecoronado.smarttasks.ui.theme.BeigeMain
import com.felipecoronado.smarttasks.ui.theme.GreenMain
import com.felipecoronado.smarttasks.ui.theme.RedMain
import com.felipecoronado.smarttasks.ui.utils.formatDueDate
import com.felipecoronado.smarttasks.ui.utils.getDaysLeft

@Composable
fun TaskListItem(task: TaskModel) {

    var formattedDueDate = stringResource(id = R.string.no_due_date)
    var daysLeft = stringResource(id = R.string.no_days_left)

    if (task.dueDate.isNotEmpty()) {
        formattedDueDate = formatDueDate(task.dueDate)
        daysLeft = getDaysLeft(task.dueDate, task.targetDate)
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
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.wrapContentHeight()) {
            Text(
                text = task.title,
                color = when (task.resolvedStatus) {
                    ResolvedStatus.UNRESOLVED -> RedMain
                    ResolvedStatus.RESOLVED -> GreenMain
                    ResolvedStatus.CANT_RESOLVE -> RedMain
                },
                style = AmsiTypography.titleLarge,
                modifier = Modifier.padding(top = 14.dp, bottom = 10.dp)
                    .widthIn(max = 300.dp)
                    .wrapContentWidth(Alignment.Start)
                    .clipToBounds()

            )

            Spacer(modifier = Modifier.weight(1f))

            if (task.resolvedStatus != ResolvedStatus.UNRESOLVED) {
                Image(
                    painter = if (task.resolvedStatus == ResolvedStatus.RESOLVED)
                        painterResource(id = R.drawable.ic_btn_resolved)
                    else painterResource(id = R.drawable.ic_btn_unresolved),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp).padding(top =4.dp)
                )
            }
        }
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
                color = when (task.resolvedStatus) {
                    ResolvedStatus.UNRESOLVED -> RedMain
                    ResolvedStatus.RESOLVED -> GreenMain
                    ResolvedStatus.CANT_RESOLVE -> RedMain
                },
                style = AmsiTypography.titleLarge,

                )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = daysLeft,
                color = when (task.resolvedStatus) {
                    ResolvedStatus.UNRESOLVED -> RedMain
                    ResolvedStatus.RESOLVED -> GreenMain
                    ResolvedStatus.CANT_RESOLVE -> RedMain
                },
                style = AmsiTypography.titleLarge
            )
        }

    }
}