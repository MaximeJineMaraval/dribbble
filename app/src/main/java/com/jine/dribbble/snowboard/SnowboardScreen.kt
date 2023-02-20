package com.jine.dribbble.snowboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

val SnowboardBackground = Color(0xFFF8F9FE)
val SnowboardPrimaryColor = Color.Black
val SnowboardContentPrimary = Color.Black
val SnowboardContentSecondary = Color(0xFFBBBFDC).copy(0.7f)
val SnowboardLikeColor = Color(0xFFFF3D00)
val SnowboardGradient =
    Brush.linearGradient(colors = listOf(SnowboardContentSecondary, Color.White))
val GilroyFontFamily = FontFamily(
    Font(R.font.gilroy_black, FontWeight.Black),
    Font(R.font.gilroy_bold, FontWeight.Bold),
    Font(R.font.gilroy_extrabold, FontWeight.ExtraBold),
    Font(R.font.gilroy_light, FontWeight.Light),
    Font(R.font.gilroy_medium, FontWeight.Medium),
    Font(R.font.gilroy_regular, FontWeight.Normal),
    Font(R.font.gilroy_semibold, FontWeight.SemiBold),
    Font(R.font.gilroy_thin, FontWeight.Thin),
    Font(R.font.gilroy_ultralight, FontWeight.ExtraLight)
)

// Hack to have good screen aspect despite of complicated Figma file
@Stable
inline val Int.snowboardDp: Dp
    get() = (this * 1.00).dp

// Hack to have good screen aspect despite of complicated Figma file
@Stable
inline val Int.snowboardSp: TextUnit
    get() = (this * 1.00).sp

@Composable
fun SnowboardScreen() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = SnowboardBackground)

    var index by remember {
        mutableStateOf(0)
    }
    when (index) {
        0 -> SnowboardStartingPage(onViewCollectionClicked = { index = 1 })
        1 -> SnowboardHomePage(onCollectionOpened = { index = 2 })
        2 -> SnowboardProductPage(onBackButtonClicked = { index = 1 })
    }
}

@Composable
fun SnowboardText(
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
fun SnowboardToolbar(onBackClicked: (() -> Unit)? = null) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.snowboardDp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(24.snowboardDp))
        IconButton(onClick = onBackClicked ?: { showNotImplementedToast(context) }) {
            if (onBackClicked != null) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_snowboard_back),
                    contentDescription = "Back"
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_snowboard_menu),
                    contentDescription = "Menu"
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { showNotImplementedToast(context) }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_snowboard_like_line),
                contentDescription = "Show liked products"
            )
        }
        SnowboardText(
            text = "17",
            fontWeight = FontWeight.Bold,
            fontSize = 16.snowboardSp,
            color = SnowboardContentPrimary
        )
        IconButton(onClick = { showNotImplementedToast(context) }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_snowboard_cart),
                contentDescription = "Show cart"
            )
        }
        SnowboardText(
            text = "3",
            fontWeight = FontWeight.Bold,
            fontSize = 16.snowboardSp,
            color = SnowboardContentPrimary
        )
        Spacer(modifier = Modifier.width(36.snowboardDp))
    }
}


// Preview

@Preview
@Composable
private fun SnowboardToolbarPreview() {
    SnowboardToolbar()
}