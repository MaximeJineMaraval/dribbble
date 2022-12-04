package com.jine.dribbble.tesla

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

@Composable
fun TeslaHome(onClick: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(TeslaGradient)
    ) {
        val context = LocalContext.current
        val (settingsButton, carName, lockSection, acStatus, carSection) = createRefs()

        TeslaTopBarButton(
            modifier = Modifier.constrainAs(settingsButton) {
                top.linkTo(parent.top, margin = 10.teslaDp)
                end.linkTo(parent.end, margin = 10.teslaDp)
            },
            iconId = R.drawable.ic_tesla_settings,
            contentDescription = "Settings"
        ) { showNotImplementedToast(context) }
        CarName(modifier = Modifier.constrainAs(carName) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(settingsButton.bottom, margin = 32.teslaDp)
        })
        LockSection(modifier = Modifier.constrainAs(lockSection) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom, margin = 32.teslaDp)
        }) {
            showNotImplementedToast(context)
        }
        ACStatus(modifier = Modifier.constrainAs(acStatus) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(lockSection.top, margin = 46.teslaDp)
        })
        CarSection(modifier = Modifier.constrainAs(carSection) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(carName.bottom)
            bottom.linkTo(acStatus.top)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }, onClick = onClick)
    }
}

@Composable
private fun CarName(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LatoText(
            text = "Tesla",
            fontWeight = FontWeight.Normal,
            fontSize = 24.teslaSp,
            color = TeslaContentGrey
        )
        LatoText(
            text = "Cybertruck",
            fontWeight = FontWeight.Black,
            fontSize = 50.teslaSp,
            color = TeslaContentWhite
        )
    }
}

@Composable
private fun LockSection(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        TeslaBlueButton(
            buttonSize = 128.teslaDp,
            iconId = R.drawable.ic_tesla_lock,
            iconSize = 32.teslaDp,
            contentDescription = "Lock",
            onClick = onClick
        )
        Spacer(modifier = Modifier.height(16.dp))
        LatoText(
            text = "Tap to open the car",
            fontWeight = FontWeight.Black,
            fontSize = 18.teslaSp,
            color = Color(0xFFE6E6E6)
        )
    }
}

@Composable
private fun ACStatus(modifier: Modifier = Modifier) {
    LatoText(
        modifier = modifier,
        text = "A/C is turned on",
        fontWeight = FontWeight.Normal,
        fontSize = 24.teslaSp,
        color = TeslaContentGrey
    )
}

@Composable
private fun CarSection(modifier: Modifier = Modifier, onClick: () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }
    val animatedScale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f,
        animationSpec = teslaAnimationSpec()
    )
    Column(
        modifier = modifier.customClick(
            onClick = onClick,
            onPress = { isPressed = true },
            onRelease = {
                isPressed = false
            })
    ) {
        Spacer(modifier = Modifier.weight(1f))
        ConstraintLayout(modifier = Modifier.scale(animatedScale)) {
            val (carImage, kmValue, kmUnit) = createRefs()
            GilroyText(
                modifier = Modifier.constrainAs(kmValue) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
                text = "297",
                fontWeight = FontWeight.ExtraLight,
                fontSize = 188.teslaSp,
                color = TeslaContentWhite
            )
            GilroyText(
                modifier = Modifier.constrainAs(kmUnit) {
                    top.linkTo(kmValue.top, margin = 30.teslaDp)
                    start.linkTo(kmValue.end, margin = 10.teslaDp)
                },
                text = "km",
                fontWeight = FontWeight.Medium,
                fontSize = 24.teslaSp,
                color = TeslaContentWhite
            )
            Image(
                painter = painterResource(id = R.drawable.tesla_cybertruck_home),
                contentDescription = "Open the car settings",
                modifier = Modifier
                    .padding(start = 46.teslaDp)
                    .fillMaxHeight(0.55f)
                    .wrapContentWidth(align = Alignment.Start, unbounded = true)
                    .constrainAs(carImage) {
                        top.linkTo(kmValue.top, margin = 109.teslaDp)
                    },
                contentScale = ContentScale.FillHeight
            )

        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

// Previews

@Preview
@Composable
private fun CarSectionPreview() {
    CarSection {}
}

@Preview
@Composable
private fun ACStatusPreview() {
    ACStatus()
}

@Preview
@Composable
private fun LockSectionPreview() {
    LockSection {}
}

@Preview
@Composable
private fun CarNamePreview() {
    CarName()
}

@Preview
@Composable
private fun TeslaHomePreview() {
    TeslaHome {}
}