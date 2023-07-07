package com.jine.dribbble.paypal

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R
import com.jine.dribbble.customClick
import com.jine.dribbble.showNotImplementedToast
import java.util.Calendar
import java.util.Date

@Composable
fun PaypalActivityScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = PaypalWhiteColor)
    var items by remember { mutableStateOf(PaypalActivityModel.getData()) }
    Column(modifier = Modifier.fillMaxSize()) {
        PaypalToolbar(
            navController = navController,
            "Activity",
            option = Pair(R.drawable.ic_paypal_search, "search")
        )
        Spacer(modifier = Modifier.height(24.dp))
        Tabs(
            onAllSelected = { items = PaypalActivityModel.getData() },
            onIncomeSelected = { items = PaypalActivityModel.getData().filter { it.isPositive } },
            onOutcomeSelected = {
                items = PaypalActivityModel.getData().filter { it.isPositive.not() }
            })
        List(items)
    }
}

@Composable
private fun Tabs(
    onAllSelected: () -> Unit,
    onIncomeSelected: () -> Unit,
    onOutcomeSelected: () -> Unit
) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("All", "Income", "Outcome")
    val tabWidth = ((LocalConfiguration.current.screenWidthDp - (32 * 2)) / 3).dp
    val indicatorOffset by animateDpAsState(
        targetValue = tabWidth * tabIndex,
        animationSpec = paypalAnimationSpec()
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(PaypalGrayColor)
            .height(48.dp)
    ) {
        // Indicator
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(tabWidth)
                .padding(2.dp)
                .offset(indicatorOffset)
                .background(
                    PaypalPrimaryShadeColor, shape = RoundedCornerShape(10.dp)
                )
        )
        // Tabs
        Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
            tabs.forEachIndexed { index, label ->
                val isSelected = tabIndex == index
                val textColor by animateColorAsState(
                    targetValue = if (isSelected) PaypalWhiteColor else PaypalBlackColor.copy(alpha = 0.5f),
                    animationSpec = paypalAnimationSpec()
                )
                PaypalRegularText(
                    modifier = Modifier
                        .width(tabWidth)
                        .customClick(onClick = {
                            tabIndex = index
                            when (index) {
                                0 -> onAllSelected()
                                1 -> onIncomeSelected()
                                2 -> onOutcomeSelected()
                            }
                        }, onPress = {}, onRelease = {}),
                    text = label,
                    color = textColor,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
private fun List(items: List<PaypalActivityModel>) {
    val today = Date()
    val todayItems = items.filter { it.date.day == today.day }
    val otherItems = items.filter { it.date.day != today.day }
    LazyColumn(contentPadding = PaddingValues(vertical = 24.dp, horizontal = 32.dp), content = {
        item {
            PaypalSmallText(text = "Today", color = PaypalBlackColor.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(todayItems) {
            PaypalActivityRow(model = it)
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            PaypalSmallText(text = "Yesterday", color = PaypalBlackColor.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(otherItems) {
            PaypalActivityRow(model = it)
            Spacer(modifier = Modifier.height(8.dp))
        }
    })
}

@Composable
fun PaypalActivityRow(model: PaypalActivityModel) {
    val context = LocalContext.current
    var isPressed by remember { mutableStateOf(false) }
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 8.dp else 14.dp,
        animationSpec = paypalAnimationSpec()
    )
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.99f else 1f,
        animationSpec = paypalAnimationSpec()
    )
    Row(
        modifier = Modifier
            .scale(scale)
            .fillMaxWidth()
            .customClick(
                onClick = { showNotImplementedToast(context) },
                onPress = { isPressed = true },
                onRelease = { isPressed = false })
            .shadow(
                elevation = elevation,
                spotColor = PaypalPrimaryDarkColor.copy(alpha = 0.2f),
                shape = RoundedCornerShape(20.dp)
            )
            .background(PaypalWhiteColor, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(PaypalGrayColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = model.label.first().uppercase(),
                fontFamily = PaypalFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = PaypalBlackColor,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            PaypalRegularText(text = model.label)
            PaypalSmallText(text = model.dateLabel, color = PaypalBlackColor.copy(alpha = 0.5f))
        }
        Spacer(modifier = Modifier.weight(1f))
        val operator = if (model.isPositive) "+" else "-"
        val priceLabel = if (model.price == model.price.toInt().toFloat()) {
            model.price.toInt().toString()
        } else {
            model.price.toString()
        }
        Text(
            text = "$operator\$$priceLabel",
            fontFamily = PaypalFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = if (model.isPositive) PaypalGreenColor else PaypalRedColor,
        )
    }
}

//region data

data class PaypalActivityModel(
    val label: String,
    val date: Date,
    val dateLabel: String,
    val price: Float,
    val isPositive: Boolean
) {
    companion object {
        fun getData(): List<PaypalActivityModel> {
            return listOf(
                PaypalActivityModel(
                    label = "Mike Rine",
                    date = Date(),
                    dateLabel = "1 minute ago",
                    price = 250f,
                    isPositive = true
                ),
                PaypalActivityModel(
                    label = "Google Drive",
                    date = Date(),
                    dateLabel = "2 hours ago",
                    price = 138.5f,
                    isPositive = false
                ),
                PaypalActivityModel(
                    label = "Casey Smith",
                    date = Date(),
                    dateLabel = "9 hours ago",
                    price = 531f,
                    isPositive = true
                ),
                PaypalActivityModel(
                    label = "Apple Store",
                    date = yesterday(),
                    dateLabel = "Yesterday at 11:45 AM",
                    price = 250f,
                    isPositive = false
                ),
                PaypalActivityModel(
                    label = "Pizza Delivery",
                    date = yesterday(),
                    dateLabel = "Yesterday at 2:30 PM",
                    price = 58.9f,
                    isPositive = false
                ),
                PaypalActivityModel(
                    label = "Amazon.com",
                    date = yesterday(),
                    dateLabel = "Yesterday at 6:28 PM",
                    price = 300f,
                    isPositive = false
                ),
                PaypalActivityModel(
                    label = "Apple Store",
                    date = yesterday(),
                    dateLabel = "Yesterday at 11:45 AM",
                    price = 250f,
                    isPositive = false
                ),
                PaypalActivityModel(
                    label = "Pizza Delivery",
                    date = yesterday(),
                    dateLabel = "Yesterday at 2:30 PM",
                    price = 58.9f,
                    isPositive = false
                ),
                PaypalActivityModel(
                    label = "Amazon.com",
                    date = yesterday(),
                    dateLabel = "Yesterday at 6:28 PM",
                    price = 300f,
                    isPositive = false
                ),
                PaypalActivityModel(
                    label = "Apple Store",
                    date = yesterday(),
                    dateLabel = "Yesterday at 11:45 AM",
                    price = 250f,
                    isPositive = true
                ),
                PaypalActivityModel(
                    label = "Pizza Delivery",
                    date = yesterday(),
                    dateLabel = "Yesterday at 2:30 PM",
                    price = 58.9f,
                    isPositive = false
                ),
                PaypalActivityModel(
                    label = "Amazon.com",
                    date = yesterday(),
                    dateLabel = "Yesterday at 6:28 PM",
                    price = 300f,
                    isPositive = false
                )
            )
        }
    }
}

private fun yesterday(): Date {
    val cal: Calendar = Calendar.getInstance()
    cal.add(Calendar.DATE, -1)
    return cal.time
}

//endregion