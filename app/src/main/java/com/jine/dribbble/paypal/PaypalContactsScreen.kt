package com.jine.dribbble.paypal

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R

@Composable
fun PaypalContactsScreen() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = PaypalWhiteColor)
    var searchText by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize()
            .background(PaypalWhiteColor)
    ) {
        PaypalToolbar(
            navController = null,
            title = "Contacts",
            option = Pair(R.drawable.ic_paypal_plus, "add")
        )
        SearchBar {
            searchText = it.lowercase()
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(
                    ContactModel.getAll().filter {
                        it.email.contains(searchText) or it.name.lowercase().contains(searchText)
                    }) {
                    Item(it)
                }
            })
    }
}

@Composable
private fun SearchBar(onSearch: (searchText: String) -> Unit) {
    var value by remember { mutableStateOf("") }
    var isFocused by remember {
        mutableStateOf(false)
    }
    val borderColor by animateColorAsState(
        targetValue = if (isFocused) PaypalPrimaryColor
        else PaypalGrayColor
    )
    val selectionColors = TextSelectionColors(
        handleColor = PaypalPrimaryColor,
        backgroundColor = PaypalPrimaryColor.copy(alpha = 0.3f),
    )
    CompositionLocalProvider(LocalTextSelectionColors provides selectionColors) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(56.dp)
                .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(20.dp))
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { isFocused = it.isFocused }
                    .padding(top = 4.dp),
                value = value,
                onValueChange = {
                    value = it
                    onSearch(it)
                },
                textStyle = TextStyle(
                    color = PaypalBlackColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = PaypalFontFamily
                ),
                placeholder = {
                    PaypalSmallText(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Enter a name or e-mail",
                        color = PaypalBlackColor.copy(alpha = 0.5f),
                    )
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_paypal_search),
                        contentDescription = null,
                        tint = PaypalBlackColor.copy(alpha = 0.5f)
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = PaypalPrimaryColor,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
            )
        }
    }
//    }
}

@Composable
private fun Item(contactModel: ContactModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 16.dp,
                spotColor = PaypalPrimaryColor.copy(alpha = 0.2f),
                shape = RoundedCornerShape(20.dp)
            )
            .background(color = PaypalWhiteColor, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(PaypalGrayColor, shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = contactModel.initial,
                fontFamily = PaypalFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = PaypalBlackColor
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column() {
            PaypalRegularText(text = contactModel.name)
            PaypalSmallText(text = contactModel.email, color = PaypalBlackColor.copy(alpha = 0.5f))
        }
    }
}

@Preview
@Composable
private fun PaypalContactsScreenPreview() {
    PaypalContactsScreen()
}

private class ContactModel(val name: String, val email: String) {
    val initial: String
        get() = name.first().uppercase()

    companion object {
        fun getAll(): List<ContactModel> {
            return listOf(
                ContactModel("Andrew Dillan", "andrew.dillan@gmail.com"),
                ContactModel("Alex Millton", "alxmillton@yahoo.com"),
                ContactModel("Don Norman", "@donnorman"),
                ContactModel("Jason Craig", "@jcraig90"),
                ContactModel("Mike Rine", "mikerine@gmail.com"),
                ContactModel("Nick Aeron", "aeronn@gmail.com"),
                ContactModel("Vena Sunny", "@venasunny"),
                ContactModel("Mike Rine", "mikerine@gmail.com"),
            )
        }
    }
}