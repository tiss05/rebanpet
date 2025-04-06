package pt.project.rebanpet.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2E7D32),
    secondary = Color(0xFF4CAF50),
    tertiary = Color(0xFF81C784),
    surface = Color.White,
    background = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF81C784),
    secondary = Color(0xFF4CAF50),
    tertiary = Color(0xFF2E7D32),
    surface = Color.White,
    background = Color.White
)

@Composable
fun RebanpetThemee(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme.copy(
            surface = Color.White,
            background = Color.White
        ),
        content = content
    )
}