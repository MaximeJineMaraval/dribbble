package com.jine.dribbble.tesla

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Oval
import com.gandiva.neumorphic.shape.RoundedCorner
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeslaDetail() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val bottomSheetPeekHeight = 144.teslaDp
    // BottomSheet
    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = { TeslaAirSettings(bottomSheetScaffoldState) },
        sheetShape = RoundedCornerShape(topStart = 50.teslaDp, topEnd = 50.teslaDp),
        sheetPeekHeight = 144.teslaDp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(TeslaGradient)
        ) {
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(134.teslaDp))
                }
                item {
                    Spacer(modifier = Modifier.height(30.teslaDp))
                }
                item {
                    MainImage()
                }
                item {
                    Spacer(modifier = Modifier.height(30.teslaDp))
                }
                item {
                    StatusSection()
                }
                item {
                    Spacer(modifier = Modifier.height(56.teslaDp))
                }
                item {
                    InfoSection()
                }
                item {
                    Spacer(modifier = Modifier.height(56.teslaDp))
                }
                item {
                    NavigationSection()
                }
                item {
                    Spacer(modifier = Modifier.height(bottomSheetPeekHeight + 25.teslaDp))
                }
            }
            Header()
        }
    }
}

@Composable
private fun Header() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(134.teslaDp)
            .padding(top = 10.teslaDp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(10.teslaDp))
        TeslaTopBarButton(iconId = R.drawable.ic_tesla_menu, contentDescription = "Menu") {
            showNotImplementedToast(context)
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LatoText(
                text = "Tesla",
                fontWeight = FontWeight.Normal,
                fontSize = 18.teslaSp,
                color = TeslaContentGrey
            )
            LatoText(
                text = "Cybertruck",
                fontWeight = FontWeight.Black,
                fontSize = 18.teslaSp,
                color = TeslaContentWhite
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        TeslaTopBarButton(
            iconId = R.drawable.ic_tesla_profile,
            contentDescription = "Profile"
        ) {
            showNotImplementedToast(context)
        }
        Spacer(modifier = Modifier.width(10.teslaDp))
    }
}

@Composable
private fun MainImage() {
    Image(
        painter = painterResource(id = R.drawable.tesla_cybertruck_detail),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
private fun StatusSection() {
    Column {
        SectionTitle(title = "Status")
        Spacer(modifier = Modifier.height(24.teslaDp))
        Row {
            Spacer(modifier = Modifier.width(40.teslaDp))
            StatusItem(iconId = R.drawable.ic_tesla_battery, title = "Battery", value = "54%")
            Spacer(modifier = Modifier.width(40.teslaDp))
            StatusItem(iconId = R.drawable.ic_tesla_range, title = "Range", value = "297 km")
            Spacer(modifier = Modifier.width(40.teslaDp))
            StatusItem(
                iconId = R.drawable.ic_tesla_temperture,
                title = "Temperture",
                value = "27°C"
            )
        }
    }
}

@Composable
private fun StatusItem(iconId: Int, title: String, value: String) {
    Row {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier
                .size(height = 18.teslaDp, width = 18.teslaDp)
                .padding(top = 1.teslaDp),
            tint = TeslaContentGrey
        )
        Spacer(modifier = Modifier.width(2.teslaDp))
        Column {
            LatoText(
                text = title,
                fontWeight = FontWeight.Normal,
                fontSize = 18.teslaSp,
                color = TeslaContentGrey
            )
            Spacer(modifier = Modifier.height(8.teslaDp))
            LatoText(
                text = value,
                fontWeight = FontWeight.Black,
                fontSize = 18.teslaSp,
                color = TeslaContentWhite
            )
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    LatoText(
        modifier = Modifier.padding(start = 40.teslaDp),
        text = title,
        fontWeight = FontWeight.Black,
        fontSize = 24.teslaSp,
        color = TeslaContentWhite
    )
}

enum class InfoItemStyle {
    Circle {

        @Composable
        override fun Shape(modifier: Modifier) {
            Box(
                modifier = modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF131517),
                                Color(0xFF1E2226)
                            )
                        ),
                        shape = CircleShape
                    )
                    .size(157.teslaDp)
            )
        }
    },
    Rectangle {
        @Composable
        override fun Shape(modifier: Modifier) {
            Box(
                modifier = modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF131517),
                                Color(0xFF1E2226)
                            )
                        ),
                        shape = RoundedCornerShape(4.teslaDp)
                    )
                    .size(width = 106.teslaDp, height = 186.teslaDp)
            )
        }
    },
    Triangle {
        @Composable
        override fun Shape(modifier: Modifier) {
            Canvas(
                modifier = modifier
                    .width(135.teslaDp)
                    .height(125.teslaDp)
            ) {
                val rect = Rect(Offset.Zero, size)
                val trianglePath = Path().apply {
                    moveTo(rect.topLeft.x, rect.topLeft.y)
                    lineTo(rect.topRight.x, rect.topRight.y)
                    lineTo(rect.bottomCenter.x, rect.bottomCenter.y)
                    close()
                }
                drawIntoCanvas { canvas ->
                    canvas.drawOutline(
                        outline = Outline.Generic(trianglePath),
                        paint = Paint().apply {
                            color = Color.Black
                            pathEffect = PathEffect.cornerPathEffect(rect.maxDimension / 18)
                            shader = LinearGradientShader(
                                rect.topCenter,
                                rect.bottomCenter,
                                colors = listOf(Color(0xFF131517), Color(0xFF1E2226))
                            )
                        }
                    )
                }
            }
        }
    };

    @Composable
    abstract fun Shape(modifier: Modifier)
}

