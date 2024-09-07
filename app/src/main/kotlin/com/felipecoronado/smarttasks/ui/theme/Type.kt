package com.felipecoronado.smarttasks.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.felipecoronado.smarttasks.R


val amsiProFamily = FontFamily(
    Font(R.font.amsipro_regular, FontWeight.Normal),
    Font(R.font.amsipro_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = amsiProFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = amsiProFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

)