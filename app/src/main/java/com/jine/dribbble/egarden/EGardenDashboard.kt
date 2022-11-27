package com.jine.dribbble.egarden

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast
import kotlin.math.absoluteValue

// TODO: exporter les SVGS plutôt que les PNGs
@Composable
fun EGardenDashboard(modifier: Modifier) {
    ConstraintLayout(
        modifier = modifier.background(EGardenWhite)
    ) {
        val (title, plusButton, displayButtons, plantsPager, waterSection, lightSection) = createRefs()

        EGardenTitle(
            title = "My Gardens",
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start, margin = 32.dp)
            })
        PlusButton(modifier = Modifier.constrainAs(plusButton) {
            top.linkTo(title.top)
            bottom.linkTo(title.bottom)
            end.linkTo(parent.end, margin = 32.dp)
        })
        DisplayButtons(modifier = Modifier.constrainAs(displayButtons) {
            top.linkTo(title.bottom, margin = 24.dp)
            start.linkTo(parent.start, margin = 32.dp)
        })
        PlantsPager(modifier = Modifier.constrainAs(plantsPager) {
            top.linkTo(displayButtons.bottom, margin = 36.dp)
            bottom.linkTo(waterSection.top, margin = 32.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            height = Dimension.fillToConstraints
            width = Dimension.fillToConstraints
        })
        WaterSection(modifier = Modifier.constrainAs(waterSection) {
            bottom.linkTo(parent.bottom, margin = 50.dp)
            end.linkTo(lightSection.start)
            start.linkTo(parent.start, margin = 0.dp)
        })
        LightSection(modifier = Modifier.constrainAs(lightSection) {
            bottom.linkTo(parent.bottom, margin = 50.dp)
            start.linkTo(waterSection.end)
            end.linkTo(parent.end, margin = 0.dp)
        })
    }
}

@Composable
private fun PlusButton(modifier: Modifier) {
    val context = LocalContext.current
    IconButton(
        onClick = { showNotImplementedToast(context) },
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_egarden_plus_circle),
            tint = EGardenGreen,
            contentDescription = "Add a plant"
        )
    }
}

@Composable
private fun DisplayButtons(modifier: Modifier) {
    val context = LocalContext.current
    Row(modifier) {
        IconButton(onClick = { showNotImplementedToast(context) }) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.egarden_grid),
                tint = EGardenGreyLight,
                contentDescription = "Show as a grid"
            )
        }
        IconButton(onClick = { showNotImplementedToast(context) }) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.egarden_card),
                tint = EGardenBlack,
                contentDescription = "Show as a card"
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun PlantsPager(modifier: Modifier = Modifier) {
    HorizontalPager(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 76.dp),
        count = 3,
    ) { page ->
        val textColor: Color
        val gradientColor: Brush
        val bottomColor: Color
        when (page) {
            0 -> {
                textColor = Color(0xFF646C74)
                gradientColor = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFD5DEE8),
                        Color(0xFFB2C0CF)
                    )
                )
                bottomColor = Color(0xFFD5DEE8)
            }
            1 -> {
                textColor = Color(0xFF4A794D)
                gradientColor = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFBEE4C0),
                        Color(0xFF94D097)
                    )
                )
                bottomColor = Color(0xFFB2DEB4)
            }
            else -> {
                textColor = Color(0xFF776B59)
                gradientColor = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFF5E8D4),
                        Color(0xFFE3CCA8)
                    )
                )
                bottomColor = Color(0xFFF5E8D4)
            }
        }
        PlantCard(
            modifier = Modifier.graphicsLayer {
                // Calculate the absolute offset for the current page from the
                // scroll position. We use the absolute value which allows us to mirror
                // any effects for both directions
                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                // We animate the scaleX + scaleY, between 85% and 100%
                lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }
            },
            textColor = textColor,
            gradientColor = gradientColor,
            bottomColor = bottomColor
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PlantCard(
    modifier: Modifier,
    textColor: Color,
    gradientColor: Brush,
    bottomColor: Color
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .fillMaxHeight()
            .width(232.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 16.dp,
        onClick = { showNotImplementedToast(context) }
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradientColor)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.26f)
                    .background(bottomColor)
            )
        }
        Column(
            modifier = Modifier.padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "AILANTHUS",
                color = textColor,
                fontFamily = EGardenNunitoFontFamily,
                fontWeight = FontWeight.Black,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "10 DAYS",
                color = EGardenWhite,
                fontFamily = EGardenNunitoFontFamily,
                fontWeight = FontWeight.Black,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(19.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.egarden_plant),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun Section(
    modifier: Modifier,
    title: String,
    value: String,
    info: String,
    iconId: Int,
    progressStartAngle: Float,
    progressSweepAngle: Float,
    progressColor: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = EGardenBlack,
            fontFamily = EGardenNunitoFontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box {
            Canvas(modifier = Modifier.size(68.dp)) {
                drawCircle(
                    style = Stroke(4.dp.toPx()),
                    color = Color(0xFFF2F2F2),
                    radius = this.size.height / 2,
                    center = Offset(x = this.size.width / 2, y = this.size.height / 2)
                )
                drawArc(
                    style = Stroke(4.dp.toPx()),
                    color = progressColor,
                    startAngle = progressStartAngle,
                    sweepAngle = progressSweepAngle,
                    useCenter = false
                )
            }
            Icon(
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = iconId),
                tint = EGardenBlack,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = value,
            color = EGardenBlack,
            fontFamily = EGardenNunitoFontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 17.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = info,
            color = EGardenGreyLight,
            fontFamily = EGardenNunitoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun WaterSection(modifier: Modifier = Modifier) {
    Section(
        modifier = modifier,
        title = "WATER",
        value = "2 DAYS",
        info = "EVERY 7 DAYS",
        iconId = R.drawable.egarden_drop,
        progressStartAngle = -90f,
        progressSweepAngle = -110f,
        progressColor = Color(0xFF34BDF8)
    )
}

@Composable
private fun LightSection(modifier: Modifier = Modifier) {
    Section(
        modifier = modifier,
        title = "LIGHT",
        value = "56%",
        info = "18HRS A DAY",
        iconId = R.drawable.egarden_sun,
        progressStartAngle = -90f,
        progressSweepAngle = 250f,
        progressColor = Color(0xFFFFB26B)
    )
}

@Preview
@Composable
fun EGardenPlantsPagerPreview() {
    PlantsPager()
}

@Preview(showBackground = true)
@Composable
fun EGardenWaterSectionPreview() {
    WaterSection()
}

@Preview(showBackground = true)
@Composable
fun EGardenLightSectionPreview() {
    LightSection()
}

@Preview
@Composable
fun EGardenDashboardPreview() {
    EGardenDashboard(Modifier.fillMaxSize())
}