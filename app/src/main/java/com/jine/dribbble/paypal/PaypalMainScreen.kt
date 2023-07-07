package com.jine.dribbble.paypal

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

const val DESTINATION_PAYPAL_HOME = "paypal_home"
const val DESTINATION_PAYPAL_CONTACTS = "paypal_contacts"
const val DESTINATION_PAYPAL_WALLET = "paypal_wallet"
const val DESTINATION_PAYPAL_ACTIVITIES = "paypal_activities"

@Composable
fun PaypalMainScreen(parentNavController: NavController) {
    val navController = rememberNavController()
    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            navController = navController,
            startDestination = DESTINATION_PAYPAL_HOME
        ) {
            composable(DESTINATION_PAYPAL_HOME) { PaypalHomeScreen(parentNavController, navController) }
            composable(DESTINATION_PAYPAL_CONTACTS) { PaypalContactsScreen() }
            composable(DESTINATION_PAYPAL_WALLET) { PaypalWalletScreen() }
            composable(DESTINATION_PAYPAL_ACTIVITIES) { PaypalActivityScreen(navController)}
        }
        BottomBar(navController)
    }
}

@Composable
private fun BottomBar(navController: NavController) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .shadow(
                46.dp,
                shape = RoundedCornerShape(topStart = 40.dp),
                spotColor = PaypalPrimaryDarkColor
            )
            .background(PaypalWhiteColor, shape = RoundedCornerShape(topStart = 40.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        var itemSelected by remember { mutableStateOf("Home") }
        BottomBarItem(
            label = "Home",
            iconRes = R.drawable.ic_paypal_home,
            isSelected = itemSelected == "Home",
            onSelect = {
                itemSelected = "Home"
                navController.navigate(DESTINATION_PAYPAL_HOME)
            }
        )
        BottomBarItem(
            label = "Contacts",
            iconRes = R.drawable.ic_paypal_users,
            isSelected = itemSelected == "Contacts",
            onSelect = {
                itemSelected = "Contacts"
                navController.navigate(DESTINATION_PAYPAL_CONTACTS)
            }
        )
        BottomBarItem(
            label = "Wallet",
            iconRes = R.drawable.ic_paypal_wallet,
            isSelected = itemSelected == "Wallet",
            onSelect = {
                itemSelected = "Wallet"
                navController.navigate(DESTINATION_PAYPAL_WALLET)
            }
        )
        BottomBarItem(
            label = "Settings",
            iconRes = R.drawable.ic_paypal_setting,
            isSelected = itemSelected == "Settings",
            onSelect = {
                showNotImplementedToast(context)
            }
        )
    }
}

@Composable
private fun BottomBarItem(
    label: String,
    iconRes: Int,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onSelect)
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = if (isSelected) PaypalPrimaryColor else PaypalBlackColor
        )
        AnimatedVisibility(visible = isSelected) {
            PaypalSmallText(
                modifier = Modifier.padding(start = 8.dp),
                text = label,
                color = PaypalPrimaryShadeColor
            )
        }
    }
}

@Preview
@Composable
private fun PaypalMainPreview() {
    PaypalMainScreen(rememberNavController())
}

