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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.felipecoronado.smarttasks.R
import com.felipecoronado.smarttasks.ui.theme.AmsiTypography
import com.felipecoronado.smarttasks.ui.theme.GreenMain
import com.felipecoronado.smarttasks.ui.theme.RedMain


@Composable
fun TaskCommentDialog(hideDialog: (Boolean, String) -> Unit) {

    var userInput by remember { mutableStateOf("") }
    var showHint by remember { mutableStateOf(false) }

    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = {
            hideDialog(false, userInput)
        },
        title = {
            Text(
                text = stringResource(id = R.string.leave_comment),
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
                        onValueChange = {
                            userInput = it
                            showHint = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textStyle = AmsiTypography.bodyLarge,
                        decorationBox = { innerTextField ->
                            if (userInput.isEmpty() && showHint) {
                                Text(
                                    text = stringResource(id = R.string.no_empty_comment),
                                    style = AmsiTypography.bodyLarge.copy(color = Color.Gray)
                                )
                            }
                            innerTextField()
                        }
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
                if (userInput.isEmpty()) {
                    showHint = true
                } else {
                    hideDialog(true, userInput)
                }
            }) {
                Text("Yes", style = AmsiTypography.titleMedium, color = GreenMain)
            }
        },
        dismissButton = {
            TextButton(onClick = {
                hideDialog(false, userInput)
            }) {
                Text("No", style = AmsiTypography.titleMedium, color = RedMain)
            }
        }
    )
}
