package com.jine.dribbble.skeuomorphism

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

//region Screen

@Composable
fun SkeuomorphismScreen4(onBackClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SkeuomorphismBackgroundColor)
    ) {
        SkeuomorphismToolbar(onBackClicked = onBackClicked)
        SkeuomorphismTitle(title = "Fast and powerful, \nlike your work")
        Spacer(modifier = Modifier.height(14.dp))
        SkeuomorphismText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = "Packed with design features you \n" +
                    "already love plus unique",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = SkeuomorphismContentSecondaryColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(38.dp))
        Gauges()
        Spacer(modifier = Modifier.height(38.dp))
        val context = LocalContext.current
        SkeuomorphismButton(
            modifier = Modifier.padding(horizontal = 24.dp),
            label = "Explore features"
        ) {
            showNotImplementedToast(context)
        }
    }
}

//endregion

//region Components

@Composable
private fun Gauges() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Gauge(value = 0.26f, iconId = R.drawable.ic_skeuomorphism_gauge1)
        Spacer(modifier = Modifier.width(30.dp))
        Gauge(value = 0.72f, iconId = R.drawable.ic_skeuomorphism_gauge2)
        Spacer(modifier = Modifier.width(30.dp))
        Gauge(value = 0.54f, iconId = R.drawable.ic_skeuomorphism_gauge3)
        Spacer(modifier = Modifier.weight(1f))
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun Gauge(value: Float, iconId: Int) {
    val animatedHeight = remember { Animatable(0f) }
    LaunchedEffect(key1 = animatedHeight, block = {
        animatedHeight.animateTo(value, tween(500, easing = EaseInOut))
    })
    val roundedCornerSize = 28.dp
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Container
        Box(
            modifier = Modifier
                .width(56.dp)
                .height(300.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFE1EDF8),
                            Color(0xFFEEF1F3)
                        )
                    ),
                    shape = RoundedCornerShape(roundedCornerSize)
                )
                .border(
                    width = 3.dp, brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFCFDBE7),
                            Color(0xFFE3EDF7)
                        )
                    ), shape = RoundedCornerShape(roundedCornerSize)
                )
                .neu(
                    darkShadowColor = Color(0xFFC8D4E2),
                    lightShadowColor = Color(0xFFFFFFFF).copy(0.75f),
                    shape = Pressed(RoundedCorner(roundedCornerSize)),
                    shadowElevation = 16.dp
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            // Content
            Box(
                modifier = Modifier
                    .padding(bottom = 13.dp)
                    .width(32.dp)
                    .height((280 * animatedHeight.value).dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFCD8A9A).copy(alpha = 0.8f),
                                Color(0xFFFEAAA0).copy(alpha = 0.8f)
                            )
                        ),
                        shape = RoundedCornerShape(23.dp)
                    )
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

//endregion

//region Preview

@Preview
@Composable
private fun GaugePreview() {
    Gauge(value = 0.4f, iconId = R.drawable.ic_skeuomorphism_gauge1)
}

@Preview
@Composable
private fun GaugesPreview() {
    Gauges()
}

@Preview
@Composable
private fun SkeuomorphismScreen4Preview() {
    SkeuomorphismScreen4 {}
}

//endregion