@Composable
private fun InfoSection() {
    val context = LocalContext.current
    Column {
        SectionTitle(title = "Information")
        Spacer(modifier = Modifier.height(24.teslaDp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 40.teslaDp),
            horizontalArrangement = Arrangement.spacedBy(32.teslaDp)
        ) {
            items(
                items = listOf(
                    Pair(Pair("Engine", "Sleeping mode"), Pair(InfoItemStyle.Circle, false)),
                    Pair(Pair("Climate", "A/C is ON"), Pair(InfoItemStyle.Rectangle, true)),
                    Pair(Pair("Tires", "Low pressure"), Pair(InfoItemStyle.Triangle, true))
                )
            ) {
                InfoItem(
                    title = it.first.first,
                    info = it.first.second,
                    backgroundStyle = it.second.first,
                    showLed = it.second.second
                ) {
                    showNotImplementedToast(context)
                }
            }
        }
    }
}

@Composable
private fun InfoItem(
    title: String,
    info: String,
    backgroundStyle: InfoItemStyle,
    showLed: Boolean,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val elevation by animateDpAsState(
        if (isPressed) 10.teslaDp else 15.teslaDp,
        animationSpec = teslaAnimationSpec()
    )
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = teslaAnimationSpec()
    )

    Card(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier
            .width(150.teslaDp)
            .height(160.teslaDp)
            .neu(
                lightShadowColor = Color(0xCC262E32),
                darkShadowColor = Color(0xFF101012),
                shadowElevation = elevation,
                shape = Flat(RoundedCorner(16.teslaDp))
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1F2328),
                        Color(0xFF1A1C1F)
                    )
                ),
                shape = RoundedCornerShape(8.teslaDp)
            )
            .scale(scale)
            .customClick(
                onClick = onClick,
                onPress = { isPressed = true },
                onRelease = { isPressed = false }),
    ) {
        ConstraintLayout {
            val (labels, shape1, shape2, blueLed) = createRefs()

            // Background shapes
            when (backgroundStyle) {
                InfoItemStyle.Circle -> {
                    backgroundStyle.Shape(modifier = Modifier.constrainAs(shape1) {
                        top.linkTo(parent.top, margin = 18.teslaDp)
                        end.linkTo(parent.end, margin = 21.teslaDp)
                    })
                    backgroundStyle.Shape(modifier = Modifier.constrainAs(shape2) {
                        top.linkTo(parent.top, margin = 55.teslaDp)
                        start.linkTo(parent.start, margin = 66.teslaDp)
                    })
                }
                InfoItemStyle.Rectangle -> {
                    backgroundStyle.Shape(modifier = Modifier
                        .rotate(-30f)
                        .constrainAs(shape1) {
                            top.linkTo(parent.top, margin = 31.teslaDp)
                            end.linkTo(parent.end, margin = 63.teslaDp)
                        })
                    backgroundStyle.Shape(modifier = Modifier
                        .rotate(-30f)
                        .constrainAs(shape2) {
                            top.linkTo(parent.top, margin = 52.teslaDp)
                            start.linkTo(parent.start, margin = 72.teslaDp)
                        })
                }
                InfoItemStyle.Triangle -> {
                    backgroundStyle.Shape(modifier = Modifier
                        .constrainAs(shape1) {
                            top.linkTo(parent.top, margin = 20.teslaDp)
                            start.linkTo(parent.start, margin = 32.teslaDp)
                        })
                    backgroundStyle.Shape(modifier = Modifier
                        .constrainAs(shape2) {
                            top.linkTo(parent.top, margin = 80.teslaDp)
                            start.linkTo(parent.start, margin = 12.teslaDp)
                        })
                }
            }
            // Labels
            Column(
                modifier = Modifier
                    .constrainAs(labels) {
                        start.linkTo(parent.start, margin = 16.teslaDp)
                        end.linkTo(parent.end, margin = 16.teslaDp)
                        bottom.linkTo(parent.bottom, margin = 16.teslaDp)
                        width = Dimension.fillToConstraints
                    }
            ) {
                LatoText(
                    text = title,
                    fontWeight = FontWeight.Black,
                    fontSize = 18.teslaSp,
                    color = TeslaContentWhite
                )
                Spacer(modifier = Modifier.height(4.teslaDp))
                LatoText(
                    text = info,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.teslaSp,
                    color = TeslaContentGrey
                )
            }
            // Blue Led
            if (showLed) {
                BlueDot(modifier = Modifier.constrainAs(blueLed) {
                    top.linkTo(parent.top, margin = 16.teslaDp)
                    start.linkTo(parent.start, margin = 16.teslaDp)
                })
            }
        }
    }
}

