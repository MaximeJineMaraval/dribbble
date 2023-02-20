package com.jine.dribbble.snowboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jine.dribbble.R

@Composable
fun SnowboardStartingPage(onViewCollectionClicked: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(SnowboardBackground)
    ) {

        val (header, tagline, buttonSection, image) = createRefs()

        Header(modifier = Modifier.constrainAs(header) {
            start.linkTo(parent.start, 48.snowboardDp)
            top.linkTo(parent.top, 69.snowboardDp)
        })

        TagLine(modifier = Modifier.constrainAs(tagline) {
            bottom.linkTo(buttonSection.top, 142.snowboardDp)
            start.linkTo(parent.start, 48.snowboardDp)
            end.linkTo(image.start, 18.snowboardDp)
            width = Dimension.fillToConstraints
        })

        ButtonSection(
            modifier = Modifier.constrainAs(buttonSection) {
                bottom.linkTo(parent.bottom, 40.snowboardDp)
                start.linkTo(parent.start, 48.snowboardDp)
            },
            onClick = onViewCollectionClicked
        )

        Image(
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top, 64.snowboardDp)
                bottom.linkTo(parent.bottom, 64.snowboardDp)
                end.linkTo(parent.end)
                height = Dimension.fillToConstraints
            },
            painter = painterResource(id = R.drawable.snowboard_startingpage_board),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier) {
    SnowboardText(
        modifier = modifier,
        text = "SNOWBOARDS",
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.snowboardSp,
        color = SnowboardContentSecondary
    )
}

@Composable
private fun TagLine(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        SnowboardText(
            text = "Riders talked, and we listened",
            fontWeight = FontWeight.Normal,
            fontSize = 34.snowboardSp,
            color = SnowboardPrimaryColor
        )
        Spacer(modifier = Modifier.height(20.snowboardDp))
        Box(
            modifier = Modifier
                .size(10.snowboardDp)
                .background(color = SnowboardPrimaryColor, shape = CircleShape)
        )
        Spacer(modifier = Modifier.height(12.snowboardDp))
        SnowboardText(
            text = "Geometric",
            fontWeight = FontWeight.Black,
            fontSize = 34.snowboardSp,
            color = SnowboardPrimaryColor
        )
    }
}

@Composable
private fun ButtonSection(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        FloatingActionButton(
            modifier = Modifier.size(60.snowboardDp),
            onClick = onClick,
            backgroundColor = SnowboardPrimaryColor,
            contentColor = Color.White,
            content = {
                Icon(
                    modifier = Modifier
                        .size(24.snowboardDp)
                        .padding(start = 4.snowboardDp),
                    painter = painterResource(id = R.drawable.ic_snowboard_arrow),
                    contentDescription = "View collection"
                )
            }
        )
        Spacer(modifier = Modifier.width(16.snowboardDp))
        SnowboardText(
            text = "View collection",
            fontWeight = FontWeight.Bold,
            fontSize = 18.snowboardSp,
            color = SnowboardContentPrimary
        )
    }
}

// Previews

@Preview
@Composable
private fun ButtonSectionPreview() {
    ButtonSection {}
}

@Preview
@Composable
private fun TagLinePreview() {
    TagLine()
}

@Preview
@Composable
private fun HeaderPreview() {
    Header()
}

@Preview
@Composable
private fun SnowboardStartingPagePreview() {
    SnowboardStartingPage {}
}