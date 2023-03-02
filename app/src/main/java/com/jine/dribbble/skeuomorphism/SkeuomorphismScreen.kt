package com.jine.dribbble.skeuomorphism

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.RoundedCorner
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R
import com.jine.dribbble.customClick
import com.jine.dribbble.showNotImplementedToast

//region Resources

val SkeuomorphismMainColor = Color(0xFFFEAAA0)
val SkeuomorphismBackgroundColor = Color(0xFFE4F0FA)
val SkeuomorphismContentPrimaryColor = Color(0xFF334669)
val SkeuomorphismContentSecondaryColor = SkeuomorphismContentPrimaryColor.copy(alpha = 0.6f)
val SkeuomorphismPrimaryFontFamily = FontFamily(
    Font(R.font.lato_regular, FontWeight.Normal),
    Font(R.font.lato_bold, FontWeight.Bold)
)
val SkeuomorphismSecondaryFontFamily = FontFamily(
    Font(R.font.mulish_black, FontWeight.Black),
    Font(R.font.mulish_bold, FontWeight.Bold)
)
val SkeuomorphismGradient =
    Brush.linearGradient(colors = listOf(Color(0xFFCD8A9A), SkeuomorphismMainColor))

//endregion

//region Screen

@Composable
fun SkeuomorphismScreen() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(SkeuomorphismBackgroundColor)
    var screenIndex by remember {
        mutableStateOf(0)
    }
    when (screenIndex) {
        0 ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(SkeuomorphismBackgroundColor)
                    .padding(horizontal = 32.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                val context = LocalContext.current
                SkeuomorphismButton(label = "Screen 1") {
                    screenIndex = 1
                }
                SkeuomorphismButton(label = "Screen 2") {
                    screenIndex = 2
                }
                SkeuomorphismButton(label = "Screen 3") {
                    screenIndex = 3
                }
                SkeuomorphismButton(label = "Screen 4") {
                    screenIndex = 4
                }
                SkeuomorphismButton(label = "Screen 5") {
                    screenIndex = 5
                }
            }
        1 -> SkeuomorphismScreen1 {
            screenIndex = 0
        }
        2 -> SkeuomorphismScreen2 {
            screenIndex = 0
        }
        3 -> SkeuomorphismScreen3 {
            screenIndex = 0
        }
        4 -> SkeuomorphismScreen4 {
            screenIndex = 0
        }
        5 -> SkeuomorphismScreen5 {
            screenIndex = 0
        }
    }
}

//endregion

//region Components

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SkeuomorphismBottomSheetScaffold(
    peekHeight: Dp,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    sheetTitle: String? = null,
    showPullingBar: Boolean = true
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            SkeuomorphismBottomSheet(
                sheetTitle = sheetTitle,
                content = sheetContent,
                showPullingBar = showPullingBar
            )
        },
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetPeekHeight = peekHeight,
        sheetBackgroundColor = SkeuomorphismBackgroundColor,
        backgroundColor = SkeuomorphismBackgroundColor,
        content = content
    )
}

@Composable
private fun SkeuomorphismBottomSheet(
    sheetTitle: String? = null,
    showPullingBar: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier
                .height(2.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFE5EFFA),
                            Color.White,
                            Color(0xFFE5EFFA)
                        )
                    )
                )
        )
        if (showPullingBar) {
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(3.dp)
                    .background(
                        color = Color(0xFF6E81A0).copy(0.24f),
                        shape = RoundedCornerShape(3.dp)
                    )
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
        if (sheetTitle != null) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SkeuomorphismText(
                    text = sheetTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = SkeuomorphismContentPrimaryColor
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_skeuomorphism_menu),
                        contentDescription = "Menu",
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFF7A8AA3)
                    )
                }
            }
        } else {
            Spacer(modifier = Modifier.height(8.dp))
        }
        content()
    }
}

@Composable
fun SkeuomorphismBottomSheetStep(number: Int) {
    val unfocusedBrush = Brush.Companion.linearGradient(
        listOf(
            Color(0xFF6E81A0).copy(alpha = 0.24f),
            Color(0xFF6E81A0).copy(alpha = 0.24f)
        )
    )
    val focusedBrush = Brush.Companion.linearGradient(
        listOf(
            Color(0xFFFEAAA0),
            Color(0xFFCD8A9A)
        )
    )
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Spacer(modifier = Modifier.height(10.dp))
        SkeuomorphismText(
            text = number.toString(),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = SkeuomorphismContentPrimaryColor.copy(0.3f)
        )
        Spacer(modifier = Modifier.height(7.dp))
        Row {
            Box(
                modifier = Modifier
                    .width(64.dp)
                    .height(3.dp)
                    .background(
                        brush = if (number == 1) focusedBrush else unfocusedBrush,
                        shape = RoundedCornerShape(3.dp)
                    )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(3.dp)
                    .background(
                        brush = if (number == 2) focusedBrush else unfocusedBrush,
                        shape = RoundedCornerShape(3.dp)
                    )
            )
        }
    }
}