@Composable
private fun BlueDot(modifier: Modifier = Modifier) {
    val darkDotSize = 16
    Box(modifier = modifier.scale(0.5f), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF1B2125),
                            Color.Black
                        ),
                        radius = darkDotSize * 2f
                    ), shape = CircleShape
                )
                .size(darkDotSize.teslaDp)
        )
        Box(
            modifier = Modifier
                .neu(
                    lightShadowColor = Color(0x220A8DDD),
                    darkShadowColor = Color(0x220A8DDD),
                    shadowElevation = (darkDotSize / 4).teslaDp,
                    shape = Flat(Oval),
                    lightSource = LightSource.LEFT_TOP
                )
                .neu(
                    lightShadowColor = Color(0x220A8DDD),
                    darkShadowColor = Color(0x220A8DDD),
                    shadowElevation = (darkDotSize / 4).teslaDp,
                    shape = Flat(Oval),
                    lightSource = LightSource.RIGHT_TOP
                )
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF0172BE),
                            Color(0xFF0F9BEE)
                        )
                    ), shape = CircleShape
                )
                .size((darkDotSize / 2).teslaDp)
        )
    }
}

@Composable
private fun NavigationSection() {
    val context = LocalContext.current
    Row(verticalAlignment = Alignment.CenterVertically) {
        SectionTitle(title = "Navigation")
        Spacer(modifier = Modifier.weight(1f))
        LatoText(
            modifier = Modifier.clickable { showNotImplementedToast(context) },
            text = "History",
            fontWeight = FontWeight.Normal,
            fontSize = 18.teslaSp,
            color = TeslaContentWhite
        )
        Spacer(modifier = Modifier.width(40.teslaDp))
    }
}

// Preview

@Preview
@Composable
private fun NavigationSectionPreview() {
    NavigationSection()
}

@Preview
@Composable
private fun BlueDotPreview() {
    BlueDot()
}

@Preview
@Composable
private fun InfoSectionPreview() {
    InfoSection()
}

@Preview
@Composable
private fun InfoTriangleItemPreview() {
    InfoItem("Engine", "Sleeping mode", InfoItemStyle.Triangle, false) {}
}

@Preview
@Composable
private fun InfoCircleItemPreview() {
    InfoItem("Engine", "Sleeping mode", InfoItemStyle.Circle, true) {}
}

@Preview
@Composable
private fun InfoSquareItemPreview() {
    InfoItem("Engine", "Sleeping mode", InfoItemStyle.Rectangle, true) {}
}

@Preview
@Composable
private fun StatusItemPreview() {
    StatusItem(iconId = R.drawable.ic_tesla_battery, title = "Battery", value = "54%")
}

@Preview
@Composable
private fun SectionTitlePreview() {
    SectionTitle("Section")
}

@Preview
@Composable
private fun StatusSectionPreview() {
    StatusSection()
}

@Preview
@Composable
private fun HeaderPreview() {
    Header()
}

@Preview
@Composable
private fun TeslaDetailPreview() {
    TeslaDetail()
}