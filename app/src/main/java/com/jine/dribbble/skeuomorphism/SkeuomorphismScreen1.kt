package com.jine.dribbble.skeuomorphism

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner
import com.jine.dribbble.R
import com.jine.dribbble.customClick
import com.jine.dribbble.showNotImplementedToast

//region Screen

@Composable
fun SkeuomorphismScreen1(onBackClicked: () -> Unit) {
    SkeuomorphismBottomSheetScaffold(
        peekHeight = 196.dp,
        sheetContent = { Wishlist() },
        sheetTitle = "Wishlist",
        content = {
            SkeuomorphismBottomSheetScaffold(
                peekHeight = 565.dp,
                sheetContent = { Shots() },
                sheetTitle = "Shots",
                content = {
                    Column(modifier = Modifier.fillMaxSize()) {
                        SkeuomorphismToolbar(onBackClicked = onBackClicked)
                        Header()
                    }
                }
            )
        })
}

//endregion

//region Components

@Composable
private fun Header(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            SkeuomorphismText(
                text = "Andrew Smith",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = SkeuomorphismContentPrimaryColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            SkeuomorphismText(
                text = "UI/UX Designer, Product Designer",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = SkeuomorphismContentSecondaryColor
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.skeuomorphism_profilepicture),
            contentDescription = null,
            modifier = Modifier
                .size(52.dp)
                .shadow(14.dp, shape = CircleShape)
                .clip(CircleShape)
        )
    }
}

@Composable
private fun Shots() {
    val context = LocalContext.current
    val modifier = Modifier
        .height(120.dp)
        .clip(RoundedCornerShape(24.dp))
        .clickable { showNotImplementedToast(context) }
    Column(Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
        Row(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.skeuomorphism_shot1),
                contentDescription = null,
                modifier = modifier.weight(2f),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.skeuomorphism_shot2),
                contentDescription = null,
                modifier = modifier.weight(1f),
                contentScale = ContentScale.FillWidth
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.skeuomorphism_shot3),
                contentDescription = null,
                modifier = modifier.weight(1f),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.skeuomorphism_shot4),
                contentDescription = null,
                modifier = modifier.weight(2f),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
private fun Wishlist() {
    val context = LocalContext.current
    val squareSize = 58.dp
    val roundedCornerSize = 12.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 10.dp)
    ) {
        //region images
        val images = listOf(
            R.drawable.skeuomorphism_wishlist1,
            R.drawable.skeuomorphism_wishlist2,
            R.drawable.skeuomorphism_wishlist3,
            R.drawable.skeuomorphism_wishlist4
        )
        for (image in images) {
            var isPressed by remember { mutableStateOf(false) }

            val elevation by animateDpAsState(
                targetValue = if (isPressed) 2.dp else 8.dp,
                animationSpec = skeuomorphismButtonAnimationSpec()
            )
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .customClick(
                        onClick = { showNotImplementedToast(context) },
                        onPress = { isPressed = true },
                        onRelease = { isPressed = false })
                    .neu(
                        lightShadowColor = Color(0xFFFFFFFF).copy(alpha = 0.72f),
                        darkShadowColor = Color(0xFFB0C3D2).copy(alpha = 0.72f),
                        shape = Flat(RoundedCorner(roundedCornerSize)),
                        shadowElevation = elevation
                    )
                    .size(squareSize)
                    .clip(RoundedCornerShape(roundedCornerSize))
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        //endregion
        //region "more" card
        var isPressed by remember { mutableStateOf(false) }

        val insideElevation by animateDpAsState(
            targetValue = if (isPressed) 20.dp else 10.dp,
            animationSpec = skeuomorphismButtonAnimationSpec()
        )
        Box(
            modifier = Modifier
                .customClick(
                    onClick = { showNotImplementedToast(context) },
                    onPress = { isPressed = true },
                    onRelease = { isPressed = false })
                .size(squareSize)
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
                    shadowElevation = insideElevation
                ),
            contentAlignment = Alignment.Center
        ) {
            SkeuomorphismText(
                text = "7+",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF6E81A0)
            )
        }
        //endregion
    }
}

//endregion

//region Preview

@Preview
@Composable
private fun WishlistPreview() {
    Wishlist()
}

@Preview
@Composable
private fun ShotsPreview() {
    Shots()
}

@Preview
@Composable
private fun HeaderPreview() {
    Header()
}

@Preview
@Composable
private fun SkeuomorphismScreen1Preview() {
    SkeuomorphismScreen1 {}
}

//endregion