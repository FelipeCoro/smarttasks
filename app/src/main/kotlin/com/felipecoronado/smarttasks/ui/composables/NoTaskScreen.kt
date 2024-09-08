package com.felipecoronado.smarttasks.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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


@Composable
fun NoTaskScreen(text:String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(YellowMain),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopNavBar()

        Image(
            painterResource(id = R.drawable.ic_empty_screen),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 138.dp)
                .size(280.dp)
        )

        Spacer(modifier = Modifier.height(42.dp))

        Text(
            text = text,
            color = Color.White,
            style = AmsiTypography.titleLarge
        )
    }
}

