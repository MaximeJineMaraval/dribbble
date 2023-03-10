package com.jine.dribbble.foodee

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R
import com.jine.dribbble.customClick
import com.jine.dribbble.showNotImplementedToast

//region Screen

@Composable
fun FoodeeHomePage(onProductClicked: () -> Unit, onCartClicked: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(FoodeeBackgroundPrimaryColor)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(FoodeeBackgroundPrimaryColor)
    ) {
        val (bottomBar) = createRefs()

        LazyColumn(modifier = Modifier
            .fillMaxSize(), content = {
            item {
                Spacer(modifier = Modifier.height(18.dp))
            }
            item {
                Header()
            }
            item {
                Spacer(modifier = Modifier.height(26.dp))
            }
            item {
                Search()
            }
            item {
                Spacer(modifier = Modifier.height(26.dp))
            }
            item {
                TodaysMenuSection(onProductClicked = onProductClicked)
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                BestOffersSection()
            }
            item {
                Spacer(modifier = Modifier.height(108.dp))
            }
        })

        BottomBar(modifier = Modifier.constrainAs(bottomBar) {
            bottom.linkTo(parent.bottom, margin = 8.dp)
            start.linkTo(parent.start, margin = 10.dp)
            end.linkTo(parent.end, margin = 10.dp)
            width = Dimension.fillToConstraints
        }, onCartClicked = onCartClicked)
    }
}

//endregion

//region Components

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(28.dp),
                spotColor = Color(0xFFD6D3C0)
            )
            .height(88.dp)
            .background(color = FoodeeBackgroundSecondaryColor, shape = RoundedCornerShape(28.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            painter = painterResource(id = R.drawable.foodee_pp),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        FoodeeText(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.Bottom)
                .padding(bottom = 14.dp),
            text = "Welcome back, Pin!\nHow hungry are you?",
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            color = FoodeeContentPrimaryColor
        )
        Spacer(modifier = Modifier.width(12.dp))
    }
}

@Composable
private fun Search() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        //region Search component
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(16.dp))
            )
            var searchText by remember { mutableStateOf("") }
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    leadingIconColor = FoodeeContentSecondaryColor,
                    disabledLeadingIconColor = FoodeeContentSecondaryColor,
                    errorLeadingIconColor = FoodeeContentSecondaryColor,
                    cursorColor = FoodeeAccentPrimaryColor,
                    errorCursorColor = FoodeeAccentPrimaryColor,
                ),
                textStyle = TextStyle(
                    color = FoodeeContentPrimaryColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = FoodeeFontFamily
                ),
                placeholder = {
                    FoodeeText(
                        text = "Search...",
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        color = FoodeeContentSecondaryColor
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_foodee_search),
                        contentDescription = "search",
                        modifier = Modifier.size(24.dp),
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        //endregion

        Spacer(modifier = Modifier.width(12.dp))

        //region Filter component
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(44.dp)
                .background(color = FoodeeAccentPrimaryColor, shape = RoundedCornerShape(16.dp))
                .clickable { showNotImplementedToast(context) },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_foodee_filter),
                contentDescription = "filter",
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }
        //endregion
    }
}

private data class OfferModel(
    val imageId: Int,
    val backgroundColor: Color,
    val title: String,
    val subtitle: String
)

private data class CategoryModel(
    val imageId: Int,
    val backgroundColor: Color,
    val title: String
)

private data class BestOfferModel(
    val imageId: Int,
    val title: String,
    val subtitle: String
)

@Composable
private fun TodaysMenuSection(onProductClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        FoodeeText(
            modifier = Modifier.padding(start = 18.dp),
            text = "Today's Menu",
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            color = FoodeeContentPrimaryColor
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(
                    items = listOf(
                        OfferModel(
                            imageId = R.drawable.foodee_donut,
                            backgroundColor = Color(0xFF84A59D),
                            title = "Free Donut!",
                            subtitle = "For orders over \$20"
                        ),
                        OfferModel(
                            imageId = R.drawable.foodee_donut,
                            backgroundColor = Color(0xFFF6BD60),
                            title = "Big offer",
                            subtitle = "Describe the offer"
                        )
                    )
                ) {
                    OfferItem(model = it)
                }
            })
        Spacer(modifier = Modifier.height(32.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(
                    items = listOf(
                        CategoryModel(
                            imageId = R.drawable.foodee_burger,
                            backgroundColor = Color(0xFFFFEF92),
                            title = "Burgers"
                        ),
                        CategoryModel(
                            imageId = R.drawable.foodee_fries,
                            backgroundColor = Color(0xFFF5CAC3),
                            title = "Fries"
                        ),
                        CategoryModel(
                            imageId = R.drawable.foodee_soda,
                            backgroundColor = Color(0xFFB6D7CF),
                            title = "Drinks"
                        ),
                        CategoryModel(
                            imageId = R.drawable.foodee_hotdog,
                            backgroundColor = Color(0xFFA9D7DA),
                            title = "Hot Dogs"
                        ),
                    )
                ) {
                    CategoryItem(model = it, onProductClicked = onProductClicked)
                }
            })
    }
}

