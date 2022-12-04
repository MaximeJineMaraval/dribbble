package com.jine.dribbble.tesla

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Oval
import com.github.krottv.compose.sliders.ListenOnPressed
import com.github.krottv.compose.sliders.SliderValueHorizontal
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeslaAirSettings(bottomSheetScaffoldState: BottomSheetScaffoldState) {
    Box(
        Modifier
            .fillMaxWidth(
            )
            .background(
                Color(0xFF424750),
                shape = RoundedCornerShape(topStart = 50.teslaDp, 50.teslaDp)
            )
            .padding(top = 3.teslaDp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    TeslaGradient,
                    shape = RoundedCornerShape(topStart = 50.teslaDp, 50.teslaDp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(isCollapsed = bottomSheetScaffoldState.bottomSheetState.isCollapsed)
            Spacer(modifier = Modifier.height(56.teslaDp))
            TemperatureSection()
            Spacer(modifier = Modifier.height(56.teslaDp))
            SliderSection()
            ModeSection()
        }
    }
}

@Composable
private fun Header(isCollapsed: Boolean) {
    val context = LocalContext.current
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (scrollLine, title, subtitle, button) = createRefs()

        // ScrollLine
        Box(
            modifier = Modifier
                .size(width = 60.teslaDp, 4.teslaDp)
                .background(color = Color(0xFF17181C), shape = RoundedCornerShape(4.teslaDp))
                .constrainAs(scrollLine) {
                    top.linkTo(parent.top, margin = 16.teslaDp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        // Title
        LatoText(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(scrollLine.bottom, margin = 10.teslaDp)
                start.linkTo(parent.start, margin = 40.teslaDp)
            },
            text = "A/C is ON",
            fontWeight = FontWeight.Black,
            fontSize = 24.teslaSp,
            color = TeslaContentWhite
        )

        // Subtitle
        LatoText(
            modifier = Modifier.constrainAs(subtitle) {
                top.linkTo(title.bottom, margin = 8.teslaDp)
                start.linkTo(title.start)
                end.linkTo(button.start, margin = 120.teslaDp)
                width = Dimension.fillToConstraints
            },
            text = if (isCollapsed) "Tap to turn off or swipe up for fast setup" else "Currently 27°C\nWill change in 2 min",
            fontWeight = FontWeight.Normal,
            fontSize = 18.teslaSp,
            color = TeslaContentGrey
        )

        TeslaBlueButton(
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 32.teslaDp)
                bottom.linkTo(parent.bottom, margin = 32.teslaDp)
                end.linkTo(parent.end, margin = 40.teslaDp)
            },
            buttonSize = 80.teslaDp,
            iconId = R.drawable.ic_tesla_on_off,
            iconSize = 24.teslaDp,
            contentDescription = "Switch air power"
        ) {
            showNotImplementedToast(context)
        }
    }
}

@Composable
private fun ModeSection() {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        LatoText(
            text = "Mode",
            fontWeight = FontWeight.Black,
            fontSize = 24.teslaSp,
            color = TeslaContentWhite
        )
        Spacer(modifier = Modifier.height(24.teslaDp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LatoText(
                    text = "Auto",
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.teslaSp,
                    color = TeslaContentGrey
                )
                Spacer(modifier = Modifier.height(16.teslaDp))
                TeslaBlueButton(
                    buttonSize = 80.teslaDp,
                    iconId = R.drawable.ic_tesla_auto,
                    iconSize = 24.teslaDp,
                    contentDescription = "Auto"
                ) {
                    showNotImplementedToast(context)
                }
            }
            Spacer(modifier = Modifier.weight(0.5f))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LatoText(
                    text = "Dry",
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.teslaSp,
                    color = TeslaContentGrey
                )
                Spacer(modifier = Modifier.height(16.teslaDp))
                TeslaGreyButton(
                    iconId = R.drawable.ic_tesla_dry,
                    contentDescription = "Dry"
                ) {
                    showNotImplementedToast(context)
                }
            }
            Spacer(modifier = Modifier.weight(0.5f))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LatoText(
                    text = "Cool",
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.teslaSp,
                    color = TeslaContentGrey
                )
                Spacer(modifier = Modifier.height(16.teslaDp))
                TeslaGreyButton(
                    iconId = R.drawable.ic_tesla_cool,
                    contentDescription = "Cool"
                ) {
                    showNotImplementedToast(context)
                }
            }
            Spacer(modifier = Modifier.weight(0.5f))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LatoText(
                    text = "Program",
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.teslaSp,
                    color = TeslaContentGrey
                )
                Spacer(modifier = Modifier.height(16.teslaDp))
                TeslaGreyButton(
                    iconId = R.drawable.ic_tesla_program,
                    contentDescription = "Program"
                ) {
                    showNotImplementedToast(context)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(32.teslaDp))
    }
}

