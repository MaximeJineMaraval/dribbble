package com.jine.dribbble.snowboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.jine.dribbble.R
import kotlin.math.absoluteValue

@Composable
fun SnowboardHomePage(onCollectionOpened: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SnowboardBackground)
    ) {
        SnowboardToolbar()
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(48.snowboardDp))
            }
            item {
                TagLine()
            }
            item {
                Spacer(modifier = Modifier.height(48.snowboardDp))
            }
            item {
                BoardPager(onClick = onCollectionOpened)
            }
            item {
                Spacer(modifier = Modifier.height(48.snowboardDp))
            }
            item {
                MostPopularSection()
            }
            item {
                Spacer(modifier = Modifier.height(48.snowboardDp))
            }
        }
    }
}

@Composable
private fun TagLine() {
    Column(modifier = Modifier.padding(horizontal = 48.snowboardDp)) {
        SnowboardText(
            text = "Full color life",
            fontWeight = FontWeight.Normal,
            fontSize = 34.snowboardSp,
            color = SnowboardContentPrimary
        )
        SnowboardText(
            text = "only on",
            fontWeight = FontWeight.Normal,
            fontSize = 34.snowboardSp,
            color = SnowboardContentPrimary
        )
        SnowboardText(
            text = "the slope.",
            fontWeight = FontWeight.Black,
            fontSize = 34.snowboardSp,
            color = SnowboardContentPrimary
        )
    }
}

@Composable
private fun MostPopularSection() {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 48.snowboardDp)
    ) {
        val (title, image, productName, productType, ratingIcon, ratingValue) = createRefs()
        SnowboardText(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            text = "Most Popular",
            fontWeight = FontWeight.Black,
            fontSize = 18.snowboardSp,
            color = SnowboardContentPrimary
        )
        Image(
            modifier = Modifier
                .height(155.snowboardDp)
                .constrainAs(image) {
                    top.linkTo(title.bottom, 6.snowboardDp)
                    start.linkTo(title.start)
                    end.linkTo(title.end)
                },
            painter = painterResource(id = R.drawable.snowboard_mostpopularboard),
            contentDescription = null,
        )
        SnowboardText(
            modifier = Modifier.constrainAs(productName) {
                top.linkTo(title.bottom, 16.snowboardDp)
                start.linkTo(image.end, 22.snowboardDp)
            },
            text = "Long Winter 23",
            fontWeight = FontWeight.Bold,
            fontSize = 18.snowboardSp,
            color = SnowboardContentPrimary
        )
        SnowboardText(
            modifier = Modifier.constrainAs(productType) {
                top.linkTo(productName.bottom, 4.snowboardDp)
                start.linkTo(productName.start)
            },
            text = "All mountain",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.snowboardSp,
            color = SnowboardContentSecondary
        )
        Icon(modifier = Modifier.constrainAs(ratingIcon) {
            top.linkTo(productType.bottom, 18.snowboardDp)
            start.linkTo(productType.start)
        }, painter = painterResource(id = R.drawable.ic_snowboard_star), contentDescription = null)
        SnowboardText(
            modifier = Modifier.constrainAs(ratingValue) {
                top.linkTo(ratingIcon.top)
                bottom.linkTo(ratingIcon.bottom)
                start.linkTo(ratingIcon.end, 8.snowboardDp)
            },
            text = "4.9",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.snowboardSp,
            color = SnowboardContentPrimary
        )

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun BoardPager(onClick: () -> Unit) {
    HorizontalPager(
        modifier = Modifier,
        contentPadding = PaddingValues(start = 48.snowboardDp, end = 132.snowboardDp),
        count = 2,
    ) { page ->
        val imageId: Int
        val title: String
        val subTitle: String
        when (page) {
            0 -> {
                imageId = R.drawable.snowboard_parkboard
                title = "All mountain"
                subTitle = "24 models"
            }
            else -> {
                imageId = R.drawable.snowboard_parkboard
                title = "Park"
                subTitle = "17 models"
            }
        }
        BoardCard(
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
            imageId = imageId,
            title = title,
            subtitle = subTitle,
            onClick = onClick
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BoardCard(
    modifier: Modifier,
    imageId: Int,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(modifier = modifier,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(40.snowboardDp),
        elevation = 12.snowboardDp,
        onClick = onClick,
        content = {
            Column(
                modifier = Modifier.padding(
                    top = 48.snowboardDp,
                    bottom = 16.snowboardDp,
                    start = 24.snowboardDp,
                    end = 24.snowboardDp
                ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.height(188.snowboardDp),
                    painter = painterResource(id = imageId),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.height(18.snowboardDp))
                SnowboardText(
                    text = title,
                    fontWeight = FontWeight.Black,
                    fontSize = 18.snowboardSp,
                    color = SnowboardContentPrimary
                )
                SnowboardText(
                    text = subtitle,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.snowboardSp,
                    color = SnowboardContentSecondary
                )
            }
        })
}

// Previews
@Preview
@Composable
private fun BoardPagerPreview() {
    BoardPager {}
}

@Preview
@Composable
private fun BoardCardPreview() {
    BoardCard(
        modifier = Modifier,
        imageId = R.drawable.snowboard_parkboard,
        title = "Park",
        subtitle = "17 models",
        {}
    )
}

@Preview
@Composable
private fun MostPopularSectionPreview() {
    MostPopularSection()
}

@Preview
@Composable
private fun TagLinePreview() {
    TagLine()
}

@Preview
@Composable
private fun SnowboardHomePagePreview() {
    SnowboardHomePage {}
}