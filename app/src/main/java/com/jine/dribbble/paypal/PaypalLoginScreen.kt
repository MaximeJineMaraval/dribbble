package com.jine.dribbble.paypal

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

@Composable
fun PaypalLoginScreen(navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PaypalWhiteColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(56.dp))
        Image(
            modifier = Modifier.width(194.dp),
            painter = painterResource(id = R.drawable.paypal_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(98.dp))
        PaypalTextField(
            modifier = Modifier.padding(horizontal = 56.dp),
            placeHolder = "Enter your name or e-mail",
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        PaypalTextField(
            modifier = Modifier.padding(horizontal = 56.dp),
            placeHolder = "Password",
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(32.dp))
        PaypalButton(
            modifier = Modifier.padding(horizontal = 56.dp),
            text = "Log in",
            onClick = { navController.navigate(DESTINATION_PAYPAL_MAIN) })
        Spacer(modifier = Modifier.height(60.dp))
        PaypalTextButton(
            text = "Having trouble logging in?",
            onClick = { showNotImplementedToast(context) })
        Spacer(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .width(65.dp)
                .height(2.dp)
                .background(PaypalGrayColor)
        )
        PaypalTextButton(
            text = "Sign up",
            onClick = { showNotImplementedToast(context) })
    }
}

@Composable
private fun PaypalTextField(
    modifier: Modifier = Modifier,
    placeHolder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
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
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(20.dp))
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { isFocused = it.isFocused }
                    .padding(top = 4.dp),
                value = value,
                onValueChange = { value = it },
                textStyle = TextStyle(
                    color = PaypalBlackColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = PaypalFontFamily
                ),
                placeholder = {
                    PaypalSmallText(
                        modifier = Modifier.fillMaxWidth(),
                        text = placeHolder,
                        color = PaypalBlackColor.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = PaypalPrimaryColor,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                ),
                keyboardOptions = keyboardOptions,
                visualTransformation = visualTransformation
            )
        }
    }
}

@Preview
@Composable
private fun PaypalLoginScreenPreview() {
    PaypalLoginScreen(rememberNavController())
}