@Composable
private fun OfferItem(model: OfferModel) {
    val context = LocalContext.current
    var isPressed by remember { mutableStateOf(false) }
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 6.dp else 16.dp,
        animationSpec = foodeeClickAnimationSpec()
    )
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = foodeeClickAnimationSpec()
    )
    ConstraintLayout {
        val (card, labels, image) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(card) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(image.top, margin = 24.dp)
                    bottom.linkTo(parent.bottom)
                }
                .shadow(
                    elevation = elevation,
                    shape = RoundedCornerShape(28.dp),
                    spotColor = model.backgroundColor
                )
                .size(width = 358.dp, height = 128.dp)
                .background(color = model.backgroundColor, shape = RoundedCornerShape(28.dp))
                .customClick(
                    onClick = { showNotImplementedToast(context) },
                    onPress = { isPressed = true },
                    onRelease = { isPressed = false })
        )

        Column(modifier = Modifier
            .constrainAs(labels) {
                start.linkTo(card.start, margin = 20.dp)
                top.linkTo(card.top)
                bottom.linkTo(card.bottom)
            }
            .scale(scale)) {
            FoodeeText(
                text = model.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
            FoodeeText(
                text = model.subtitle,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                color = FoodeeContentTertiaryColor
            )
        }

        Image(
            painter = painterResource(id = model.imageId),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    end.linkTo(card.end, margin = 5.dp)
                }
                .size(130.dp)
                .scale(scale)
        )
    }
}

@Composable
private fun CategoryItem(model: CategoryModel, onProductClicked: () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 6.dp else 16.dp,
        animationSpec = foodeeClickAnimationSpec()
    )
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = foodeeClickAnimationSpec()
    )
    ConstraintLayout(
        modifier = Modifier
            .shadow(
                elevation = elevation,
                shape = RoundedCornerShape(28.dp),
                spotColor = Color(0xFFF2CC8F)
            )
            .size(width = 104.dp, height = 128.dp)
            .background(color = model.backgroundColor, shape = RoundedCornerShape(28.dp))
            .customClick(
                onClick = onProductClicked,
                onPress = { isPressed = true },
                onRelease = { isPressed = false })
    ) {
        val (image, title) = createRefs()

        Image(
            painter = painterResource(id = model.imageId),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 38.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                }
                .size(132.dp)
                .scale(scale),
            contentScale = ContentScale.Fit
        )

        FoodeeText(
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .scale(scale),
            text = model.title,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = FoodeeContentPrimaryColor
        )
    }
}

