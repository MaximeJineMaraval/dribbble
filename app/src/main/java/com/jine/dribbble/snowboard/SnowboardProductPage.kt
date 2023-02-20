package com.jine.dribbble.snowboard

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

@Composable
fun SnowboardProductPage(onBackButtonClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SnowboardBackground)
    ) {
        SnowboardToolbar(onBackClicked = onBackButtonClicked)
        Spacer(modifier = Modifier.height(48.snowboardDp))
        ProductPager()
        Spacer(modifier = Modifier.height(24.snowboardDp))
        BottomActionBar()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ProductPager() {
    val pagerState = rememberPagerState()
    val pageNumber = 3
    Column() {
        HorizontalPager(
            state = pagerState,
            count = pageNumber,
            content = {
                when (it) {
                    1 -> ProductDetail()
                    2 -> ProductDetail()
                    else -> ProductDetail()
                }
            }
        )
        Spacer(modifier = Modifier.height(24.snowboardDp))
        PageIndicator(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            numberOfPages = pageNumber,
            selectedPage = pagerState.currentPage
        )
    }
}

@Composable
private fun PageIndicator(
    modifier: Modifier = Modifier,
    numberOfPages: Int,
    selectedPage: Int = 0,
) {
    Log.d("MAX", "SelectedPage : $selectedPage")
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.snowboardDp)
    ) {
        for (i in 0 until numberOfPages) {
            PageIndicatorView(isSelected = i == selectedPage)
        }
    }
}


@Composable
private fun PageIndicatorView(isSelected: Boolean) {
    val animationDurationInMillis = 300
    val defaultSize = 5.snowboardDp
    val color: Color by animateColorAsState(
        targetValue = if (isSelected) SnowboardContentPrimary else SnowboardContentSecondary,
        animationSpec = tween(durationMillis = animationDurationInMillis)
    )
    val width: Dp by animateDpAsState(
        targetValue = if (isSelected) 14.snowboardDp else defaultSize,
        animationSpec = tween(durationMillis = animationDurationInMillis)
    )
    Canvas(modifier = Modifier.size(width = width, height = defaultSize)) {
        drawRoundRect(
            color = color,
            topLeft = Offset.Zero,
            size = Size(width = width.toPx(), height = defaultSize.toPx()),
            cornerRadius = CornerRadius(x = defaultSize.toPx(), y = defaultSize.toPx())
        )
    }
}

@Composable
private fun ProductDetail() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 48.snowboardDp)
    ) {
        val (productCategory, productName, price, size, print, rating, image) = createRefs()

        SnowboardText(
            modifier = Modifier.constrainAs(productCategory) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            text = "POWDER SNOWBOARD",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.snowboardSp,
            color = SnowboardContentSecondary
        )

        SnowboardText(
            modifier = Modifier.constrainAs(productName) {
                top.linkTo(productCategory.bottom, 22.snowboardDp)
                start.linkTo(productCategory.start)
            },
            text = "Burton Tree Resonator",
            fontWeight = FontWeight.Black,
            fontSize = 34.snowboardSp,
            color = SnowboardContentPrimary
        )

        Row(modifier = Modifier.constrainAs(price) {
            top.linkTo(productName.bottom, 16.snowboardDp)
            start.linkTo(productName.start)
        }) {
            SnowboardText(
                text = "USD",
                fontWeight = FontWeight.Medium,
                fontSize = 24.snowboardSp,
                color = SnowboardContentSecondary
            )
            Spacer(modifier = Modifier.width(8.snowboardDp))
            SnowboardText(
                text = "1,325",
                fontWeight = FontWeight.Medium,
                fontSize = 24.snowboardSp,
                color = SnowboardContentPrimary
            )
        }

        ProductAdditionalData(
            modifier = Modifier.constrainAs(size) {
                top.linkTo(price.bottom, 48.snowboardDp)
                start.linkTo(price.start)
            },
            name = "SIZE",
            value = "159W"
        )
        ProductAdditionalData(
            modifier = Modifier.constrainAs(print) {
                top.linkTo(size.bottom, 16.snowboardDp)
                start.linkTo(size.start)
            },
            name = "PRINT",
            imageValue = R.drawable.snowboard_print
        )
        ProductAdditionalData(
            modifier = Modifier.constrainAs(rating) {
                top.linkTo(print.bottom, 16.snowboardDp)
                start.linkTo(print.start)
            },
            name = "RATING",
            value = "4.8"
        )

        Image(
            modifier = Modifier.constrainAs(image) {
                top.linkTo(productName.top, 40.snowboardDp)
                bottom.linkTo(rating.bottom)
                height = Dimension.fillToConstraints
                end.linkTo(parent.end, 45.snowboardDp)
            },
            painter = painterResource(id = R.drawable.snowboard_boarddetail),
            contentDescription = null
        )
    }
}

