package com.jine.dribbble.paypal

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

//region Assets

val PaypalGradient = Brush.radialGradient(
    colors = listOf(Color(0xFF0070BA), Color(0xFF1546A0)),
    center = Offset.Zero,
    tileMode = TileMode.Clamp
)
val PaypalPrimaryColor = Color(0xFF0070BA)
val PaypalPrimaryShadeColor = Color(0xFF005EA6)
val PaypalPrimaryDarkColor = Color(0xFF1546A0)
val PaypalGrayColor = Color(0xFFF5F7FA)
val PaypalWhiteColor = Color(0xFFFFFFFF)
val PaypalBlackColor = Color(0xFF243656)
val PaypalBlackTransparentColor = Color(0xFF929BAB)
val PaypalGreenColor = Color(0xFF37D39B)
val PaypalRedColor = Color(0xFFF47090)
val PaypalFontFamily = FontFamily(
    Font(R.font.manrope_regular, FontWeight.Normal),
    Font(R.font.manrope_semibold, FontWeight.SemiBold),
    Font(R.font.manrope_bold, FontWeight.Bold),
)

fun <T> paypalAnimationSpec() = tween<T>(durationMillis = 300)

//endregion

@Composable
fun PaypalApp() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = DESTINATION_PAYPAL_LOGIN
    ) {
        composable(DESTINATION_PAYPAL_LOGIN) { PaypalLoginScreen(navController) }
        composable(DESTINATION_PAYPAL_MAIN) { PaypalMainScreen(navController) }
        composable(DESTINATION_PAYPAL_SENDMONEY) { PaypalSendMoneyScreen(navController) }
    }
}

const val DESTINATION_PAYPAL_LOGIN = "paypal_login"
const val DESTINATION_PAYPAL_MAIN = "paypal_main"
const val DESTINATION_PAYPAL_SENDMONEY = "paypal_sendmoney"

//region Texts

@Composable
fun PaypalSmallText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = PaypalBlackColor,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        fontFamily = PaypalFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun PaypalRegularText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = PaypalBlackColor,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        fontFamily = PaypalFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = color,
        textAlign = textAlign,

        )
}

@Composable
fun PaypalBoldText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = PaypalBlackColor,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        fontFamily = PaypalFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = color,
        textAlign = textAlign
    )
}

//endregion

//region Buttons

@Composable
fun PaypalTextButton(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.textButtonColors(contentColor = PaypalBlackColor)
    ) {
        PaypalSmallText(text = text)
    }
}

@Composable
fun PaypalButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 24.dp, pressedElevation = 18.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = PaypalGradient, shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            PaypalBoldText(text = text, color = PaypalWhiteColor)
        }
    }
}

//endregion

@Composable
fun PaypalToolbar(navController: NavController?, title: String, option: Pair<Int, String>? = null, isBlue: Boolean = false) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = if(isBlue) PaypalPrimaryDarkColor else PaypalWhiteColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(12.dp))
        if (navController?.previousBackStackEntry != null) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_paypal_back),
                    contentDescription = "back",
                    tint = if(isBlue) PaypalWhiteColor else PaypalBlackColor
                )
            }
        } else {
            Spacer(modifier = Modifier.width(36.dp))
        }
        PaypalBoldText(
            modifier = Modifier.weight(1f),
            text = title,
            textAlign = TextAlign.Center,
            color = if(isBlue) PaypalWhiteColor else PaypalBlackColor
        )
        if (option != null) {
            IconButton(onClick = { showNotImplementedToast(context) }) {
                Icon(
                    painter = painterResource(id = option.first),
                    contentDescription = option.second,
                    tint = if(isBlue) PaypalWhiteColor else PaypalBlackColor
                )
            }
        } else {
            Spacer(modifier = Modifier.width(36.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
    }
}