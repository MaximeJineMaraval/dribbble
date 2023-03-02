package com.jine.dribbble.skeuomorphism

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner
import com.jine.dribbble.R
import com.jine.dribbble.customClick
import com.jine.dribbble.showNotImplementedToast
import java.util.*

//region Screen

@Composable
fun SkeuomorphismScreen5(onBackClicked: () -> Unit) {
    SkeuomorphismBottomSheetScaffold(
        peekHeight = 345.dp,
        sheetContent = {
            BottomSheetContent()
        },
        content = {
            SkeuomorphismToolbar(onBackClicked = onBackClicked)
            SkeuomorphismTitle(title = "Payment")
            SkeuomorphismText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                text = "We welcome authors who provide top \nquality and useful creative resources",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = SkeuomorphismContentSecondaryColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            CardsSection()
            Spacer(modifier = Modifier.height(20.dp))
            AddCardButton()
        })
}

//endregion

//region Components

private sealed class Card(val id: String = UUID.randomUUID().toString()) {
    class CreditCard(val cardNumber: String, val expirationDate: String, val typeIconId: Int) :
        Card()

    object Paypal : Card()
}

@Composable
private fun CardsSection() {
    val creditCard = Card.CreditCard("**** **** 2203", "09 / 20", R.drawable.skeuomorphism_visa)
    val payPal = Card.Paypal
    var selectedItemId by remember {
        mutableStateOf(creditCard.id)
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
        content = {
            items(items = listOf(creditCard, payPal)) { card ->
                CardItem(card = card, isSelected = selectedItemId == card.id, onSelected = {
                    selectedItemId = if (card.id == selectedItemId) {
                        ""
                    } else {
                        card.id
                    }
                })
            }
        })
}

