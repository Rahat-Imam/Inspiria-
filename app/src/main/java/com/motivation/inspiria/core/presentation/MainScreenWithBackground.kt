package com.motivation.inspiria.core.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.motivation.inspiria.ui.theme.BackgroundColor


@Composable
fun MainScreenWithBackground(
    modifier: Modifier = Modifier,
    addScaffolding: Boolean = false,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        CompositionLocalProvider(LocalAbsoluteTonalElevation provides 0.dp) {
            if (!addScaffolding)
                content(PaddingValues.Absolute())
            else {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.background,
                    topBar = topBar,
                    bottomBar = bottomBar
                )
                {
                    content(it)
                }
            }
        }
    }
}

