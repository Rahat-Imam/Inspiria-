package com.motivation.inspiria.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = AppMainColorDark,
    onPrimary = Color(0xFF000000),
    onPrimaryContainer = Color(0xFFFFFFFF),

    secondary = BackgroundColorDark,

    onSecondary = cardColorDark,
    onSecondaryContainer = textColorDark,

    tertiary = BackgroundColorDark,

    onTertiary = bottomAppBarColorDark,
    background = BackgroundColorDark,
    onBackground = Color.LightGray
)

private val LightColorScheme = lightColorScheme(
    primary = AppMainColor,
    onPrimary = Color(0xFFFFFFFF),
    onPrimaryContainer = Color(0xFF000000),

    secondary = Color(0xFFFFFFFF),

    onSecondary = cardColorLight,
    onSecondaryContainer = textColorLight,

    tertiary = Color(0xFFFFFFFF),

    onTertiary = bottomAppBarColorLight,
//    background = BackgroundColor,
    background = Color(0xFFFFFFFF),
    onBackground = Color.DarkGray

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


@Composable
fun InspiriaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !darkTheme
    val statusBarColor = colorScheme.onPrimary

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = statusBarColor,
            darkIcons = useDarkIcons
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}