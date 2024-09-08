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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.felipecoronado.smarttasks.R
import com.felipecoronado.smarttasks.ui.theme.AmsiTypography
import com.felipecoronado.smarttasks.ui.theme.YellowMain
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TopNavBar(
    currentDate: LocalDate,
    earliestDate: LocalDate,
    latestDate: LocalDate,
    onDateChanged: (LocalDate) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(YellowMain)
            .padding(top = 12.dp)
    ) {
        if (currentDate.isAfter(earliestDate)) {
            Image(
                painter = painterResource(id = R.drawable.ic_chevron_left),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        val newDate = currentDate.minusDays(1)
                        onDateChanged(newDate)
                    }
            )
        } else {
            Spacer(modifier = Modifier.size(40.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = currentDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")),
            color = Color.White,
            style = AmsiTypography.titleMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        if (currentDate.isBefore(latestDate)) {
            Image(
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        val newDate = currentDate.plusDays(1)
                        onDateChanged(newDate)
                    }
            )
        } else {
            Spacer(modifier = Modifier.size(40.dp))
        }
    }
}
