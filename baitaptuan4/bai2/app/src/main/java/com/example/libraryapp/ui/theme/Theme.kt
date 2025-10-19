package com.example.libraryapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Purple80,
    secondary = Pink80,
    tertiary = PurpleGrey80
)

private val DarkColors = darkColorScheme(
    primary = Purple40,
    secondary = Pink40,
    tertiary = PurpleGrey40
)

@Composable
fun LibraryAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
