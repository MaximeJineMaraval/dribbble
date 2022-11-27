package com.jine.dribbble.egarden

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

@Composable
fun EGardenStore(modifier: Modifier) {
    LazyColumn(modifier = modifier.background(EGardenWhite)) {
        item {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (title,
                    cartButton,
                    search,
                    filter,
                    primaryContent,
                    productSection,
                    plantTypesSection,
                    popularSection) = createRefs()

                EGardenTitle(title = "Secret Shop", modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, margin = 20.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                })

                CartButton(modifier = Modifier.constrainAs(cartButton) {
                    top.linkTo(title.top)
                    bottom.linkTo(title.bottom)
                    end.linkTo(parent.end)
                })

                Search(modifier = Modifier.constrainAs(search) {
                    top.linkTo(filter.top)
                    bottom.linkTo(filter.bottom)
                    end.linkTo(filter.start, margin = 24.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                    width = Dimension.fillToConstraints
                })

                Filter(modifier = Modifier.constrainAs(filter) {
                    top.linkTo(title.bottom, margin = 20.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                })

                PrimaryContent(modifier = Modifier.constrainAs(primaryContent) {
                    top.linkTo(search.bottom, margin = 36.dp)
                    start.linkTo(parent.start, 32.dp)
                    end.linkTo(parent.end, 32.dp)
                    width = Dimension.fillToConstraints
                })

                ProductsSection(modifier = Modifier.constrainAs(productSection) {
                    top.linkTo(primaryContent.bottom, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                })

                PlantTypesSection(modifier = Modifier.constrainAs(plantTypesSection) {
                    top.linkTo(productSection.bottom, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                })

                PopularSection(modifier = Modifier.constrainAs(popularSection) {
                    top.linkTo(plantTypesSection.bottom, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                })
            }
        }
        item {
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CartButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Card(modifier = modifier
        .width(68.dp)
        .height(40.dp),
        shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
        elevation = 10.dp,
        onClick = { showNotImplementedToast(context) }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.size(12.dp))
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.egarden_shopping_cart),
                tint = EGardenBlack,
                contentDescription = "Open cart"
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Search(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var value by remember { mutableStateOf("") }
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    BasicTextField(
        value = value,
        onValueChange = { value = it },
        textStyle = TextStyle(
            color = EGardenBlack,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = EGardenNunitoFontFamily
        ),
        modifier = modifier
            .background(
                color = EGardenGreyExtraLight,
                shape = RoundedCornerShape(12.dp)
            )
            .indicatorLine(
                enabled = true,
                isError = false,
                interactionSource = interactionSource,
                colors = TextFieldDefaults.textFieldColors(),
                focusedIndicatorLineThickness = 0.dp,  //to hide the indicator line
                unfocusedIndicatorLineThickness = 0.dp //to hide the indicator line
            )
            .height(40.dp),
        interactionSource = interactionSource,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {
            showNotImplementedToast(context)
        })
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = value,
            innerTextField = it,
            singleLine = true,
            enabled = true,
            visualTransformation = VisualTransformation.None,
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.egarden_search),
                    tint = EGardenBlack,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(onClick = { value = "" }) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.Close,
                            tint = EGardenBlack,
                            contentDescription = "Erase search content"
                        )
                    }
                }
            },
            placeholder = {
                Text(
                    text = "Enter plant name",
                    color = EGardenGreyLight,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = EGardenNunitoFontFamily
                )
            },
            interactionSource = interactionSource,
            // keep horizontal paddings but change the vertical
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                top = 0.dp, bottom = 0.dp
            )
        )
    }
}

