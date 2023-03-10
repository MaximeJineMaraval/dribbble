package com.jine.dribbble.foodee

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
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

// TODO: Faire une animation au scroll : Conserver Mr.Cheeze + les badges + le close Button, réduire le burger de la hauteur des 2 badges. Cette partie s'anime au scroll et est donc sticky

//region Screen

@Composable
fun FoodeeProductPage(onCloseClicked: () -> Unit, onProductAddedToCart: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(FoodeeAccentPrimaryColor)
    systemUiController.setNavigationBarColor(FoodeeBackgroundPrimaryColor)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(FoodeeBackgroundPrimaryColor)
    ) {
        val (bottomBar) = createRefs()

        LazyColumn(modifier = Modifier
            .fillMaxSize(), content = {
            item {
                Header(onCloseClicked = onCloseClicked)
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                AdditionalFlavors()
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                NutritionFacts()
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                Description()
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
        }, onAddToCartClicked = onProductAddedToCart)
    }
}

//endregion

//region Components

@Composable
private fun Header(onCloseClicked: () -> Unit) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (title, closeButton, badges, image, background) = createRefs()

        //region Background
        Box(
            modifier = Modifier
                .constrainAs(background) {
                    top.linkTo(parent.top)
                    bottom.linkTo(image.bottom, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(
                    color = FoodeeAccentPrimaryColor,
                    shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
                )
        )
        //endregion

        //region Image
        Image(
            painter = painterResource(id = R.drawable.foodee_burger),
            contentDescription = null,
            modifier = Modifier
                .height(256.dp)
                .constrainAs(image) {
                    start.linkTo(parent.start, margin = 114.dp)
                    top.linkTo(badges.top)
                },
            contentScale = ContentScale.FillHeight
        )
        //endregion

        //region Title
        FoodeeText(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = 18.dp)
                start.linkTo(parent.start, margin = 18.dp)
            },
            text = "Mr. Cheezy",
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            color = Color.White
        )
        //endregion

        //region Close button
        Box(
            modifier = Modifier
                .constrainAs(closeButton) {
                    top.linkTo(title.top)
                    bottom.linkTo(title.bottom)
                    end.linkTo(parent.end, margin = 18.dp)
                }
                .clip(RoundedCornerShape(16.dp))
                .size(44.dp)
                .background(color = Color(0xFFF5CAC3), shape = RoundedCornerShape(16.dp))
                .clickable { onCloseClicked() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_foodee_close),
                contentDescription = "filter",
                modifier = Modifier.size(24.dp),
                tint = FoodeeAccentPrimaryColor
            )
        }
        //endregion

        //region Badges
        Column(modifier = Modifier.constrainAs(badges) {
            start.linkTo(title.start)
            top.linkTo(title.bottom, margin = 20.dp)
        }) {
            FoodeeText(
                modifier = Modifier
                    .background(
                        color = FoodeeBackgroundTertiaryColor,
                        shape = RoundedCornerShape(28.dp)
                    )
                    .padding(12.dp),
                text = "Classical Taste",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = FoodeeAccentPrimaryColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            FoodeeText(
                modifier = Modifier
                    .background(
                        color = FoodeeAccentSecondaryColor,
                        shape = RoundedCornerShape(28.dp)
                    )
                    .padding(12.dp),
                text = "Bestseller",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
        }
        //endregion
    }
}

private data class FoodeeFlavorModel(val imageId: Int, val name: String, val price: String)

@Composable
private fun AdditionalFlavors() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
    ) {
        FoodeeText(
            text = "Add More Flavor \uD83E\uDD29",
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            color = FoodeeContentPrimaryColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            FlavorItem(
                model = FoodeeFlavorModel(
                    imageId = R.drawable.foodee_cheese,
                    name = "Cheddar",
                    price = "+ \$0.79"
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            FlavorItem(
                model = FoodeeFlavorModel(
                    imageId = R.drawable.foodee_bacon,
                    name = "Bacon",
                    price = "+ \$0.59"
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            FlavorItem(
                model = FoodeeFlavorModel(
                    imageId = R.drawable.foodee_onion,
                    name = "Onion",
                    price = "+ \$0.29"
                )
            )
        }
    }
}

@Composable
private fun FlavorItem(model: FoodeeFlavorModel) {
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
    Column(
        modifier = Modifier
            .customClick(
                onClick = { showNotImplementedToast(context) },
                onPress = { isPressed = true },
                onRelease = { isPressed = false })
            .shadow(elevation, shape = RoundedCornerShape(28.dp), spotColor = Color(0xFFD6D3C0))
            .background(FoodeeBackgroundSecondaryColor, shape = RoundedCornerShape(28.dp))
            .padding(
                horizontal = 8.dp,
                vertical = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = model.imageId),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .scale(scale)
        )
        Spacer(modifier = Modifier.height(8.dp))
        FoodeeText(
            modifier = Modifier.scale(scale),
            text = model.name,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = FoodeeContentPrimaryColor
        )
        FoodeeText(
            modifier = Modifier.scale(scale),
            text = model.price,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = FoodeeContentPrimaryColor
        )
    }
}

@Composable
private fun NutritionFacts() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            FoodeeText(
                text = "Nutrition facts",
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                color = FoodeeContentPrimaryColor
            )
            Spacer(modifier = Modifier.weight(1f))
            FoodeeText(
                text = "650 Cal",
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = FoodeeContentPrimaryColor
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            NutritionFactItem(title = "35 g", subtitle = "Total Fat (45% DV)")
            Spacer(modifier = Modifier.weight(1f))
            NutritionFactItem(title = "43 g", subtitle = "Total Carbs (16% DV)")
            Spacer(modifier = Modifier.weight(1f))
            NutritionFactItem(title = "36 g", subtitle = "Protein")
        }
    }
}

