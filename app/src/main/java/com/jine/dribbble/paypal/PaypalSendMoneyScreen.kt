package com.jine.dribbble.paypal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

@Composable
fun PaypalSendMoneyScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = PaypalWhiteColor)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        var amount by remember { mutableStateOf("") }
        PaypalToolbar(navController = navController, title = "Send Money")
        Spacer(modifier = Modifier.height(24.dp))
        ToCell()
        Spacer(modifier = Modifier.height(36.dp))
        AmountInput(amount, modifier = Modifier.padding(horizontal = 32.dp))
        Spacer(modifier = Modifier.height(48.dp))
        Keyboard(onChar = { char -> amount += char }, onDelete = { amount = amount.dropLast(1) })
        Spacer(modifier = Modifier.height(52.dp))
        PaypalButton(
            text = "Send",
            modifier = Modifier.padding(horizontal = 32.dp)
        ) { showNotImplementedToast(context = context) }
    }
}

@Composable
private fun ToCell() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 56.dp, height = 58.dp)
                .border(width = 2.dp, color = PaypalGrayColor, shape = RoundedCornerShape(22.dp)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(width = 48.dp, height = 50.dp)
                    .background(color = PaypalGrayColor, shape = RoundedCornerShape(18.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "A",
                    fontFamily = PaypalFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column() {
            PaypalSmallText(text = "To:", color = PaypalBlackColor.copy(alpha = 0.5f))
            PaypalRegularText(text = "Ann Nielsen")
            PaypalSmallText(
                text = "nielsen.ann@gmail.com",
                color = PaypalBlackColor.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
private fun AmountInput(input: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .border(width = 2.dp, color = PaypalPrimaryColor, shape = RoundedCornerShape(20.dp))
            .shadow(4.dp, shape = RoundedCornerShape(20.dp), spotColor = PaypalPrimaryColor)
            .background(color = PaypalWhiteColor),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "$ $input",
            color = PaypalBlackColor,
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = PaypalFontFamily
        )
    }
}

@Composable
private fun Keyboard(onChar: (char: Char) -> Unit, onDelete: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
            KeyboardItem(model = KeyboardItemModel.CharItem('1'), onClick = { onChar('1') })
            Spacer(modifier = Modifier.weight(1f))
            KeyboardItem(model = KeyboardItemModel.CharItem('2'), onClick = { onChar('2') })
            Spacer(modifier = Modifier.weight(1f))
            KeyboardItem(model = KeyboardItemModel.CharItem('3'), onClick = { onChar('3') })
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth()) {
            KeyboardItem(model = KeyboardItemModel.CharItem('4'), onClick = { onChar('4') })
            Spacer(modifier = Modifier.weight(1f))
            KeyboardItem(model = KeyboardItemModel.CharItem('5'), onClick = { onChar('5') })
            Spacer(modifier = Modifier.weight(1f))
            KeyboardItem(model = KeyboardItemModel.CharItem('6'), onClick = { onChar('6') })
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth()) {
            KeyboardItem(model = KeyboardItemModel.CharItem('7'), onClick = { onChar('7') })
            Spacer(modifier = Modifier.weight(1f))
            KeyboardItem(model = KeyboardItemModel.CharItem('8'), onClick = { onChar('8') })
            Spacer(modifier = Modifier.weight(1f))
            KeyboardItem(model = KeyboardItemModel.CharItem('9'), onClick = { onChar('9') })
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth()) {
            KeyboardItem(model = KeyboardItemModel.CharItem('0'), onClick = { onChar('0') })
            Spacer(modifier = Modifier.weight(1f))
            KeyboardItem(model = KeyboardItemModel.CharItem('.'), onClick = { onChar('.') })
            Spacer(modifier = Modifier.weight(1f))
            KeyboardItem(model = KeyboardItemModel.DeleteItem, onClick = { onDelete() })
        }
    }
}

@Composable
private fun KeyboardItem(model: KeyboardItemModel, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(72.dp)
            .shadow(elevation = 16.dp, shape = CircleShape, spotColor = PaypalPrimaryColor.copy(alpha = 0.3f))
            .background(PaypalWhiteColor, shape = CircleShape)
            .clip(CircleShape)
            .clickable(){ onClick() }
        ,
        contentAlignment = Alignment.Center
    ) {
        when (model) {
            is KeyboardItemModel.CharItem -> {
                Text(
                    text = model.char.toString(),
                    color = PaypalBlackColor,
                    fontFamily = PaypalFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            }

            is KeyboardItemModel.DeleteItem -> {
                Icon(
                    painter = painterResource(id = R.drawable.ic_paypal_delete),
                    contentDescription = "delete",
                    tint = PaypalBlackColor
                )
            }
        }
    }
}

@Preview
@Composable
private fun KeyboardPreview() {
    Keyboard({}, {})
}

private sealed class KeyboardItemModel {
    class CharItem(val char: Char) : KeyboardItemModel()
    object DeleteItem : KeyboardItemModel()
}