@Composable
private fun ProductAdditionalData(
    modifier: Modifier = Modifier,
    name: String,
    value: String? = null,
    imageValue: Int? = null
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        SnowboardText(
            text = name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.snowboardSp,
            color = SnowboardContentSecondary
        )
        Spacer(modifier = Modifier.height(8.snowboardDp))
        if (value != null) {
            Box(
                modifier = Modifier
                    .size(72.snowboardDp)
                    .background(color = Color.White, shape = RoundedCornerShape(20.snowboardDp))
            ) {
                SnowboardText(
                    modifier = Modifier.align(Alignment.Center),
                    text = value,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.snowboardSp,
                    color = SnowboardPrimaryColor
                )
            }
        }
        if (imageValue != null) {
            Image(
                modifier = Modifier
                    .size(72.snowboardDp)
                    .clip(RoundedCornerShape(20.snowboardDp)),
                painter = painterResource(id = R.drawable.snowboard_print),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun BottomActionBar() {
    val context = LocalContext.current
    val animationDurationInMillis = 300
    var isLiked by remember { mutableStateOf(false) }
    val buttonColor: Color by animateColorAsState(
        targetValue = if (isLiked) SnowboardLikeColor else Color.White,
        animationSpec = tween(durationMillis = animationDurationInMillis)
    )
    val iconColor: Color by animateColorAsState(
        targetValue = if (isLiked) Color.White else SnowboardLikeColor,
        animationSpec = tween(durationMillis = animationDurationInMillis)
    )
    Row(Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(48.snowboardDp))
        FloatingActionButton(
            modifier = Modifier.size(60.snowboardDp),
            backgroundColor = buttonColor,
            contentColor = iconColor,
            onClick = { isLiked = isLiked.not() },
            content = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Like")
            })
        Spacer(modifier = Modifier.width(32.snowboardDp))
        FloatingActionButton(
            modifier = Modifier
                .height(60.snowboardDp)
                .weight(1f),
            backgroundColor = SnowboardContentPrimary,
            onClick = { showNotImplementedToast(context) },
            content = {
                SnowboardText(
                    text = "Add to cart",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.snowboardSp,
                    color = Color.White
                )
            })
        Spacer(modifier = Modifier.width(48.snowboardDp))
    }
}

// Previews

@Preview
@Composable
private fun ProductAdditionalDataPreviewWithImageValue() {
    ProductAdditionalData(name = "PRINT", imageValue = R.drawable.snowboard_print)
}

@Preview
@Composable
private fun ProductAdditionalDataPreviewWithValue() {
    ProductAdditionalData(name = "SIZE", value = "159W")
}

@Preview
@Composable
private fun ProductDetailPreview() {
    ProductDetail()
}

@Preview
@Composable
private fun BottomActionBarPreview() {
    BottomActionBar()
}

@Preview
@Composable
private fun SnowboardProductPagePreview() {
    SnowboardProductPage {}
}