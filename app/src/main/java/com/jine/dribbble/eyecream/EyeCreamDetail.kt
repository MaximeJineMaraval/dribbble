package com.jine.dribbble.eyecream

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R

@Composable
fun EyeCreamDetail(onBackClicked: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = EyeCreamPurpleLight)
    Box() {
        Image(
            painter = painterResource(id = R.drawable.eyecream_background),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
    }
    Column {
        Header(onBackClicked)
        Spacer(modifier = Modifier.weight(1f))
        Detail()
        Spacer(modifier = Modifier.height(62.dp))
    }
}

@Composable
private fun Header(onBackClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        IconButton(onClick = onBackClicked) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.eyecream_back),
                contentDescription = "Back",
                colorFilter = ColorFilter.tint(color = EyeCreamBlack)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {}) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.eyecream_basket),
                contentDescription = "Buy",
                colorFilter = ColorFilter.tint(color = EyeCreamBlack)
            )
        }
    }
}

@Composable
private fun Detail(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(horizontal = 24.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = EyeCreamWhite.copy(alpha = 0.95f)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            val (title, description, price, button) = createRefs()

            Text(
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                text = "Moon Dew Eye Cream",
                color = EyeCreamBlack,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = EyeCreamFontFamily
            )
            Text(
                modifier = Modifier.constrainAs(description) {
                    top.linkTo(title.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                text = "Reveal silky-smooth skin with the Crystal Queen of Calmness. Gently exfoliating and purely magical, crushed amethyst gemstone combines with magnesium-rich salt, ultra-nourishing organic virgin coconut oil and night-blooming jasmine sambac to lavish your body with luxe moisture.",
                color = EyeCreamBlackDescription,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = EyeCreamFontFamily
            )
            Text(
                modifier = Modifier.constrainAs(price) {
                    top.linkTo(button.top)
                    bottom.linkTo(button.bottom)
                    start.linkTo(parent.start)
                },
                text = "$44.00",
                color = EyeCreamBlack,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = EyeCreamFontFamily
            )
            EyeCreamButton(
                modifier = Modifier.constrainAs(button) {
                    top.linkTo(description.bottom, margin = 28.dp)
                    end.linkTo(parent.end)
                },
                text = "Add to cart"
            ) {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EyeCreamDetailHeaderPreview() {
    Header {}
}

@Preview(showBackground = true)
@Composable
fun EyeCreamDetailDetailPreview() {
    Detail()
}

@Preview
@Composable
fun EyeCreamDetailScreenPreview() {
    EyeCreamDetail {}
}
