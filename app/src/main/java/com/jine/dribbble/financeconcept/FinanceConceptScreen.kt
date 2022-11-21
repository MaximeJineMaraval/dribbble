package com.jine.dribbble.financeconcept

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R

val FinanceConceptWhite = Color(0xFFFFFFFF)
val FinanceConceptAlmostWhite = Color(0xFFF8F8F8)
val FinanceConceptBlack = Color(0xFF333333)
val FinanceConceptGrey = Color(0xFF989898)
val FinanceConceptPink = Color(0xFFF96EDA)
val FinanceConceptPurple = Color(0xFF8C61E9)
val FinanceConceptGreen = Color(0xFF188975)
val FinanceConceptRed = Color(0xFFCB60A0)
val FinanceConceptGradiant =
    Brush.linearGradient(colors = listOf(FinanceConceptPink, FinanceConceptPurple))
val FinanceConceptFontFamily = FontFamily(
    Font(R.font.manrope_bold, weight = FontWeight.Bold),
    Font(R.font.manrope_extrabold, weight = FontWeight.ExtraBold),
    Font(R.font.manrope_extralight, weight = FontWeight.ExtraLight),
    Font(R.font.manrope_medium, weight = FontWeight.Medium),
    Font(R.font.manrope_regular, weight = FontWeight.Normal),
    Font(R.font.manrope_semibold, weight = FontWeight.SemiBold)
)

@Composable
fun FinanceConceptScreen() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = FinanceConceptAlmostWhite)
    var showDetail by remember { mutableStateOf(false) }
    if (showDetail) {
        FinanceConceptDetail()
    } else {
        FinanceConceptHomePage {
            showDetail = true
        }
    }
}
