package com.jine.dribbble.paypal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

@Composable
fun PaypalWalletScreen() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = PaypalPrimaryDarkColor)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PaypalWhiteColor)
    ) {
        Header()
        Spacer(modifier = Modifier.height(32.dp))
        PersonalInfo()
        Spacer(modifier = Modifier.height(54.dp))
        BankingCard()
    }
}

@Composable
private fun Header() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        PaypalToolbar(
            navController = null,
            title = "Your wallet",
            option = Pair(R.drawable.ic_paypal_edit, "Edit"),
            isBlue = true
        )
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(PaypalPrimaryDarkColor)
            )
            Image(
                modifier = Modifier
                    .size(155.dp)
                    .padding(top = 8.dp),
                painter = painterResource(id = R.drawable.ic_paypal_pp),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun PersonalInfo() {
    Column(Modifier.padding(horizontal = 32.dp)) {
        PaypalSmallText(text = "Personal info", color = PaypalBlackColor.copy(alpha = 0.5f))
        Spacer(modifier = Modifier.height(18.dp))
        Row() {
            PaypalRegularText(text = "Name", modifier = Modifier.width(75.dp))
            Spacer(modifier = Modifier.width(8.dp))
            PaypalBoldText(text = "Vadim Demenko")
        }
        Spacer(modifier = Modifier.height(18.dp))
        Row() {
            PaypalRegularText(text = "E-mail", modifier = Modifier.width(75.dp))
            Spacer(modifier = Modifier.width(8.dp))
            PaypalBoldText(text = "vadikforz@gmail.com")
        }
        Spacer(modifier = Modifier.height(18.dp))
        Row() {
            PaypalRegularText(text = "Phone", modifier = Modifier.width(75.dp))
            Spacer(modifier = Modifier.width(8.dp))
            PaypalBoldText(text = "+4 1782 049 294")
        }
    }
}

@Composable
private fun BankingCard() {
    val context = LocalContext.current
    Column(Modifier.padding(horizontal = 32.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            PaypalSmallText(text = "My banking cards", color = PaypalBlackColor.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = { showNotImplementedToast(context) }) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_paypal_plus),
                    contentDescription = null,
                    tint = PaypalBlackColor.copy(alpha = 0.5f)
                )
                Spacer(modifier = Modifier.width(6.dp))
                PaypalSmallText(text = "Add", color = PaypalBlackColor.copy(alpha = 0.5f))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .shadow(
                    elevation = 8.dp,
                    spotColor = PaypalPrimaryColor.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(20.dp)
                )
                .fillMaxWidth()
                .height(72.dp)
                .background(PaypalWhiteColor, shape = RoundedCornerShape(20.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.paypal_visa),
                contentDescription = null,
                modifier = Modifier.width(34.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                PaypalRegularText(text = "Visa")
                PaypalSmallText(
                    text = "4*** **** ****2 4746",
                    color = PaypalBlackColor.copy(alpha = 0.5f)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .shadow(
                    elevation = 16.dp,
                    spotColor = PaypalPrimaryColor.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(20.dp)
                )
                .fillMaxWidth()
                .height(72.dp)
                .background(PaypalWhiteColor, shape = RoundedCornerShape(20.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.paypal_mastercard),
                contentDescription = null,
                modifier = Modifier.width(34.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                PaypalRegularText(text = "MasterCard")
                PaypalSmallText(
                    text = "4*** **** ****3 5236",
                    color = PaypalBlackColor.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PaypalWalletScreen()
}