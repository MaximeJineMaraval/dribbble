package com.jine.dribbble.meditation

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.jine.dribbble.R

val MeditationPrimary1 = Color(0xFF72BBE4)
val MeditationPrimary2 = Color(0xFF90D8EF)
val MeditationPrimary3 = Color(0xFFCAF0F8)
val MeditationSecondary1 = Color(0xFFEC8383)
val MeditationSecondary2 = Color(0xFFF9B9B9)
val MeditationNeutral1 = Color(0xFF333333)
val MeditationNeutral2 = Color(0xFF909090)
val MeditationNeutral3 = Color(0xFFD0D0D0)
val MeditationNeutral4 = Color(0xFFFFFFFF)
val MeditationBackground = Color(0xFFFBFBFB)
val MeditationGradient = Brush.linearGradient(colors = listOf(Color(0xFF5EBDDA), Color(0xFFF9B9B9)))
val MeditationFontFamily = FontFamily(
    Font(R.font.nunito_extralight, FontWeight.ExtraLight),
    Font(R.font.nunito_light, FontWeight.Light),
    Font(R.font.nunito_medium, FontWeight.Medium),
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_semibold, FontWeight.SemiBold),
    Font(R.font.nunito_bold, FontWeight.Bold),
    Font(R.font.nunito_extrabold, FontWeight.ExtraBold),
    Font(R.font.nunito_black, FontWeight.Black)
)

@Composable
fun MeditationScreen() {
    var showMeditationListening by remember { mutableStateOf(false) }
    if (showMeditationListening) {
        MeditationListening {
            showMeditationListening = false
        }
    } else {
        MeditationHome {
            showMeditationListening = true
        }
    }
}

@Composable
fun MeditationText(
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
        fontFamily = MeditationFontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign
    )
}