@Composable
private fun NutritionFactItem(title: String, subtitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FoodeeText(
            text = title,
            fontWeight = FontWeight.Light,
            fontSize = 20.sp,
            color = FoodeeContentPrimaryColor
        )
        FoodeeText(
            text = subtitle,
            fontWeight = FontWeight.Light,
            fontSize = 11.sp,
            color = FoodeeContentPrimaryColor
        )
    }
}

@Composable
private fun Description() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {
        FoodeeText(
            text = "Description",
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            color = FoodeeContentPrimaryColor
        )
        Spacer(modifier = Modifier.height(12.dp))
        FoodeeText(
            text = "Each Mr.Cheezy® with Cheese Bacon burger features thick-cut applewood smoked bacon atop a ¼ lb.",
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = FoodeeContentPrimaryColor
        )
    }
}

@Composable
private fun BottomBar(modifier: Modifier, onAddToCartClicked: () -> Unit) {
    var productQuantity by remember { mutableStateOf(0) }
    Row(
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(28.dp),
                spotColor = Color(0xFFC2BFAC)
            )
            .height(92.dp)
            .background(FoodeeBackgroundPrimaryColor, shape = RoundedCornerShape(28.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarStepper(modifier = Modifier
            .fillMaxHeight()
            .weight(1f), onValueUpdated = { productQuantity = it })
        Spacer(modifier = Modifier.width(8.dp))
        AddToCartButton(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            quantity = productQuantity,
            onAddToCartClicked = onAddToCartClicked
        )
    }
}

@Composable
private fun BottomBarStepper(modifier: Modifier = Modifier, onValueUpdated: (value: Int) -> Unit) {
    var value by remember { mutableStateOf(0) }
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = FoodeeAccentPrimaryColor,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_foodee_minus),
            contentDescription = "minus",
            modifier = Modifier
                .clip(CircleShape)
                .size(24.dp)
                .background(color = FoodeeBackgroundTertiaryColor, shape = CircleShape)
                .clickable(onClick = {
                    value--
                    onValueUpdated(value)
                }, enabled = value > 0),
            tint = FoodeeAccentPrimaryColor
        )
        Spacer(modifier = Modifier.width(8.dp))
        FoodeeText(
            modifier = Modifier.width(34.dp),
            text = "$value",
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            color = FoodeeContentPrimaryColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_foodee_plus),
            contentDescription = "plus",
            modifier = Modifier
                .clip(CircleShape)
                .size(24.dp)
                .background(color = FoodeeAccentPrimaryColor, shape = CircleShape)
                .clickable(onClick = {
                    value++
                    onValueUpdated(value)
                }, enabled = value < 20),
            tint = Color.White
        )
    }
}

@Composable
private fun AddToCartButton(
    modifier: Modifier = Modifier,
    quantity: Int,
    onAddToCartClicked: () -> Unit
) {
    val unitPrice = 5.49
    Button(
        modifier = modifier,
        enabled = quantity > 0,
        onClick = onAddToCartClicked,
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(backgroundColor = FoodeeAccentPrimaryColor)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            FoodeeText(
                text = "Add to Cart",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.White
            )
            FoodeeText(
                text = "$${"%.2f".format(unitPrice * quantity)}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

//endregion

//region Preview

//@Preview
//@Composable
//private fun HeaderPreview() {
//    Header(onCloseClicked = {})
//}

//@Preview
//@Composable
//private fun AdditionalFlavorsPreview() {
//    AdditionalFlavors()
//}

//@Preview
//@Composable
//private fun FlavorItemPreview() {
//    FlavorItem(
//        FoodeeFlavorModel(
//            imageId = R.drawable.foodee_cheese,
//            name = "Cheddar",
//            price = "+ \$0.79"
//        )
//    )
//}

//@Preview
//@Composable
//private fun NutritionFactsPreview() {
//    NutritionFacts()
//}

//@Preview
//@Composable
//private fun DescriptionPreview() {
//    Description()
//}

//@Preview
//@Composable
//private fun BottomBarPreview() {
//    BottomBar(modifier = Modifier.fillMaxWidth(), onAddToCartClicked = {})
//}

//@Preview
//@Composable
//private fun BottomBarStepperPreview() {
//    BottomBarStepper(onValueUpdated = {})
//}

//@Preview
//@Composable
//private fun AddToCartButtonPreview() {
//    AddToCartButton(quantity = 2) {}
//}

@Preview
@Composable
private fun FoodeeProductPagePreview() {
    FoodeeProductPage(onCloseClicked = {}, onProductAddedToCart = {})
}

//endregion__