package com.jine.dribbble.skeuomorphism

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

//region Screen

@Composable
fun SkeuomorphismScreen3(onBackClicked: () -> Unit) {
    SkeuomorphismBottomSheetScaffold(
        peekHeight = 620.dp,
        showPullingBar = false,
        sheetContent = {
            Header()
            Spacer(modifier = Modifier.height(38.dp))
            Product()
            Spacer(modifier = Modifier.height(14.dp))
            Rating()
            Spacer(modifier = Modifier.height(38.dp))
            Comments()
            Spacer(modifier = Modifier.height(35.dp))
            val context = LocalContext.current
            SkeuomorphismButton(modifier = Modifier.padding(horizontal = 24.dp), label = "Submit") {
                showNotImplementedToast(context)
            }
        },
        content = {
            SkeuomorphismToolbar(onBackClicked = onBackClicked)
            SkeuomorphismTitle(title = "Rate your order")
        })
}

//endregion

//region Components

@Composable
private fun Header() {
    SkeuomorphismBottomSheetStep(2)
}

@Composable
private fun Product() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SkeuomorphismText(
            text = "Bottle Magic Tricks",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = SkeuomorphismContentPrimaryColor
        )
        Spacer(modifier = Modifier.height(14.dp))
        Image(
            painter = painterResource(id = R.drawable.skeuomorphism_ratingproduct),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(132.dp)
                .neu(
                    darkShadowColor = Color(0xFFB1C5D5),
                    lightShadowColor = Color(0xFFFFFFFF).copy(0.75f),
                    shape = Flat(RoundedCorner(30.dp)),
                    shadowElevation = 10.dp
                )
                .clip(RoundedCornerShape(30.dp))
        )
    }
}

@Composable
private fun Rating() {
    var note by remember { mutableStateOf(0) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            RatingStar(isSelected = note >= 1) {
                note = 1
            }
            RatingStar(isSelected = note >= 2) {
                note = 2
            }
            RatingStar(isSelected = note >= 3) {
                note = 3
            }
            RatingStar(isSelected = note >= 4) {
                note = 4
            }
            RatingStar(isSelected = note >= 5) {
                note = 5
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        SkeuomorphismText(
            text = "54 Reviews",
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = SkeuomorphismContentSecondaryColor
        )
    }
}

@Composable
private fun RatingStar(isSelected: Boolean, onClick: () -> Unit) {
    val color by animateColorAsState(
        targetValue = if (isSelected)
            SkeuomorphismMainColor
        else
            Color(0xFF6E81A0),
        animationSpec = skeuomorphismButtonAnimationSpec()
    )
    Icon(
        painter = painterResource(id = R.drawable.ic_skeuomorphism_star),
        contentDescription = null,
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        tint = color
    )
}

@Composable
private fun Comments() {
    var comments by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        val roundedCornerSize = 20.dp
        TextField(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(
                    color = Color(0xFFE3EDF7),
                    shape = RoundedCornerShape(roundedCornerSize)
                )
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
            value = comments,
            onValueChange = { if(it.length <= 200) comments = it },
            placeholder = {
                SkeuomorphismText(
                    text = "Add your thoughts here",
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = SkeuomorphismContentSecondaryColor
                )
            },
            textStyle = LocalTextStyle.current.copy(
                fontFamily = SkeuomorphismPrimaryFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = SkeuomorphismContentSecondaryColor
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = SkeuomorphismMainColor,
                focusedLabelColor = SkeuomorphismMainColor,
            ),
        )
        Spacer(modifier = Modifier.height(6.dp))
        SkeuomorphismText(
            modifier = Modifier.fillMaxWidth(),
            text = "${200 - comments.length} chars",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = SkeuomorphismContentSecondaryColor,
            textAlign = TextAlign.End
        )
    }
//    }
}

//endregion

//region Preview

@Preview
@Composable
private fun CommentsPreview() {
    Comments()
}

@Preview
@Composable
private fun RatingPreview() {
    Rating()
}

@Preview
@Composable
private fun SkeuomorphismProductPreview() {
    Product()
}

@Preview
@Composable
private fun SkeuomorphismScreen3Preview() {
    SkeuomorphismScreen3 {}
}

//endregion