package com.jine.dribbble.financeconcept

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jine.dribbble.R

@Composable
fun FinanceConceptHomePage(onButtonClick: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(FinanceConceptAlmostWhite)
    ) {
        val (wallet, secured, title, subtitle, button) = createRefs()
        Image(
            modifier = Modifier
                .fillMaxWidth(0.72f)
                .constrainAs(wallet) {
                    top.linkTo(parent.top, margin = 96.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                },
            painter = painterResource(R.drawable.financeconcept_wallet),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(0.42f)
                .constrainAs(secured) {
                    top.linkTo(parent.top, margin = 288.dp)
                    end.linkTo(parent.end, margin = 28.dp)
                },
            painter = painterResource(R.drawable.financeconcept_secured),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
        Text(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(secured.bottom, margin = 32.dp)
                start.linkTo(parent.start, margin = 24.dp)
            },
            text = "Safe payment,\nhappy life",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            fontFamily = FinanceConceptFontFamily,
            color = FinanceConceptBlack
        )
        Text(
            modifier = Modifier.constrainAs(subtitle) {
                top.linkTo(title.bottom, margin = 24.dp)
                start.linkTo(parent.start, margin = 24.dp)
                end.linkTo(parent.end, margin = 24.dp)
                width = Dimension.fillToConstraints
            },
            text = "Make safe payments and keep your money wise with our help.",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontFamily = FinanceConceptFontFamily,
            color = FinanceConceptGrey
        )
        Button(modifier = Modifier.constrainAs(button) {
            top.linkTo(subtitle.bottom, margin = 56.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, onClick = onButtonClick) {
            Text(
                text = "Get Started",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                fontFamily = FinanceConceptFontFamily,
                color = FinanceConceptWhite
            )
            Spacer(modifier = Modifier.size(16.dp))
            Image(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = FinanceConceptWhite)
            )
        }
    }
}

@Composable
private fun Button(
    modifier: Modifier,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .clickable { onClick() }
            .background(FinanceConceptGradiant)
            .height(48.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Preview
@Composable
private fun ScreenPreview() {
    FinanceConceptHomePage {}
}