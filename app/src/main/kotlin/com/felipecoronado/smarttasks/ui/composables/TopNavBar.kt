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
import com.felipecoronado.smarttasks.ui.theme.AmsiTypography
import com.felipecoronado.smarttasks.ui.theme.YellowMain
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TopNavBar(onDateChanged: (LocalDate) -> Unit) {
    var currentDate by remember { mutableStateOf(LocalDate.now()) }

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
                    currentDate = currentDate.minusDays(1)
                    onDateChanged(currentDate)
                }
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = if (currentDate == LocalDate.now()) {
                stringResource(id = R.string.today)
            } else {

                currentDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
                    .replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }
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
                    currentDate = currentDate.plusDays(1)
                    onDateChanged(currentDate)
                }
        )
    }
}
