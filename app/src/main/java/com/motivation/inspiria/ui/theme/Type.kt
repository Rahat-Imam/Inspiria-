package com.motivation.inspiria.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.motivation.inspiria.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */


)


val PoppinsFontBold = FontFamily(
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val PoppinsFontLight = FontFamily(
    Font(R.font.poppins_light, FontWeight.Light)
)

val PoppinsFontRegular = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal)
)

val PoppinsFontMedium = FontFamily(
    Font(R.font.poppins_medium, FontWeight.Medium)
)

val PoppinsFontSemiBold = FontFamily(
    Font(R.font.poppins_semi_bold,FontWeight.SemiBold)
)