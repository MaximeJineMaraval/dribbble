package com.jine.dribbble.foodee

import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

//region Screen

@Composable
fun FoodeeMyCart(onCloseClicked: () -> Unit) {
    var totalPrice by remember { mutableStateOf(0.0f) }
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
                Header(onCloseClicked = onCloseClicked)
            }
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
            item {
                Breadcrumb(currentStep = 1)
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                OrderList()
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                AdditionalProduct()
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        })

        BottomBar(modifier = Modifier.constrainAs(bottomBar) {
            bottom.linkTo(parent.bottom, margin = 8.dp)
            start.linkTo(parent.start, margin = 10.dp)
            end.linkTo(parent.end, margin = 10.dp)
            width = Dimension.fillToConstraints
        }, totalPrice = totalPrice)
    }
}

//endregion

//region Components

@Composable
private fun Header(onCloseClicked: () -> Unit) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (title, closeButton) = createRefs()
        //region Title
        FoodeeText(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = 18.dp)
                start.linkTo(parent.start, margin = 18.dp)
            },
            text = "My Cart",
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            color = FoodeeContentPrimaryColor
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
    }
}

@Suppress("SameParameterValue")
@Composable
private fun Breadcrumb(currentStep: Int) {
    //if (currentStep >= number) 1f else 0.25f),
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (label1, number1, line2, label2, number2, line3, label3, number3) = createRefs()

        val alphaStep1 = if (currentStep >= 1) 1f else 0.25f
        val alphaStep2 = if (currentStep >= 2) 1f else 0.25f
        val alphaStep3 = if (currentStep >= 3) 1f else 0.25f

        BreadcrumbLabel(
            modifier = Modifier
                .constrainAs(label1) {
                    bottom.linkTo(number1.top)
                    start.linkTo(number1.start)
                    end.linkTo(number1.end)
                }
                .alpha(alphaStep1),
            label = "My Order")
        BreadcrumbLabel(
            modifier = Modifier
                .constrainAs(label2) {
                    bottom.linkTo(number2.top)
                    start.linkTo(number2.start)
                    end.linkTo(number2.end)
                }
                .alpha(alphaStep2),
            label = "Details"
        )
        BreadcrumbLabel(
            modifier = Modifier
                .constrainAs(label3) {
                    bottom.linkTo(number3.top)
                    start.linkTo(number3.start)
                    end.linkTo(number3.end)
                }
                .alpha(alphaStep3), label = "Payment"
        )
        BreadcrumbNumber(
            modifier = Modifier
                .constrainAs(number1) {
                    start.linkTo(parent.start, margin = 36.dp)
                    bottom.linkTo(parent.bottom)
                }
                .alpha(alphaStep1), number = 1
        )
        BreadcrumbNumber(
            modifier = Modifier
                .constrainAs(number2) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(line2.end)
                    end.linkTo(line3.start)
                }
                .alpha(alphaStep2), number = 2
        )
        BreadcrumbNumber(
            modifier = Modifier
                .constrainAs(number3) {
                    end.linkTo(parent.end, margin = 36.dp)
                    bottom.linkTo(parent.bottom)
                }
                .alpha(alphaStep3), number = 3
        )
        BreadcrumbLine(
            modifier = Modifier
                .constrainAs(line2) {
                    start.linkTo(number1.end)
                    end.linkTo(number2.start)
                    top.linkTo(number1.top)
                    bottom.linkTo(number1.bottom)
                    width = Dimension.fillToConstraints
                }
                .alpha(alphaStep2)
        )
        BreadcrumbLine(
            modifier = Modifier
                .constrainAs(line3) {
                    start.linkTo(number2.end)
                    end.linkTo(number3.start)
                    top.linkTo(number1.top)
                    bottom.linkTo(number1.bottom)
                    width = Dimension.fillToConstraints
                }
                .alpha(alphaStep3)
        )
    }
}

@Composable
private fun BreadcrumbLabel(modifier: Modifier, label: String) {
    FoodeeText(
        modifier = modifier.height(20.dp),
        text = label,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        color = FoodeeContentPrimaryColor
    )
}

@Composable
private fun BreadcrumbNumber(modifier: Modifier, number: Int) {
    Box(
        modifier = modifier
            .background(color = FoodeeAccentPrimaryColor, shape = CircleShape)
            .size(44.dp),
        contentAlignment = Alignment.Center
    ) {
        FoodeeText(
            text = "$number",
            fontWeight = FontWeight.Light,
            fontSize = 11.sp,
            color = Color.White,
        )
    }
}

@Composable
private fun BreadcrumbLine(modifier: Modifier) {
    Box(
        modifier = modifier
            .height(1.dp)
            .background(color = FoodeeAccentPrimaryColor)
    )
}

@Composable
private fun OrderList() {
}

@Composable
private fun AdditionalProduct() {
}

@Composable
private fun BottomBar(modifier: Modifier, totalPrice: Float) {
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
        AddToCartButton(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            totalPrice = totalPrice
        )
    }
}

@Composable
private fun AddToCartButton(modifier: Modifier = Modifier, totalPrice: Float) {
    val context = LocalContext.current
    Button(
        modifier = modifier,
        onClick = { showNotImplementedToast(context) },
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            FoodeeText(
                text = "Add to Cart",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(12.dp))
            FoodeeText(
                text = "$${"%.2f".format(totalPrice)}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

//endregion

//region Preview

@Preview
@Composable
private fun BreadcrumbPreview() {
    Breadcrumb(currentStep = 2)
}

//@Preview
//@Composable
//private fun FoodeeMyCartPreview() {
//    FoodeeMyCart(onCloseClicked = {})
//}

//endregion__