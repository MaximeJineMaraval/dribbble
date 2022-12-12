package com.jine.dribbble.movie

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast
import com.jine.dribbble.tesla.GilroyFontFamily

val MovieBackground = Color.White
val MoviePrimaryColor = Color(0xFFFE6D8E)
val MovieContentPrimary = Color(0xFF12153D)
val MovieContentSecondary = Color(0xFF434670)
val MovieContentTertiary = Color(0xFF9A9BB2)
val MovieRateColor = Color(0xFFFCC419)
val MovieScoreColor = Color(0xFF51CF66)
val MovieGradient = Brush.linearGradient(colors = listOf(MoviePrimaryColor, Color.White))
val ProximaNovaFontFamily = FontFamily(
    Font(R.font.proximanova_medium, FontWeight.Medium),
    Font(R.font.proximanova_semibold, FontWeight.SemiBold),
    Font(R.font.proximanova_regular, FontWeight.Normal),
)

@Composable
fun MovieScreen() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = MovieBackground)

    var showDetail by remember { mutableStateOf(false) }
    if (showDetail) {
        MovieDetail {
            showDetail = false
        }
    } else {
        MovieList {
            showDetail = true
        }
    }
}

@Composable
fun MovieText(
    modifier: Modifier = Modifier,
    text: String,
    fontWeight: FontWeight,
    fontSize: TextUnit,
    color: Color,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        fontFamily = GilroyFontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun MovieTopBarIconButton(iconId: Int, contentDescription: String, onClick: (() -> Unit)? = null) {
    val context = LocalContext.current
    IconButton(onClick = onClick ?: { showNotImplementedToast(context) }) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.movieDp),
            tint = MovieContentPrimary
        )
    }
}


// Hack to have good screen aspect despite of complicated Figma file
@Stable
inline val Int.movieDp: Dp
    get() = (this * 0.80).dp

// Hack to have good screen aspect despite of complicated Figma file
@Stable
inline val Int.movieSp: TextUnit
    get() = (this * 0.80).sp
