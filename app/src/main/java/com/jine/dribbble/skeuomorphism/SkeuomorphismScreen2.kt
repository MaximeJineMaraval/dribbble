package com.jine.dribbble.skeuomorphism

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import com.jine.dribbble.customClick
import com.jine.dribbble.showNotImplementedToast

//region Screen

@Composable
fun SkeuomorphismScreen2(onBackClicked: () -> Unit) {
    var phoneNumber by remember {
        mutableStateOf("+38 063 864 68 90")
    }
    SkeuomorphismBottomSheetScaffold(
        peekHeight = 440.dp,
        sheetContent = {
            Keyboard(
                onAddNumber = { number -> phoneNumber += number.toString() },
                onRemoveChar = { phoneNumber = phoneNumber.dropLast(1) })
        },
        content = {
            SkeuomorphismToolbar(onBackClicked = onBackClicked)
            SkeuomorphismTitle(title = "Register phone")
            Spacer(modifier = Modifier.height(14.dp))
            SkeuomorphismText(
                modifier = Modifier.fillMaxWidth(),
                text = "Enter your mobile number to enable\n" +
                        "2-step verification",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = SkeuomorphismContentSecondaryColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(35.dp))
            PhoneNumberTextField(phoneNumber)
        })
}

//endregion

//region Components

@Composable
private fun PhoneNumberTextField(phoneNumber: String) {
    val roundedCornerSize = 20.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .padding(horizontal = 24.dp)
            .background(color = Color(0xFFE3EDF7), shape = RoundedCornerShape(roundedCornerSize))
            .border(
                width = 2.dp, brush = Brush.linearGradient(
                    listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFFCCD9E4)
                    )
                ), shape = RoundedCornerShape(roundedCornerSize)
            )
            .neu(
                darkShadowColor = Color(0xFFB1C5D5),
                lightShadowColor = Color(0xFFFFFFFF).copy(0.75f),
                shape = Pressed(RoundedCorner(roundedCornerSize)),
                shadowElevation = 10.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            modifier = Modifier
                .height(52.dp)
                .wrapContentWidth(),
            value = phoneNumber,
            onValueChange = { },
            readOnly = true,
            textStyle = LocalTextStyle.current.copy(
                fontFamily = SkeuomorphismPrimaryFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = SkeuomorphismContentSecondaryColor,
                textAlign = TextAlign.Center
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_skeuomorphism_flag_de),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFE3EDF7),
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = SkeuomorphismMainColor,
                focusedLabelColor = SkeuomorphismMainColor,
            ),
        )
    }
}

@Composable
private fun Keyboard(onAddNumber: (number: Int) -> Unit, onRemoveChar: () -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        Row {
            Spacer(modifier = Modifier.weight(1f))
            KeyboardTouchCard(number = 1) {
                onAddNumber(1)
            }
            Spacer(modifier = Modifier.weight(1f))
            KeyboardTouchCard(number = 2) {
                onAddNumber(2)
            }
            Spacer(modifier = Modifier.weight(1f))
            KeyboardTouchCard(number = 3) {
                onAddNumber(3)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row {
            Spacer(modifier = Modifier.weight(1f))
            KeyboardTouchCard(number = 4) {
                onAddNumber(4)
            }
            Spacer(modifier = Modifier.weight(1f))
            KeyboardTouchCard(number = 5) {
                onAddNumber(5)
            }
            Spacer(modifier = Modifier.weight(1f))
            KeyboardTouchCard(number = 6) {
                onAddNumber(6)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row {
            Spacer(modifier = Modifier.weight(1f))
            KeyboardTouchCard(number = 7) {
                onAddNumber(7)
            }
            Spacer(modifier = Modifier.weight(1f))
            KeyboardTouchCard(number = 8) {
                onAddNumber(8)
            }
            Spacer(modifier = Modifier.weight(1f))
            KeyboardTouchCard(number = 9) {
                onAddNumber(9)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row {
            Spacer(modifier = Modifier.weight(1f))
            FakeKeyboardTouchCard()
            Spacer(modifier = Modifier.weight(1f))
            KeyboardTouchCard(number = 0) {
                onAddNumber(0)
            }
            Spacer(modifier = Modifier.weight(1f))
            KeyboardTouchCard(iconId = R.drawable.ic_skeuomorphism_backspace) {
                onRemoveChar()
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(24.dp))
        val context = LocalContext.current
        SkeuomorphismButton(modifier = Modifier.padding(horizontal = 24.dp), label = "Continue") {
            showNotImplementedToast(context)
        }
    }
}


@Composable
private fun FakeKeyboardTouchCard() {
    Box(
        modifier = Modifier
            .height(62.dp)
            .width(96.dp)
    )
}

@Composable
private fun KeyboardTouchCard(number: Int? = null, iconId: Int? = null, onClick: () -> Unit) {
    val roundedCornerSize = 12.dp
    val context = LocalContext.current
    var isPressed by remember { mutableStateOf(false) }

    val insideElevation by animateDpAsState(
        targetValue = if (isPressed) 10.dp else 0.dp,
        animationSpec = skeuomorphismButtonAnimationSpec()
    )
    Box(
        modifier = Modifier
            .customClick(
                onClick = onClick,
                onPress = { isPressed = true },
                onRelease = { isPressed = false })
            .height(62.dp)
            .width(96.dp)
            .background(
                color = SkeuomorphismBackgroundColor,
                shape = RoundedCornerShape(roundedCornerSize)
            )
            .neu(
                darkShadowColor = Color(0xFFB1C5D5),
                lightShadowColor = Color(0xFFFFFFFF).copy(0.75f),
                shape = Pressed(RoundedCorner(roundedCornerSize)),
                shadowElevation = insideElevation
            ),
        contentAlignment = Alignment.Center
    ) {
        if (number != null) {
            SkeuomorphismText(
                text = number.toString(),
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                color = SkeuomorphismContentPrimaryColor
            )
        } else if (iconId != null) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = SkeuomorphismContentSecondaryColor
            )
        }
    }
}
//endregion

//region Preview

@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
@Composable
private fun KeyboardPreview() {
    Keyboard({}, {})
}

@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
@Composable
private fun KeyboardTouchCardPreview() {
    KeyboardTouchCard(number = 2) {}
}

@Preview
@Composable
private fun PhoneNUmberTextFieldPreview() {
    PhoneNumberTextField("+38 063 864 68 90")
}

@Preview
@Composable
private fun SkeSkeuomorphismScreen2Preview() {
    SkeuomorphismScreen2 {}
}

//endregion