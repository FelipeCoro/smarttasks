package com.felipecoronado.smarttasks.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.felipecoronado.smarttasks.R
import com.felipecoronado.smarttasks.ui.theme.YellowMain
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navigateToTasksListScreen: () -> Unit) {

    LaunchedEffect(key1 = Unit) {
        delay(1000)
        navigateToTasksListScreen()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(YellowMain),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 100.dp)
                .size(200.dp),
        )
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painterResource(id = R.drawable.ic_intro_illustration),
            contentDescription = null,
            modifier = Modifier.size(200.dp),
        )
    }
}
