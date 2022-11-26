package com.sederikkuapplication.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorPalette = lightColorScheme(
    primary = Red800,
    onPrimary = Gray800,
    primaryContainer = Gray100,
    onPrimaryContainer = Red700,
    inversePrimary = Red700,
    secondary = Red900,
    onSecondary = Gray700,
    secondaryContainer = Gray300,
    onSecondaryContainer = Gray200,
    background = Gray300,
    onBackground = Red600,
    surface = Red50,
    onSurface = Red900,
    inverseSurface = Red900,
    inverseOnSurface = Gray100,
    surfaceVariant = Gray900,
    onSurfaceVariant = Gray800,
    outline = Red200
)

private val DarkColorPalette = darkColorScheme(
    primary = Red800,
    onPrimary = Red300,
    primaryContainer = Gray800,
    onPrimaryContainer = Gray100,
    inversePrimary = Red700,
    secondary = Gray100,
    onSecondary = Gray700,
    secondaryContainer = Gray300,
    onSecondaryContainer = Gray200,
    background = Gray900,
    onBackground = Gray300,
    surface = Red50,
    onSurface = Red200,
    inverseSurface = Red500,
    inverseOnSurface = Gray100,
    surfaceVariant = Gray900,
    onSurfaceVariant = Gray800,
    outline = Red200
)

@Composable
fun CyberTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val useDynamicColors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
        useDynamicColors && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        useDynamicColors && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun MainTheme(
    darkTheme: Boolean = true, //isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val useDynamicColors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
        useDynamicColors && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        useDynamicColors && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}