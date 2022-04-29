package com.br.titoaesj.todo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(

    primary = BlueGray900,
    onPrimary = Color.White,
    primaryVariant = BlueGray900,
    secondary = OrageYellow700,
    onSecondary = BlueGray100,
    secondaryVariant = BlueGray600,
    background = BlueGray900,
    onBackground = Color.White,
    surface = OrageYellow600,
    onSurface = Color.Black,
    error = Red,
    onError = Color.White
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    onPrimary = Color.Black,
    primaryVariant = Color.White,
    secondary = OrageYellow700,
    onSecondary = Color.Black,
    secondaryVariant = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = OrageYellow600,
    onSurface = Color.Black,
    error = Color.White,
    onError = Color.Black
)

@Composable
fun ToDoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}