@Composable
private fun SliderSection() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        LatoText(
            text = "Fan speed",
            fontWeight = FontWeight.Black,
            fontSize = 24.teslaSp,
            color = TeslaContentWhite
        )
        Spacer(modifier = Modifier.height(26.teslaDp))
        Slider()
        Spacer(modifier = Modifier.height(56.teslaDp))
    }
}

@Composable
private fun Slider() {
    var sliderState by remember { mutableStateOf(2.5f) }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.teslaDp)
    ) {
        Row(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(8.teslaDp))
            LatoText(
                text = "1",
                fontWeight = FontWeight.Normal,
                fontSize = 14.teslaSp,
                color = TeslaContentGrey
            )
            Spacer(modifier = Modifier.weight(1f))
            LatoText(
                text = "2",
                fontWeight = FontWeight.Normal,
                fontSize = 14.teslaSp,
                color = TeslaContentGrey
            )
            Spacer(modifier = Modifier.weight(1f))
            LatoText(
                text = "3",
                fontWeight = FontWeight.Normal,
                fontSize = 14.teslaSp,
                color = TeslaContentGrey
            )
            Spacer(modifier = Modifier.weight(1f))
            LatoText(
                text = "4",
                fontWeight = FontWeight.Normal,
                fontSize = 14.teslaSp,
                color = TeslaContentGrey
            )
            Spacer(modifier = Modifier.weight(1f))
            LatoText(
                text = "5",
                fontWeight = FontWeight.Normal,
                fontSize = 14.teslaSp,
                color = TeslaContentGrey
            )
            Spacer(modifier = Modifier.width(8.teslaDp))
        }
        SliderValueHorizontal(
            modifier = Modifier.fillMaxWidth(),
            value = sliderState,
            onValueChange = { sliderState = it },
            valueRange = 0f..5f,
            steps = 3,
            thumb = { modifier, _, interactionSource, _, _ ->
                var isPressed by remember { mutableStateOf(false) }
                interactionSource.ListenOnPressed { isPressed = it }

                val scale by animateFloatAsState(
                    if (isPressed) 0.95f else 1f,
                    teslaAnimationSpec()
                )
                Box(
                    modifier = modifier
                        .size(28.teslaDp)
                        .background(color = Color(0xFF282B2E), shape = CircleShape)
                        .scale(scale)
                        .shadow(
                            elevation = 4.teslaDp,
                            shape = CircleShape,
                            spotColor = Color.Black
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(23.teslaDp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF141515),
                                        Color(0xFF2E3236)
                                    )
                                ), shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(4.teslaDp)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xFF0172BE),
                                            Color(0xFF0F9BEE)
                                        )
                                    ), shape = CircleShape
                                )
                        )
                    }
                }
            },
            track = { modifier: Modifier, fraction: Float, interactionSource: MutableInteractionSource, tickFractions: List<Float>, enabled: Boolean ->
                Box(modifier = modifier) {
                    Box(
                        modifier = Modifier
                            .height(9.teslaDp)
                            .fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0.75f),
                                        Color(0xFF202226),
                                        Color(0xFF4E5154)
                                    )
                                ),
                                shape = RoundedCornerShape(9.teslaDp)
                            )
                    )
                    Box(
                        modifier = Modifier
                            .height(7.teslaDp)
                            .fillMaxWidth(fraction)
                            .padding(top = 1.teslaDp, start = 2.teslaDp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF016BB8),
                                        Color(0xFF0782DB),
                                        Color(0xFF0F9CEB),
                                        Color(0xFF11A8FD)
                                    )
                                ), shape = RoundedCornerShape(6.teslaDp)
                            )
                    )
                }
            }
        )
    }
}

