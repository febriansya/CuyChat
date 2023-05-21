package com.example.cuychat.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cuychat.R

// Set of Material typography styles to start with

val fontGilroy = FontFamily(
    Font(R.font.gilory_extra_bold, weight = FontWeight.ExtraBold),
    Font(R.font.gilory_light, weight = FontWeight.Light)
)

val TextTypografy = Typography(
    titleMedium = TextStyle(
        fontFamily = fontGilroy,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 28.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = fontGilroy,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 22.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = fontGilroy,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),

    bodySmall = TextStyle(
        fontFamily = fontGilroy,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
    ),
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
)