@Composable
private fun Filter(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(EGardenGreyExtraLight)
            .clickable { showNotImplementedToast(context) }
            .padding(vertical = 8.dp, horizontal = 22.dp)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.egarden_sliders),
            tint = EGardenBlack,
            contentDescription = "Show filters"
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PrimaryContent(modifier: Modifier = Modifier) {
    val radius = 12.dp
    val context = LocalContext.current
    Box(
        modifier = modifier
            .height(176.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // Green shadow
        Box(
            modifier = Modifier
                .shadow(
                    elevation = 16.dp,
                    ambientColor = Color(0xFF2CA780),
                    spotColor = Color(0xFF2CA780),
                    clip = false,
                    shape = RoundedCornerShape(28.dp),
                )
                .fillMaxHeight()
                .fillMaxWidth(0.75f)
        )
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(radius),
            elevation = 28.dp,
            onClick = { showNotImplementedToast(context) }
        ) {
            Box {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.egarden_primarycontent),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp, start = 18.dp),
                    text = "Outer world’s plants\nsuitable for indoor deco.",
                    color = EGardenWhite.copy(alpha = 0.8f),
                    fontFamily = EGardenNunitoFontFamily,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier,
        text = title,
        color = EGardenBlack,
        fontFamily = EGardenNunitoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}

@Composable
private fun ProductsSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        SectionTitle(modifier = Modifier.padding(start = 32.dp), title = "Products")
        Spacer(modifier = Modifier.size(20.dp))
        val items = listOf(
            Triple(R.drawable.egarden_product1, "Plants", "155 products"),
            Triple(R.drawable.egarden_product2, "Flowers", "98 products"),
            Triple(R.drawable.egarden_product3, "Fertilizer", "55 products")
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 32.dp)
        ) {
            items(items) { item ->
                ProductItem(imageId = item.first, title = item.second, subtitle = item.third)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ProductItem(imageId: Int, title: String, subtitle: String) {
    val context = LocalContext.current
    Card(
        elevation = 8.dp,
        backgroundColor = EGardenWhite,
        shape = RoundedCornerShape(8.dp),
        onClick = { showNotImplementedToast(context) }
    ) {
        Column(
            modifier = Modifier.padding(start = 18.dp, top = 12.dp, end = 18.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(44.dp),
                painter = painterResource(id = imageId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = title,
                color = EGardenGreyStrong,
                fontFamily = EGardenNunitoFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = subtitle,
                color = EGardenGreyLight,
                fontFamily = EGardenNunitoFontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun PlantTypesSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        SectionTitle(modifier = Modifier.padding(start = 32.dp), title = "Plant Types")
        Spacer(modifier = Modifier.size(20.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val items = listOf(
                Triple(R.drawable.egarden_planttypes1, "Outer Worlds", "72 types of plants"),
                Triple(R.drawable.egarden_planttypes2, "Home Plants", "77 types of plants")
            )
            items(items) { item ->
                PlantTypesItem(imageId = item.first, title = item.second, subtitle = item.third)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PlantTypesItem(imageId: Int, title: String, subtitle: String) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .width(280.dp)
            .height(144.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        onClick = { showNotImplementedToast(context) }
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Box(modifier = Modifier.background(Color.Black.copy(alpha = 0.24f)))
        Column(Modifier.padding(horizontal = 12.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = title,
                color = EGardenWhite,
                fontFamily = EGardenNunitoFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(6.dp))
            Text(
                text = subtitle,
                color = EGardenWhite,
                fontFamily = EGardenNunitoFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.size(14.dp))
        }
    }
}

@Composable
private fun PopularSection(modifier: Modifier = Modifier) {
    val items = listOf(
        Pair(
            Triple(R.drawable.egarden_popular1, "Muli Space", "$14"),
            Pair(Color(0xFFEEB041), Color(0xFFF0B754))
        ),
        Pair(
            Triple(R.drawable.egarden_popular2, "Aratatata", "$14"),
            Pair(Color(0xFF57B1A7), Color(0xFF64B7AD))
        ),
        Pair(
            Triple(R.drawable.egarden_popular2, "Aratatata", "$14"),
            Pair(Color(0xFF5786B1), Color(0xFF6293C1))
        ),
        Pair(
            Triple(R.drawable.egarden_popular1, "Muli Space", "$14"),
            Pair(Color(0xFFFABBB9), Color(0xFFFEC3C1))
        ),
    )
    Column(modifier = modifier.padding(horizontal = 32.dp)) {
        SectionTitle(title = "Popular")
        Spacer(modifier = Modifier.size(20.dp))
        (0 until items.size / 2).forEach { index ->
            val firstItem = items[index * 2]
            val secondItem = items[index * 2 + 1]
            Row {
                PopularItem(
                    modifier = Modifier.weight(1f),
                    imageId = firstItem.first.first,
                    title = firstItem.first.second,
                    price = firstItem.first.third,
                    colorStrong = firstItem.second.first,
                    colorLight = firstItem.second.second
                )
                Spacer(modifier = Modifier.size(16.dp))
                PopularItem(
                    modifier = Modifier.weight(1f),
                    imageId = secondItem.first.first,
                    title = secondItem.first.second,
                    price = secondItem.first.third,
                    colorStrong = secondItem.second.first,
                    colorLight = secondItem.second.second
                )
            }
            //Only add vertical space if there is more items to show
            if ((index + 1) * 2 + 1 <= items.size) {
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PopularItem(
    modifier: Modifier = Modifier,
    imageId: Int,
    title: String,
    price: String,
    colorStrong: Color,
    colorLight: Color
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .fillMaxWidth(),
        backgroundColor = colorStrong,
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        onClick = { showNotImplementedToast(context) }) {
        ConstraintLayout() {

            val (shape1, shape2) = createRefs()
            // Background shape 1
            Box(
                modifier = Modifier
                    .width(108.dp)
                    .height(140.dp)
                    .rotate(37f)
                    .background(color = colorLight, shape = RoundedCornerShape(8.dp))
                    .constrainAs(shape1) {
                        top.linkTo(parent.top, margin = 56.dp)
                        end.linkTo(parent.end, margin = 96.dp)
                    }
            )
            // Background shape 2
            Box(
                modifier = Modifier
                    .width(108.dp)
                    .height(70.dp)
                    .rotate(37f)
                    .background(color = colorLight, shape = RoundedCornerShape(8.dp))
                    .constrainAs(shape2) {
                        top.linkTo(parent.top, margin = 136.dp)
                        start.linkTo(parent.start, margin = 64.dp)
                    }
            )

            // Image and labels
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Spacer(modifier = Modifier.size(12.dp))
                Image(
                    modifier = Modifier
                        .height(118.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = imageId),
                    contentScale = ContentScale.FillHeight,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = title,
                    color = EGardenWhite,
                    fontFamily = EGardenNunitoFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    modifier = Modifier
                        .background(color = EGardenWhite, shape = RoundedCornerShape(56.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    text = price,
                    color = colorLight,
                    fontFamily = EGardenNunitoFontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Preview
@Composable
fun EGardenPopularSectionPreview() {
    PopularSection()
}

@Preview(widthDp = 148)
@Composable
fun EGardenPopularItemPreview() {
    PopularItem(
        imageId = R.drawable.egarden_popular1,
        title = "Muli Space",
        price = "$14",
        colorStrong = Color(0xFFEEB041),
        colorLight = Color(0xFFF0B754)
    )
}

@Preview
@Composable
fun EGardenPlantTypesSectionPreview() {
    PlantTypesSection()
}

@Preview
@Composable
fun EGardenPlantTypesItemPreview() {
    PlantTypesItem(
        imageId = R.drawable.egarden_planttypes1,
        title = "Outer Worlds",
        subtitle = "72 Types of Plants"
    )
}

@Preview
@Composable
fun EGardenProductItemPreview() {
    ProductItem(
        imageId = R.drawable.egarden_product1,
        title = "Plants",
        subtitle = "155 products"
    )
}

@Preview
@Composable
fun EGardenProductsSectionPreview() {
    ProductsSection()
}

@Preview
@Composable
fun EGardenSectionTitlePreview() {
    SectionTitle(title = "Plant Types")
}

@Preview
@Composable
fun EGardenPrimaryContentPreview() {
    PrimaryContent()
}

@Preview
@Composable
fun EGardenFilterPreview() {
    Filter()
}

@Preview
@Composable
fun EGardenSearchPreview() {
    Search()
}

@Preview
@Composable
fun EGardenCartButtonPreview() {
    CartButton()
}

@Preview
@Composable
fun EGardenStorePreview() {
    EGardenStore(modifier = Modifier.fillMaxSize())
}