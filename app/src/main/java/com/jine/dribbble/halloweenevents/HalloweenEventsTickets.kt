package com.jine.dribbble.halloweenevents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun HalloweenEventsTickets(onBackClicked: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = HalloweenEventsBrownStrong)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(HalloweenEventsBrownStrong)
    ) {

        val (backButton, ticket) = createRefs()

        IconButton(
            modifier = Modifier.constrainAs(backButton) {
                top.linkTo(parent.top, margin = 32.dp)
                start.linkTo(parent.start, margin = 12.dp)
            },
            onClick = onBackClicked
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.halloweenevents_back),
                tint = HalloweenEventsWhiteStrong,
                contentDescription = "Back"
            )
        }

        Ticket(modifier = Modifier.constrainAs(ticket) {
            top.linkTo(backButton.bottom, margin = 28.dp)
            start.linkTo(parent.start, margin = 24.dp)
            end.linkTo(parent.end, margin = 24.dp)
            width = Dimension.fillToConstraints
        })
    }
}

@Composable
private fun Ticket(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFDC6430).copy(alpha = 0.4f))
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp),
    ) {
        Image(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            painter = painterResource(id = R.drawable.halloweenevents_image3),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Halloween Party",
            color = HalloweenEventsWhiteLight,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = HalloweenEventsFontFamily
        )
        Text(
            text = "by Taylor",
            color = HalloweenEventsWhiteLight,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = HalloweenEventsFontFamily
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            modifier = Modifier.width(148.dp),
            color = HalloweenEventsWhiteStrong.copy(alpha = 0.4f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "LOCATION",
            color = HalloweenEventsWhiteStrong.copy(alpha = 0.6f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = HalloweenEventsFontFamily
        )
        Text(
            text = "1901 Thornridge Cir. Shiloh, Hawaii 81063",
            color = HalloweenEventsWhiteLight,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = HalloweenEventsFontFamily
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "NAME",
            color = HalloweenEventsWhiteStrong.copy(alpha = 0.6f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = HalloweenEventsFontFamily
        )
        Text(
            text = "Summer",
            color = HalloweenEventsWhiteLight,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = HalloweenEventsFontFamily
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row() {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "DATE",
                    color = HalloweenEventsWhiteStrong.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = HalloweenEventsFontFamily
                )
                Text(
                    text = "31 Oct 2022",
                    color = HalloweenEventsWhiteLight,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = HalloweenEventsFontFamily
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "TIME",
                    color = HalloweenEventsWhiteStrong.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = HalloweenEventsFontFamily
                )
                Text(
                    text = "6:00 PM",
                    color = HalloweenEventsWhiteLight,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = HalloweenEventsFontFamily
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            modifier = Modifier.width(148.dp),
            color = HalloweenEventsWhiteStrong.copy(alpha = 0.4f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Scan barcode",
            color = HalloweenEventsWhiteLight,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = HalloweenEventsFontFamily
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            modifier = Modifier
                .width(180.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.halloweenevents_barcode),
            contentDescription = "Barcode",
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview
@Composable
private fun HalloweenEventsTicketPreview() {
    Ticket()
}

@Preview
@Composable
fun HalloweenEventsTicketsPreview() {
    HalloweenEventsTickets {
    }
}