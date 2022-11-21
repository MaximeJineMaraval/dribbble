package com.jine.dribbble.eyecream

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
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
fun EyeCreamHome(onDetailClicked: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = EyeCreamWhite)
    Column(modifier = Modifier.background(color = EyeCreamWhite)) {
        Header()
        Spacer(modifier = Modifier.height(10.dp))
        Products(onDetailClicked)
        Spacer(modifier = Modifier.height(32.dp))
        NewIn()
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        IconButton(onClick = {}) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.eyecream_menu),
                contentDescription = "Menu",
                colorFilter = ColorFilter.tint(color = EyeCreamBlack)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {}) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.eyecream_search),
                contentDescription = "Search",
                colorFilter = ColorFilter.tint(color = EyeCreamBlack)
            )
        }
    }
}

@Composable
private fun Products(onDetailClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp, vertical = 24.dp)
    ) {
        // Back image
        Image(
            painter = painterResource(id = R.drawable.eyecream_product5),
            modifier = Modifier
                .fillMaxWidth()
                .rotate(5f)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
        // Front image

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .rotate(-5f)
                .clip(RoundedCornerShape(20.dp))
        ) {
            val (image, bottomLayout) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.eyecream_product6),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .constrainAs(bottomLayout) {
                        start.linkTo(image.start)
                        end.linkTo(image.end)
                        top.linkTo(image.bottom)
                        bottom.linkTo(image.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
                    .background(color = EyeCreamPurpleSecond),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Moon Dew Eye Cream",
                    color = EyeCreamBlack,
                    fontFamily = EyeCreamFontFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                EyeCreamButton(text = "Shop", onClick = onDetailClicked)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun NewIn() {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "New in",
                color = EyeCreamBlack,
                fontFamily = EyeCreamFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "View all",
                color = EyeCreamBlack,
                fontFamily = EyeCreamFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        val items = listOf(
            Triple(R.drawable.eyecream_product4, "Body Scrub", "$44.00"),
            Triple(R.drawable.eyecream_product3, "Smoothing Serum", "$54.00"),
            Triple(R.drawable.eyecream_product1, "Body Scrub", "$44.00"),
            Triple(R.drawable.eyecream_product2, "Smoothing Serum", "$54.00"),
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            content = {
                items(items) {
                    NewInCell(imageId = it.first, title = it.second, price = it.third)
                }
            })
    }
}

@Composable
private fun NewInCell(imageId: Int, title: String, price: String) {
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            painter = painterResource(id = imageId),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title,
            color = EyeCreamBlack,
            fontFamily = EyeCreamFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
        Text(
            text = price,
            color = EyeCreamGrey,
            fontFamily = EyeCreamFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun EyeCreamHomeScreenPreview() {
    EyeCreamHome {
    }
}

@Preview
@Composable
fun ProductsPreview() {
    Products {}
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header()
}

@Preview(showBackground = true)
@Composable
fun NewInPreview() {
    NewIn()
}

@Preview(showBackground = true)
@Composable
fun NewInCellPreview() {
    NewInCell(imageId = R.drawable.eyecream_product4, title = "Body Scrub", price = "$44.00")
}