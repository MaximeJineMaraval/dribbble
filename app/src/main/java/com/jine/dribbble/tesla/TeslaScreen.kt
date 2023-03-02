package com.jine.dribbble.tesla

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
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
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Oval
import com.gandiva.neumorphic.shape.Pressed
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R
import com.jine.dribbble.customClick

val TeslaBackgroundTop = Color(0xFF353A40)
val TeslaBackgroundBottom = Color(0xFF16171B)
val TeslaGradient =
    Brush.verticalGradient(colors = listOf(TeslaBackgroundTop, TeslaBackgroundBottom))
val TeslaContentGrey = Color(0xFF7F8489)
val TeslaContentWhite = Color(0xFFFDFDFD)
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
val LatoFontFamily = FontFamily(
    Font(R.font.lato_black, FontWeight.Black),
    Font(R.font.lato_bold, FontWeight.Bold),
    Font(R.font.lato_light, FontWeight.Light),
    Font(R.font.lato_regular, FontWeight.Normal),
    Font(R.font.lato_thin, FontWeight.Thin),
)


@Composable
fun TeslaScreen() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = TeslaBackgroundTop)
    systemUiController.setNavigationBarColor(color = TeslaBackgroundBottom)

    var showDetail by remember { mutableStateOf(false) }
    if (showDetail) {
        TeslaDetail()
    } else {
        TeslaHome(onClick = {
            showDetail = true
        })
    }
}

fun <T> teslaAnimationSpec() = tween<T>(150, easing = LinearEasing)

@Composable
fun GilroyText(
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
fun LatoText(
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
        fontFamily = LatoFontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun TeslaTopBarButton(
    modifier: Modifier = Modifier,
    iconId: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    val lightColor = Color(0xFF2F353A)
    val darkColor = Color(0xFF1C1F22)

    var isPressed by remember { mutableStateOf(false) }

    // Animation
    val buttonLightColor by animateColorAsState(
        targetValue = if (isPressed) darkColor else lightColor,
        animationSpec = teslaAnimationSpec()
    )
    val buttonDarkColor by animateColorAsState(
        targetValue = if (isPressed) lightColor else darkColor,
        animationSpec = teslaAnimationSpec()
    )

    // Global container
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        // Border around the button
        Box(
            modifier = Modifier
                .size(124.teslaDp)
                .blur(1.teslaDp, edgeTreatment = BlurredEdgeTreatment.Unbounded),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(64.teslaDp)
                    .neu(
                        lightShadowColor = Color(0xFF485057),
                        darkShadowColor = Color(0xFF1F2427),
                        shape = Flat(Oval),
                        shadowElevation = 20.teslaDp
                    )
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF1F2427),
                                Color(0xFF2C3036)
                            )
                        )
                    )
                    .neu(
                        lightShadowColor = Color(0xFF2C3036),
                        darkShadowColor = Color(0xFF31343C),
                        shape = Flat(Oval),
                        shadowElevation = 20.teslaDp
                    )
            )
        }
        // Round button
        Box(
            modifier = Modifier
                .size(58.teslaDp)
                .clip(CircleShape)
                .customClick(
                    onClick = onClick,
                    onPress = { isPressed = true },
                    onRelease = { isPressed = false })
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF2F353A),
                            Color(0xFF1C1F22)
                        )
                    )
                )
                .neu(
                    lightShadowColor = buttonLightColor,
                    darkShadowColor = buttonDarkColor,
                    shape = Flat(Oval),
                    shadowElevation = 20.teslaDp
                ), contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = contentDescription,
                modifier = Modifier.size(24.teslaDp),
                tint = TeslaContentGrey
            )
        }
    }
}

