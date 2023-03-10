package com.jine.dribbble.foodee

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R

//region Assets

val FoodeeGradient =
    Brush.linearGradient(colors = listOf(Color(0xFFFFFFF2), Color(0xFFF28482)))

val FoodeeBackgroundPrimaryColor = Color(0xFFFFFFFF)
val FoodeeBackgroundSecondaryColor = Color(0xFFFFFFF2)
val FoodeeBackgroundTertiaryColor = Color(0xFFF7EDE2)
val FoodeeAccentPrimaryColor = Color(0xFFF28482)
val FoodeeAccentSecondaryColor = Color(0xFF84A59D)
val FoodeeContentPrimaryColor = Color(0xFF3D405B)
val FoodeeContentSecondaryColor = FoodeeContentPrimaryColor.copy(alpha = 0.4f)
val FoodeeContentTertiaryColor = Color(0xFFF4F1DE)
val FoodeeFontFamily = FontFamily(
    Font(R.font.unbounded_light, FontWeight.Light),
    Font(R.font.unbounded_regular, FontWeight.Normal),
    Font(R.font.unbounded_semibold, FontWeight.SemiBold),
    Font(R.font.unbounded_bold, FontWeight.Bold)
)

fun <T> foodeeClickAnimationSpec() = tween<T>(150, easing = LinearEasing)

//endregion

//region Screen

@Composable
fun FoodeeScreen() {
    var index by remember {
        mutableStateOf(0)
    }
    when (index) {
        0 -> FoodeeHomePage(onProductClicked = { index = 1 }, onCartClicked = { index = 2 })
        1 -> FoodeeProductPage(onCloseClicked = { index = 0 }, onProductAddedToCart = { index = 2 })
        2 -> {} //todo FoodeeCartPage
    }
}

//endregion

//region Components

@Composable
fun FoodeeText(
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
        fontFamily = FoodeeFontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign
    )
}

//endregion

//region Preview

//endregion