@Composable
private fun CardItem(card: Card, isSelected: Boolean, onSelected: () -> Unit) {
    val contentColor = Color(0xFF2E476E)
    val cardCornerRadius = 20.dp

    var isPressed by remember { mutableStateOf(false) }

    val outerButtonElevation by animateDpAsState(
        targetValue = if (isSelected) {
            if (isPressed) 20.dp else 10.dp
        } else {
            if (isPressed) 4.dp else 10.dp
        },
        animationSpec = skeuomorphismButtonAnimationSpec()
    )
    val cardColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFFE3EDF7) else Color(0xFFE8F3FB),
        animationSpec = skeuomorphismButtonAnimationSpec()
    )
    val borderRadiusWidth by animateDpAsState(
        targetValue = if (isSelected) 2.dp else 0.dp,
        animationSpec = skeuomorphismButtonAnimationSpec()
    )
    val borderRadiusLightColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFFFFFFFF) else SkeuomorphismBackgroundColor,
        animationSpec = skeuomorphismButtonAnimationSpec()
    )
    val borderRadiusDarkColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFFCCD9E4) else SkeuomorphismBackgroundColor,
        animationSpec = skeuomorphismButtonAnimationSpec()
    )
    Box(
        modifier = Modifier
            .height(97.dp)
            .width(202.dp)
            .neu(
                lightShadowColor = Color(0xFFFFFFFF).copy(alpha = 0.72f),
                darkShadowColor = Color(0xFFB0C3D2).copy(alpha = 0.72f),
                shape = if (isSelected) Pressed(RoundedCorner(cardCornerRadius)) else Flat(
                    RoundedCorner(cardCornerRadius)
                ),
                shadowElevation = outerButtonElevation
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = cardColor, shape = RoundedCornerShape(cardCornerRadius))
                .border(
                    width = borderRadiusWidth,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            borderRadiusLightColor,
                            borderRadiusDarkColor
                        )
                    ),
                    shape = RoundedCornerShape(cardCornerRadius)
                )
                .customClick(
                    onClick = onSelected,
                    onPress = { isPressed = true },
                    onRelease = { isPressed = false }),
            contentAlignment = Alignment.Center
        ) {
            when (card) {
                is Card.Paypal -> {
                    Image(
                        painter = painterResource(id = R.drawable.skeuomorphism_paypal),
                        contentDescription = null,
                        modifier = Modifier
                            .height(32.dp),
                        contentScale = ContentScale.FillHeight,
                        colorFilter = ColorFilter.tint(color = contentColor)
                    )
                }
                is Card.CreditCard -> {
                    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                        val (cardNumber, expirationDate, logo) = createRefs()
                        Text(
                            modifier = Modifier.constrainAs(cardNumber) {
                                top.linkTo(parent.top, 20.dp)
                                start.linkTo(parent.start, 26.dp)
                            },
                            text = card.cardNumber,
                            fontWeight = FontWeight.Bold,
                            fontFamily = SkeuomorphismSecondaryFontFamily,
                            fontSize = 17.sp,
                            color = contentColor
                        )
                        Text(
                            modifier = Modifier.constrainAs(expirationDate) {
                                start.linkTo(cardNumber.start)
                                top.linkTo(cardNumber.bottom, 14.dp)
                            },
                            text = card.expirationDate,
                            fontWeight = FontWeight.Bold,
                            fontFamily = SkeuomorphismSecondaryFontFamily,
                            fontSize = 17.sp,
                            color = contentColor
                        )
                        Image(
                            modifier = Modifier
                                .constrainAs(logo) {
                                    top.linkTo(expirationDate.top)
                                    bottom.linkTo(expirationDate.bottom)
                                    end.linkTo(parent.end, 16.dp)
                                }
                                .size(53.dp),
                            painter = painterResource(id = card.typeIconId),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = contentColor)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AddCardButton() {
    val context = LocalContext.current
    var isPressed by remember { mutableStateOf(false) }
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 8.dp else 24.dp,
        animationSpec = skeuomorphismButtonAnimationSpec()
    )
    val color by animateColorAsState(
        targetValue = if (isPressed) {
            Color(0xFFFF69A9).copy(alpha = 0.32f)
        } else {
            Color(0xFFFF69A9).copy(alpha = 0.18f)
        },
        animationSpec = skeuomorphismButtonAnimationSpec()
    )
    Box(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .neu(
                lightShadowColor = Color.Transparent,
                darkShadowColor = color,
                shape = Flat(RoundedCorner(14.dp)),
                shadowElevation = elevation
            )
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFFEAAA0),
                            Color(0xFFCD8A9A)
                        )
                    ), shape = RoundedCornerShape(14.dp)
                )
                .customClick(
                    onClick = { showNotImplementedToast(context) },
                    onPress = { isPressed = true },
                    onRelease = {
                        isPressed = false
                    }), contentAlignment = Alignment.Center

        ) {
            SkeuomorphismText(
                text = "Add new card",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

@Composable
private fun BottomSheetContent() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SkeuomorphismText(
                text = "Feb, 2020",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = SkeuomorphismContentPrimaryColor
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { showNotImplementedToast(context) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_skeuomorphism_menu),
                    contentDescription = "Options",
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF7A8AA3)
                )
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            SkeuomorphismText(
                text = "Subtotal",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = SkeuomorphismContentSecondaryColor
            )
            Spacer(modifier = Modifier.weight(1f))
            SkeuomorphismText(
                text = "\$26.55",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = SkeuomorphismContentSecondaryColor
            )
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .padding(horizontal = 24.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            SkeuomorphismText(
                text = "Delivery fee",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = SkeuomorphismContentSecondaryColor
            )
            Spacer(modifier = Modifier.weight(1f))
            SkeuomorphismText(
                text = "\$1.25",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = SkeuomorphismContentSecondaryColor
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            SkeuomorphismText(
                text = "Total",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = SkeuomorphismContentPrimaryColor
            )
            Spacer(modifier = Modifier.weight(1f))
            SkeuomorphismText(
                text = "\$27.80",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = SkeuomorphismContentPrimaryColor
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        SkeuomorphismButton(modifier = Modifier.padding(horizontal = 24.dp), label = "Continue") {
            showNotImplementedToast(context)
        }
    }
}

//endregion

//region Preview

//@Preview
//@Composable
//private fun AddCardButtonPreview() {
//    AddCardButton()
//}
//
//@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
//@Composable
//private fun CardItemPayPalPreview() {
//    Box(modifier = Modifier.size(250.dp), contentAlignment = Alignment.Center) {
//        CardItem(card = Card.Paypal, isSelected = false, onSelected = {})
//    }
//}
//
//@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
//@Composable
//private fun CardItemPayPalSelectedPreview() {
//    Box(modifier = Modifier.size(250.dp), contentAlignment = Alignment.Center) {
//        CardItem(card = Card.Paypal, isSelected = true, onSelected = {})
//    }
//}
//
//@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
//@Composable
//private fun CardItemCreditCardPreview() {
//    Box(modifier = Modifier.size(250.dp), contentAlignment = Alignment.Center) {
//        CardItem(
//            card = Card.CreditCard("**** **** 2203", "09 / 20", R.drawable.skeuomorphism_visa),
//            isSelected = false,
//            onSelected = {})
//    }
//}
//
//
//@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
//@Composable
//private fun CardsSectionPreview() {
//    CardsSection()
//}

@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
@Composable
private fun BottomSheetContentPreview() {
    BottomSheetContent()
}

@Preview
@Composable
private fun SkeuomorphismScreen5Preview() {
    SkeuomorphismScreen5 {}
}

//endregion