@Composable
fun SkeuomorphismText(
    modifier: Modifier = Modifier,
    text: String,
    fontWeight: FontWeight,
    fontSize: TextUnit,
    color: Color,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        fontFamily = SkeuomorphismPrimaryFontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign
    )
}

fun <T> skeuomorphismButtonAnimationSpec() = tween<T>(150, easing = LinearEasing)

@Composable
fun SkeuomorphismButton(
    modifier: Modifier = Modifier,
    label: String,
    buttonWidth: Dp = LocalConfiguration.current.screenWidthDp.dp,
    buttonHeight: Dp = 50.dp,
    buttonCornerRadius: Dp = 20.dp,
    onClick: () -> Unit
) {
    //region Animation
    val lightColor = Color(0xFFFFFFFF).copy(alpha = 0.72f)
    val darkColor = Color(0xFFB0C3D2).copy(alpha = 0.72f)

    var isPressed by remember { mutableStateOf(false) }

    val outerButtonElevation by animateDpAsState(
        targetValue = if (isPressed) 4.dp else 20.dp,
        animationSpec = skeuomorphismButtonAnimationSpec()
    )
    val innerButtonElevation by animateDpAsState(
        targetValue = if (isPressed) 50.dp else 100.dp,
        animationSpec = skeuomorphismButtonAnimationSpec()
    )
    //endregion

    //region Global container
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        //region Border around the button
        Box(
            modifier = Modifier
                .height(buttonHeight)
                .width(buttonWidth)
                .neu(
                    lightShadowColor = lightColor,
                    darkShadowColor = darkColor,
                    shape = Flat(RoundedCorner(buttonCornerRadius)),
                    shadowElevation = outerButtonElevation
                )
        )
        //endregion
        //region Button
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(buttonWidth)
                .clip(RoundedCornerShape(buttonCornerRadius))
                .customClick(
                    onClick = onClick,
                    onPress = { isPressed = true },
                    onRelease = { isPressed = false })
                .background(color = Color(0xFFE8F3FB))
                .neu(
                    lightShadowColor = darkColor,
                    darkShadowColor = lightColor,
                    shadowElevation = innerButtonElevation
                )
                .padding(horizontal = 18.dp),
            contentAlignment = Alignment.Center
        ) {
            SkeuomorphismText(
                text = label,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = Color(0xFF2E476E)
            )
        }
        //endregion
    }
    //endregion
}

// Include a 10.dp padding
@Composable
private fun SkeuomorphismToolbarButton(
    modifier: Modifier = Modifier,
    iconId: Int,
    contentDescription: String,
    notificationCount: Int = 0,
    onClick: () -> Unit
) {
    //region Animation
    val lightColor = Color(0xFFFFFFFF).copy(alpha = 0.72f)
    val darkColor = Color(0xFFB0C3D2).copy(alpha = 0.72f)

    var isPressed by remember { mutableStateOf(false) }

    val outerButtonElevation by animateDpAsState(
        targetValue = if (isPressed) 4.dp else 20.dp,
        animationSpec = skeuomorphismButtonAnimationSpec()
    )
    val innerButtonElevation by animateDpAsState(
        targetValue = if (isPressed) 50.dp else 100.dp,
        animationSpec = skeuomorphismButtonAnimationSpec()
    )
    //endregion

    //region Global container
    Box(modifier = modifier.padding(10.dp), contentAlignment = Alignment.Center) {
        //region Border around the button
        Box(
            modifier = Modifier
                .size(width = if (notificationCount > 0) 60.dp else 35.dp, height = 35.dp)
                .neu(
                    lightShadowColor = lightColor,
                    darkShadowColor = darkColor,
                    shape = Flat(RoundedCorner(35.dp)),
                    shadowElevation = outerButtonElevation
                )
        )
        //endregion
        //region Button
        ConstraintLayout(
            modifier = Modifier
                .size(width = if (notificationCount > 0) 60.dp else 35.dp, height = 35.dp)
                .clip(CircleShape)
                .customClick(
                    onClick = onClick,
                    onPress = { isPressed = true },
                    onRelease = { isPressed = false })
                .background(color = Color(0xFFE8F3FB))
                .neu(
                    lightShadowColor = darkColor,
                    darkShadowColor = lightColor,
                    shadowElevation = innerButtonElevation
                )
        ) {
            val (icon, badge) = createRefs()
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = contentDescription,
                tint = Color(0xFF6E81A0),
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(icon) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
            )
            if (notificationCount > 0) {
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .background(color = SkeuomorphismBackgroundColor, shape = CircleShape)
                        .constrainAs(badge) {
                            top.linkTo(icon.top)
                            start.linkTo(icon.start, margin = 16.dp)
                        }, contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = SkeuomorphismGradient,
                                shape = CircleShape,
                                alpha = 0.8f
                            )
                    )
                    Text(
                        text = "$notificationCount",
                        fontFamily = SkeuomorphismSecondaryFontFamily,
                        fontWeight = FontWeight.Black,
                        fontSize = 10.sp,
                        color = Color.White
                    )
                }
            }
        }
        //endregion
    }
    //endregion
}