@Composable
private fun TemperatureSection() {

    val temperatures = listOf(
        "Low",
        "17°C",
        "18°C",
        "19°C",
        "20°C",
        "21°C",
        "22°C",
        "23°C",
        "24°C",
        "25°C",
        "26°C",
        "27°C",
        "High"
    )
    val startAngle = 135.0
    var angle by remember { mutableStateOf(90 + startAngle) }
    Box(modifier = Modifier.size(376.teslaDp), contentAlignment = Alignment.Center) {
        CircularSlider(startAngle = startAngle, onValueChange = { newAngle ->
            Log.d("ANGLE", "$newAngle")
            angle = newAngle
        })
        // Labels
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GilroyText(
                text = temperatures.getOrElse(
                    (angle / ((360 + 90) / (temperatures.size + 1))).toInt() - 2
                ) { "broken $it" },
                fontWeight = FontWeight.Black,
                fontSize = 40.teslaSp,
                color = TeslaContentWhite
            )
            GilroyText(
                text = "Cooling...",
                fontWeight = FontWeight.Medium,
                fontSize = 18.teslaSp,
                color = TeslaContentWhite
            )
        }
        // Lines
        Row(Modifier.fillMaxWidth()) {
            CircularSliderLed(isTurnedOn = angle >= 90 + 90)
            Spacer(modifier = Modifier.weight(1f))
            CircularSliderLed(isTurnedOn = angle >= 270 + 90)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .rotate(90f)
        ) {
            CircularSliderLed(isTurnedOn = angle >= 180 + 90)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .rotate(45f)
        ) {
            CircularSliderLed(isTurnedOn = angle >= 135 + 90)
            Spacer(modifier = Modifier.weight(1f))
            CircularSliderLed(isTurnedOn = angle >= 315 + 90)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .rotate(-45f)
        ) {
            CircularSliderLed(isTurnedOn = angle >= 45 + 90)
            Spacer(modifier = Modifier.weight(1f))
            CircularSliderLed(isTurnedOn = angle >= 225 + 90)
        }
    }
}

@Composable
private fun CircularSliderLed(isTurnedOn: Boolean) {
    if (isTurnedOn) {
        Spacer(
            modifier = Modifier
                .size(height = 3.teslaDp, width = 15.teslaDp)
                .background(
                    Color(0xFF0C95E8),
                    shape = RoundedCornerShape(3.teslaDp)
                )
                .shadow(12.dp, spotColor = Color(0xFF016CBC))
        )
    } else {
        Box {
            Spacer(
                modifier = Modifier
                    .size(height = 3.teslaDp, width = 15.teslaDp)
                    .background(
                        Color(0xFF535960),
                        shape = RoundedCornerShape(3.teslaDp)
                    )
            )
            Spacer(
                modifier = Modifier
                    .size(height = 3.teslaDp, width = 15.teslaDp)
                    .padding(top = 1.teslaDp)
                    .background(
                        Color(0xFF15171C),
                        shape = RoundedCornerShape(3.teslaDp)
                    )
            )
        }
    }
}

