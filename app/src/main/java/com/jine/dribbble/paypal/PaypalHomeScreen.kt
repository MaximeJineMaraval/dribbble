package com.jine.dribbble.paypal

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R
import com.jine.dribbble.customClick
import com.jine.dribbble.showNotImplementedToast

@Composable
fun PaypalHomeScreen(parentNavController: NavController, navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = PaypalPrimaryDarkColor)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PaypalWhiteColor)
    ) {
        Header()
        Spacer(modifier = Modifier.height(24.dp))
        Actions(onSendMoneyClicked = { parentNavController.navigate(DESTINATION_PAYPAL_SENDMONEY)})
        Spacer(modifier = Modifier.height(38.dp))
        Activity(navController)
    }
}

@Composable
private fun Header() {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 36.dp,
                spotColor = PaypalPrimaryDarkColor,
                shape = RoundedCornerShape(bottomEnd = 40.dp)
            )
            .background(
                PaypalGradient,
                shape = RoundedCornerShape(bottomEnd = 40.dp)
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.paypal_home_bg),
            contentDescription = "",
            colorFilter = ColorFilter.tint(PaypalWhiteColor.copy(alpha = 0.15f))
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(36.dp),
                    painter = painterResource(id = R.drawable.paypal_logo_p),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier
                        .size(52.dp)
                        .border(2.dp, PaypalPrimaryColor, RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp)),
                    painter = painterResource(id = R.drawable.paypal_user_pic),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            PaypalRegularText(text = "Hello, Vadim!", color = PaypalWhiteColor.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "\$ 272.30",
                fontFamily = PaypalFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 40.sp,
                color = PaypalWhiteColor
            )
            Spacer(modifier = Modifier.height(2.dp))
            PaypalRegularText(text = "Your Balance", color = PaypalWhiteColor)
            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
private fun Actions(onSendMoneyClicked: () -> Unit) {
    val context = LocalContext.current
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(32.dp))
        ActionCard(
            weight = 1f,
            iconRes = R.drawable.ic_paypal_upload,
            label = "Send\nMoney",
            showLabel = true,
            backgroundColor = PaypalGradient,
            contentColor = PaypalWhiteColor,
            shadowColor = PaypalPrimaryDarkColor,
            onClick = onSendMoneyClicked
        )
        Spacer(modifier = Modifier.width(16.dp))
        ActionCard(
            weight = 1f,
            iconRes = R.drawable.ic_paypal_download,
            label = "Request\nPayment",
            showLabel = true,
            backgroundColor = Brush.linearGradient(listOf(PaypalWhiteColor, PaypalWhiteColor)),
            contentColor = PaypalPrimaryShadeColor,
            shadowColor = PaypalPrimaryDarkColor.copy(alpha = 0.2f),
            onClick = { showNotImplementedToast(context) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        ActionCard(
            weight = 0.5f,
            iconRes = R.drawable.ic_paypal_option,
            label = "Options",
            showLabel = false,
            backgroundColor = Brush.linearGradient(listOf(PaypalWhiteColor, PaypalWhiteColor)),
            contentColor = PaypalBlackColor.copy(alpha = 0.5f),
            shadowColor = PaypalPrimaryDarkColor.copy(alpha = 0.2f),
            onClick = { showNotImplementedToast(context) }
        )
        Spacer(modifier = Modifier.width(32.dp))
    }
}

@Composable
private fun RowScope.ActionCard(
    weight: Float,
    iconRes: Int,
    label: String,
    showLabel: Boolean,
    backgroundColor: Brush,
    contentColor: Color,
    shadowColor: Color,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 8.dp else 14.dp,
        animationSpec = paypalAnimationSpec()
    )
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.99f else 1f,
        animationSpec = paypalAnimationSpec()
    )
    Column(
        modifier = Modifier
            .scale(scale)
            .customClick(
                onClick = onClick,
                onPress = { isPressed = true },
                onRelease = { isPressed = false })
            .shadow(
                elevation = elevation,
                spotColor = shadowColor,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                brush = backgroundColor,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 20.dp)
            .weight(weight)
            .height(120.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = contentColor
        )
        if (showLabel) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Send \n" +
                        "Money",
                fontFamily = PaypalFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = contentColor,
            )
        }
    }
}

@Composable
private fun Activity(navController: NavController) {
    val items = PaypalActivityModel.getData().take(3)
    Column(modifier = Modifier.padding(horizontal = 32.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            PaypalBoldText(text = "Activity")
            Spacer(modifier = Modifier.weight(1f))
            PaypalSmallText(
                text = "View all",
                color = PaypalBlackColor.copy(alpha = 0.5f),
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable {
                        navController.navigate(
                            DESTINATION_PAYPAL_ACTIVITIES
                        )
                    })
        }
        Spacer(modifier = Modifier.height(16.dp))
        for (item in items) {
            PaypalActivityRow(model = item)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
private fun PaypalHomeScreenPreview() {
    PaypalHomeScreen(rememberNavController(), rememberNavController())
}