@Composable
fun SkeuomorphismToolbar(modifier: Modifier = Modifier, onBackClicked: () -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(14.dp))
        SkeuomorphismToolbarButton(
            iconId = R.drawable.ic_skeuomorphism_back,
            contentDescription = "Back",
            onClick = onBackClicked
        )
        Spacer(modifier = Modifier.weight(1f))
        SkeuomorphismToolbarButton(
            iconId = R.drawable.ic_skeuomorphism_notification,
            contentDescription = "Notifications"
        ) {
            showNotImplementedToast(context)
        }
        SkeuomorphismToolbarButton(
            iconId = R.drawable.ic_skeuomorphism_cart,
            contentDescription = "Cart",
            notificationCount = 3
        ) {
            showNotImplementedToast(context)
        }
        Spacer(modifier = Modifier.width(14.dp))
    }
}

@Composable
fun SkeuomorphismSkippableToolbar(modifier: Modifier = Modifier, onBackClicked: () -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(14.dp))
        SkeuomorphismToolbarButton(
            iconId = R.drawable.ic_skeuomorphism_back,
            contentDescription = "Back",
            onClick = onBackClicked
        )
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = { showNotImplementedToast(context) }) {
            SkeuomorphismText(
                text = "Skip",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = SkeuomorphismContentSecondaryColor
            )
        }
        Spacer(modifier = Modifier.width(14.dp))
    }
}

@Composable
fun SkeuomorphismTitle(title: String) {
    SkeuomorphismText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        color = SkeuomorphismContentPrimaryColor,
        textAlign = TextAlign.Center
    )
}

//endregion

//region Previews

@Preview
@Composable
private fun SkeuomorphismBottomSheetStepPreview() {
    SkeuomorphismBottomSheetStep(2)
}

@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
@Composable
private fun SkeuomorphismTitlePreview() {
    SkeuomorphismTitle(title = "Title")
}

@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
@Composable
private fun SkeuomorphismBottomSheetPreview() {
    SkeuomorphismBottomSheet(sheetTitle = "BottomSheet") {}
}

@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
@Composable
private fun SkeuomorphismToolbarPreview() {
    SkeuomorphismToolbar {
    }
}

@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
@Composable
private fun SkeuomorphismSkippableToolbarPreview() {
    SkeuomorphismSkippableToolbar {}
}

@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
@Composable
private fun SkeuomorphismToolbarButtonPreview() {
    SkeuomorphismToolbarButton(
        modifier = Modifier,
        iconId = R.drawable.ic_skeuomorphism_back,
        ""
    ) {}
}

@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
@Composable
private fun SkeuomorphismToolbarButtonPreview2() {
    SkeuomorphismToolbarButton(
        modifier = Modifier,
        iconId = R.drawable.ic_skeuomorphism_cart,
        "",
        notificationCount = 3
    ) {}
}

@Preview(backgroundColor = 0xFFE4F0FA, showBackground = true)
@Composable
private fun SkeuomorphismButtonPreview() {
    SkeuomorphismButton(label = "Button", buttonWidth = 180.dp) {}
}

@Preview
@Composable
private fun SkeuomorphismScreenPreview() {
    SkeuomorphismScreen()
}

//endregion