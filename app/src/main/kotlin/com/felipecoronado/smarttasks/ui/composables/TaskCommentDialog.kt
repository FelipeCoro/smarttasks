package com.felipecoronado.smarttasks.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.felipecoronado.smarttasks.ui.theme.AmsiTypography
import com.felipecoronado.smarttasks.ui.theme.GreenMain
import com.felipecoronado.smarttasks.ui.theme.RedMain


@Composable
fun TaskCommentDialog(hideDialog: (Boolean) -> Unit) {

    var userInput by remember { mutableStateOf("") }

    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = {
            hideDialog(false)
        },
        title = {
            Text(
                text = "Do you want to leave a comment?",
                style = AmsiTypography.titleMedium
            )
        },
        text = {
            Column {
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    BasicTextField(
                        value = userInput,
                        onValueChange = { userInput = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textStyle = AmsiTypography.bodyLarge

                    )
                    HorizontalDivider(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        thickness = 1.dp,
                        color = Color.Black
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                hideDialog(true)
            }) {
                Text("Yes", style = AmsiTypography.titleMedium, color = GreenMain)
            }
        },
        dismissButton = {
            TextButton(onClick = {
                hideDialog(false)
            }) {
                Text("No", style = AmsiTypography.titleMedium, color = RedMain)
            }
        }
    )
}