@Composable
private fun BestOffersSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
    ) {
        FoodeeText(
            text = "Best Offers \uD83D\uDC95",
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            color = FoodeeContentPrimaryColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        val models = listOf(
            BestOfferModel(
                imageId = R.drawable.foodee_hotdog,
                title = "Frenchdog",
                subtitle = "Tasty&Spicy \uD83C\uDF36\uD83C\uDF36\uD83C\uDF36"
            ),
            BestOfferModel(
                imageId = R.drawable.foodee_hotdog,
                title = "Frenchdog",
                subtitle = "Tasty&Spicy \uD83C\uDF36\uD83C\uDF36\uD83C\uDF36"
            )
        )
        for (model in models) {
            BestOfferItem(model = model)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun BestOfferItem(model: BestOfferModel) {
    val context = LocalContext.current
    var isPressed by remember { mutableStateOf(false) }
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 6.dp else 16.dp,
        animationSpec = foodeeClickAnimationSpec()
    )
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = foodeeClickAnimationSpec()
    )
    Row(
        modifier = Modifier
            .customClick(
                onClick = { showNotImplementedToast(context) },
                onPress = { isPressed = true },
                onRelease = { isPressed = false })
            .shadow(
                elevation = elevation,
                shape = RoundedCornerShape(28.dp),
                spotColor = Color(0xFFF2CC8F)
            )
            .fillMaxWidth()
            .background(FoodeeBackgroundPrimaryColor, shape = RoundedCornerShape(28.dp))
            .padding(horizontal = 24.dp, vertical = 14.dp)
    ) {
        Image(
            painter = painterResource(id = model.imageId),
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .scale(scale)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            FoodeeText(
                modifier = Modifier.scale(scale),
                text = model.title,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                color = FoodeeContentPrimaryColor
            )
            FoodeeText(
                modifier = Modifier.scale(scale),
                text = model.subtitle,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                color = FoodeeContentPrimaryColor
            )
        }
    }
}

@Composable
private fun BottomBar(modifier: Modifier, onCartClicked: () -> Unit) {
    Row(
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(28.dp),
                spotColor = Color(0xFFC2BFAC)
            )
            .height(92.dp)
            .background(FoodeeBackgroundPrimaryColor, shape = RoundedCornerShape(28.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarItem(
            modifier = Modifier.weight(1f),
            iconId = R.drawable.ic_foodee_map,
            label = "Location"
        )
        BottomBarItem(
            modifier = Modifier.weight(1f),
            iconId = R.drawable.ic_foodee_home,
            label = "Home",
            isSelected = true
        )
        BottomBarItem(
            modifier = Modifier.weight(1f),
            iconId = R.drawable.ic_foodee_cart,
            label = "My Cart",
            badge = 5,
            onItemClicked = onCartClicked
        )
        BottomBarItem(
            modifier = Modifier.weight(1f),
            iconId = R.drawable.ic_foodee_user,
            label = "Me"
        )
    }
}

@Composable
private fun BottomBarItem(
    modifier: Modifier,
    iconId: Int,
    label: String,
    badge: Int? = null,
    isSelected: Boolean = false,
    onItemClicked: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val color = if (isSelected) FoodeeAccentPrimaryColor else FoodeeContentPrimaryColor
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable { onItemClicked?.invoke() ?: showNotImplementedToast(context) }
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = iconId),
                contentDescription = label,
                tint = color
            )
            badge?.let {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 5.dp, end = 5.dp)
                ) {
                    FoodeeText(
                        modifier = Modifier
                            .size(16.dp)
                            .background(
                                color = FoodeeAccentPrimaryColor,
                                shape = CircleShape
                            ),
                        text = it.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        FoodeeText(
            text = label,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = color
        )
    }
}

//endregion

//region Preview

@Preview
@Composable
private fun HeaderPreview() {
    Header()
}

@Preview
@Composable
private fun SearchPreview() {
    Search()
}

@Preview
@Composable
private fun TodaysMenuSectionPreview() {
    TodaysMenuSection(onProductClicked = {})
}

@Preview
@Composable
private fun OfferItemPreview() {
    OfferItem(
        model = OfferModel(
            imageId = R.drawable.foodee_donut,
            backgroundColor = Color(0xFF84A59D),
            title = "Free Donut!",
            subtitle = "For orders over \$20"
        )
    )
}

@Preview
@Composable
private fun CategoryItemPreview() {
    CategoryItem(
        model = CategoryModel(
            imageId = R.drawable.foodee_burger,
            backgroundColor = Color(0xFFFFEF92),
            title = "Burgers"
        ),
        onProductClicked = {}
    )
}

@Preview
@Composable
private fun BestOffersSectionPreview() {
    BestOffersSection()
}

@Preview
@Composable
private fun BestOfferItemPreview() {
    BestOfferItem(
        BestOfferModel(
            imageId = R.drawable.foodee_hotdog,
            title = "Frenchdog",
            subtitle = "Tasty&Spicy \uD83C\uDF36\uD83C\uDF36\uD83C\uDF36"
        )
    )
}

@Preview
@Composable
private fun BottomBarPreview() {
    BottomBar(modifier = Modifier.fillMaxWidth(), onCartClicked = {})
}

@Preview
@Composable
private fun FoodeeHomePagePreview() {
    FoodeeHomePage(onProductClicked = {}, onCartClicked = {})
}

//endregion__