// todo add blue leds around the circle
@Composable
private fun CircularSlider(startAngle: Double, onValueChange: (angle: Double) -> Unit) {
    fun getRotationAngle(currentPosition: Offset, center: Offset): Double {
        val (dx, dy) = currentPosition - center
        val theta = atan2(dy, dx).toDouble()
        var angle = Math.toDegrees(theta)
        if (angle < 90) {
            angle += 360.0
        }
        return angle
    }

    val circleWidth = with(LocalDensity.current) { 54.teslaDp.toPx() }
    val littleThumbRadius = with(LocalDensity.current) { 4.teslaDp.toPx() } / 2
    val bigThumbRadius = with(LocalDensity.current) { 49.teslaDp.toPx() } / 2
    val bigThumbStrokeRadius = with(LocalDensity.current) { 50.teslaDp.toPx() } / 2
    val bigThumbShadowRadius = with(LocalDensity.current) { 58.teslaDp.toPx() } / 2

    var shapeCenter = Offset.Zero
    var handleCenter by remember {
        mutableStateOf(Offset.Zero)
    }
    var angle by remember {
        mutableStateOf(startAngle)
    }
    var isInit = true
    Box(
        modifier = Modifier
            .size(323.teslaDp)
            .neu(
                lightShadowColor = Color(0xFF485057),
                darkShadowColor = Color(0xFF141415),
                shadowElevation = 20.teslaDp,
                shape = Flat(Oval)
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF202428),
                        Color(0xFF131314)
                    )
                ),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier
                .size(300.teslaDp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        isInit = false
                        handleCenter += dragAmount

                        angle = getRotationAngle(handleCenter, shapeCenter)
                        onValueChange(angle)
                        change.consume()
                    }
                }
                .padding(30.dp)
        ) {
            shapeCenter = center
            val radius = size.minDimension / 2
            val x: Float
            val y: Float
            if (isInit) {
                x = (shapeCenter.x + cos(Math.toRadians(angle + 90)) * radius).toFloat()
                y = (shapeCenter.y + sin(Math.toRadians(angle + 90)) * radius).toFloat()
            } else {
                x = (shapeCenter.x + cos(Math.toRadians(angle)) * radius).toFloat()
                y = (shapeCenter.y + sin(Math.toRadians(angle)) * radius).toFloat()
            }
            handleCenter = Offset(x, y)

            // Circle not selected
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFAABBDE).copy(alpha = 0.55f), Color(0xFF1F2124)),
                    center = this.center,
                    radius = radius * 0.85f,
                ),
                style = Stroke(circleWidth),
                radius = radius
            )

            // Circle selected
            drawArc(
                brush = Brush.sweepGradient(
                    colors = listOf(Color(0xFF005696), Color(0xFF11A8FD), Color(0xFF005696)),
                    center = this.center
                ),
                startAngle = 90f,
                sweepAngle = if (isInit) angle.toFloat() else angle.toFloat() - 90f,
                useCenter = false,
                style = Stroke(circleWidth, cap = StrokeCap.Round)
            )

            // Thumb Shadow
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color.Black,
                        Color.Transparent,
                    ),
                    center = handleCenter,
                    radius = bigThumbShadowRadius
                ),
                center = handleCenter,
                radius = bigThumbShadowRadius
            )
            // Thumb Stroke
            drawCircle(
                color = Color(0xFF1B1D1E),
                center = handleCenter,
                radius = bigThumbStrokeRadius
            )
            // Thumb
            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF141515),
                        Color(0xFF2E3236)
                    )
                ),
                center = handleCenter,
                radius = bigThumbRadius
            )
            // Thumb Blue Dot
            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0172BE),
                        Color(0xFF0F9BEE)
                    )
                ),
                center = handleCenter,
                radius = littleThumbRadius
            )
        }
    }
}

// Previews

@Preview
@Composable
private fun TemperatureSectionPreview() {
    TemperatureSection()
}

@Preview
@Composable
private fun CircularSliderPreview() {
    CircularSlider(50.0) {}
}

@Preview
@Composable
private fun SliderSectionPreview() {
    SliderSection()
}

@Preview
@Composable
private fun SliderPreview() {
    Slider()
}

@Preview
@Composable
private fun ModeSectionPreview() {
    ModeSection()
}

@Preview
@Composable
private fun HeaderPreview() {
    Header(true)
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
private fun TeslaAirSettingsPreview() {
    TeslaAirSettings(rememberBottomSheetScaffoldState())
}