@Composable
fun TeslaGreyButton(
    modifier: Modifier = Modifier,
    iconId: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    val lightColor = Color(0xFF2F353A)
    val darkColor = Color(0xFF1C1F22)

    var isPressed by remember { mutableStateOf(false) }

    // Animation
    val buttonLightColor by animateColorAsState(
        targetValue = if (isPressed) darkColor else lightColor,
        animationSpec = teslaAnimationSpec()
    )
    val buttonDarkColor by animateColorAsState(
        targetValue = if (isPressed) lightColor else darkColor,
        animationSpec = teslaAnimationSpec()
    )

    // Global container
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        // Border around the button
        Box(
            modifier = Modifier
                .size(80.teslaDp)
                .neu(
                    lightShadowColor = Color(0xFF262E32),
                    darkShadowColor = Color(0xFF101012),
                    shape = Flat(Oval),
                    shadowElevation = 20.teslaDp
                )
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF1D2328),
                            Color(0xFF131314)
                        )
                    )
                )
                .neu(
                    lightShadowColor = Color(0xFF1D2328),
                    darkShadowColor = Color(0xFF131314),
                    shape = Flat(Oval),
                    shadowElevation = 20.teslaDp
                )
        )
        // Round button
        Box(
            modifier = Modifier
                .size(72.teslaDp)
                .clip(CircleShape)
                .customClick(
                    onClick = onClick,
                    onPress = { isPressed = true },
                    onRelease = { isPressed = false })
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF2F353A),
                            Color(0xFF1C1F22)
                        )
                    )
                )
                .neu(
                    lightShadowColor = buttonLightColor,
                    darkShadowColor = buttonDarkColor,
                    shape = Flat(Oval),
                    shadowElevation = 20.teslaDp
                ), contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = contentDescription,
                modifier = Modifier.size(24.teslaDp),
                tint = TeslaContentGrey
            )
        }
    }
}

@Composable
fun TeslaBlueButton(
    modifier: Modifier = Modifier,
    buttonSize: Dp,
    iconId: Int,
    iconSize: Dp,
    contentDescription: String,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val buttonOutsideElevation: Dp by animateDpAsState(
        if (isPressed) buttonSize.times(0.16f) else buttonSize.times(0.23f),
        animationSpec = teslaAnimationSpec()
    )
    val buttonInsideElevation: Dp by animateDpAsState(
        if (isPressed) buttonSize.times(0.12f) else buttonSize.times(0.2f),
        animationSpec = teslaAnimationSpec()
    )
    val buttonScale by animateFloatAsState(
        targetValue = if (isPressed) 0.99f else 1f,
        animationSpec = teslaAnimationSpec()
    )
    Box(
        modifier = modifier
            .neu(
                lightShadowColor = Color(0xFF2F393D),
                darkShadowColor = Color(0xFF000000),
                shape = Flat(Oval),
                shadowElevation = buttonOutsideElevation
            )
            .scale(buttonScale)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(buttonSize)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF11A8FD),
                            Color(0xFF0081C9)
                        )
                    ),
                    shape = CircleShape
                )
                .padding(3.teslaDp)
                .neu(
                    darkShadowColor = Color(0xAA005EA3),
                    lightShadowColor = Color(0xFF11A8FD),
                    shape = Pressed(Oval),
                    shadowElevation = buttonInsideElevation
                )
                .customClick(onClick = onClick, onPress = {
                    isPressed = true
                }, onRelease = { isPressed = false })
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = contentDescription,
                modifier = Modifier.size(iconSize),
                tint = TeslaContentWhite
            )
        }
    }
}

// Hack to have good screen aspect despite of complicated Figma file
@Stable
inline val Int.teslaDp: Dp
    get() = (this * 0.80).dp

// Hack to have good screen aspect despite of complicated Figma file
@Stable
inline val Int.teslaSp: TextUnit
    get() = (this * 0.80).sp

// Preview

@Preview
@Composable
private fun TeslaTopBarButtonPreview() {
    TeslaTopBarButton(iconId = R.drawable.ic_tesla_program, contentDescription = "Program") {}
}

@Preview
@Composable
private fun TeslaGreyButtonPreview() {
    TeslaGreyButton(iconId = R.drawable.ic_tesla_program, contentDescription = "Program") {}
}

@Preview
@Composable
private fun TeslaBlueButtonPreview() {
    TeslaBlueButton(
        buttonSize = 80.teslaDp,
        iconSize = 24.teslaDp,
        iconId = R.drawable.ic_tesla_program,
        contentDescription = "Program"
    ) {}
}