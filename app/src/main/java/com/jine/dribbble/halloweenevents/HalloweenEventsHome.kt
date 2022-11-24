package com.jine.dribbble.halloweenevents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R

@Composable
fun HalloweenEventsHome(onTicketClicked: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = HalloweenEventsBrownLight)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(HalloweenEventsBrownStrong)
    ) {
        val (content, bottomBar, topBackground) = createRefs()
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
                    )
                )
                .background(color = Color(0xFFFFA451).copy(alpha = 0.2f))
                .constrainAs(topBackground) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .height(420.dp)
        )
        LazyColumn(modifier = Modifier.constrainAs(content) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom, margin = 56.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }) {
            item {
                ConstraintLayout {
                    val (header,
                        search,
                        eventsTitle,
                        events,
                        browseTitle,
                        browseSection) = createRefs()
                    Header(modifier = Modifier.constrainAs(header) {
                        top.linkTo(parent.top, margin = 36.dp)
                        start.linkTo(parent.start, margin = 24.dp)
                    })
                    SearchView(modifier = Modifier.constrainAs(search) {
                        top.linkTo(header.bottom, margin = 16.dp)
                        start.linkTo(parent.start, margin = 24.dp)
                        end.linkTo(parent.end, margin = 24.dp)
                        width = Dimension.fillToConstraints
                    })
                    Title(
                        title = "Upcoming events",
                        modifier = Modifier.constrainAs(eventsTitle) {
                            top.linkTo(search.bottom, margin = 32.dp)
                            start.linkTo(parent.start, margin = 24.dp)
                            end.linkTo(parent.end, margin = 24.dp)
                            width = Dimension.fillToConstraints
                        })
                    EventsList(modifier = Modifier.constrainAs(events) {
                        top.linkTo(eventsTitle.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }, onItemClick = onTicketClicked)
                    Title(
                        title = "Browse by categories",
                        modifier = Modifier.constrainAs(browseTitle) {
                            top.linkTo(events.bottom, margin = 38.dp)
                            start.linkTo(parent.start, margin = 24.dp)
                            end.linkTo(parent.end, margin = 24.dp)
                            width = Dimension.fillToConstraints
                        })
                    Categories(categories = listOf("All", "Music", "Holiday", "Food", "Fine Art"),
                        modifier = Modifier.constrainAs(browseSection) {
                            top.linkTo(browseTitle.bottom, margin = 12.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        })
                    Spacer(modifier = Modifier.size(100.dp))
                }
            }
            item {
                Spacer(modifier = Modifier.height(72.dp))
            }
        }
        BottomAppBar(modifier = Modifier.constrainAs(bottomBar) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        })
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Hi, Summer",
            color = HalloweenEventsWhiteStrong,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            fontFamily = HalloweenEventsFontFamily
        )
        Text(
            text = "What do you want to do?",
            color = HalloweenEventsWhiteStrong,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            fontFamily = HalloweenEventsFontFamily
        )
    }
}

@Composable
private fun SearchView(modifier: Modifier = Modifier) {
    var value by remember { mutableStateOf("") }
    TextField(
        modifier = modifier,
        singleLine = true,
        value = value,
        onValueChange = { value = it },
        shape = RoundedCornerShape(16.dp),
        textStyle = TextStyle(fontFamily = HalloweenEventsFontFamily),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = HalloweenEventsWhiteStrong.copy(alpha = 0.8f),
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color(0xFFF0F0F0).copy(alpha = 0.15f),
            textColor = HalloweenEventsWhiteLight
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.halloweenevents_search),
                contentDescription = null,
                tint = HalloweenEventsWhiteStrong
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = { value = "" }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Close,
                        contentDescription = "Erase search",
                        tint = HalloweenEventsWhiteStrong.copy(alpha = 0.8f)
                    )
                }
            }
        },
        placeholder = {
            Text(
                text = "Search events",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = HalloweenEventsWhiteStrong.copy(alpha = 0.3f),
                fontFamily = HalloweenEventsFontFamily
            )
        })
}

@Composable
private fun Title(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier,
        text = title,
        color = HalloweenEventsWhiteStrong,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        fontFamily = HalloweenEventsFontFamily
    )
}

@Composable
private fun EventsList(modifier: Modifier = Modifier, onItemClick: () -> Unit) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(2) {
            when (it) {
                0 -> EventsCell(
                    onClick = onItemClick,
                    dateNumber = "31",
                    dateMonth = "OCT",
                    title = "Halloween Party",
                    subtitle = "by Taylor",
                    imageId = R.drawable.halloweenevents_image1,
                    background = Color(0xFF753519)
                )
                1 -> EventsCell(
                    onClick = onItemClick,
                    dateNumber = "2",
                    dateMonth = "NOV",
                    title = "Baking Class",
                    subtitle = "by Lana",
                    imageId = R.drawable.halloweenevents_image2,
                    background = Color(0xFF73395A)
                )
            }
        }
    }
}

@Composable
private fun EventsCell(
    dateNumber: String,
    dateMonth: String,
    title: String,
    subtitle: String,
    imageId: Int,
    background: Color,
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .background(background)
    ) {
        val (imageView, dateView, titleView, descriptionView) = createRefs()
        Image(
            modifier = Modifier
                .size(width = 210.dp, height = 238.dp)
                .clip(RoundedCornerShape(12.dp))
                .constrainAs(imageView) {
                    top.linkTo(parent.top, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                },
            painter = painterResource(id = imageId),
            contentDescription = null
        )
        Text(
            modifier = Modifier.constrainAs(titleView) {
                top.linkTo(imageView.bottom, margin = 8.dp)
                start.linkTo(imageView.start)
            },
            text = title,
            color = HalloweenEventsWhiteLight,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = HalloweenEventsFontFamily
        )
        Text(
            modifier = Modifier.constrainAs(descriptionView) {
                top.linkTo(titleView.bottom)
                start.linkTo(titleView.start)
                bottom.linkTo(parent.bottom, margin = 12.dp)
            },
            text = subtitle,
            color = HalloweenEventsWhiteLight,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = HalloweenEventsFontFamily
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(HalloweenEventsWhiteStrong.copy(alpha = 0.8f))
                .size(44.dp)
                .constrainAs(dateView) {
                    top.linkTo(imageView.top, margin = 8.dp)
                    end.linkTo(imageView.end, margin = 8.dp)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dateNumber,
                color = HalloweenEventsBlack,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = HalloweenEventsFontFamily
            )
            Text(
                text = dateMonth,
                color = HalloweenEventsBlack,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontFamily = HalloweenEventsFontFamily
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Categories(modifier: Modifier = Modifier, categories: List<String>) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(categories) {
            Chip(
                onClick = { },
                colors = ChipDefaults.chipColors(
                    backgroundColor = HalloweenEventsWhiteStrong.copy(
                        alpha = 0.1f
                    )
                )
            ) {
                Text(
                    text = it,
                    color = HalloweenEventsWhiteLight,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    fontFamily = HalloweenEventsFontFamily
                )
            }
        }
    }
}

@Composable
private fun BottomAppBar(modifier: Modifier = Modifier) {
    BottomNavigation(
        modifier = modifier
            .height(104.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
        backgroundColor = HalloweenEventsBrownLight
    ) {
        BottomAppBarItem(
            modifier = Modifier.align(Alignment.CenterVertically),
            selected = true,
            iconId = R.drawable.halloweenevents_home,
            text = "Home"
        )
        BottomAppBarItem(
            modifier = Modifier.align(Alignment.CenterVertically),
            iconId = R.drawable.halloweenevents_tickets,
            text = "Tickets"
        )
        BottomAppBarItem(
            modifier = Modifier.align(Alignment.CenterVertically),
            iconId = R.drawable.halloweenevents_profile,
            text = "Profile"
        )
    }
}

@Composable
private fun RowScope.BottomAppBarItem(
    modifier: Modifier,
    selected: Boolean = false,
    iconId: Int,
    text: String
) {
    BottomNavigationItem(
        modifier = modifier.size(72.dp),
        selected = selected,
        onClick = { },
        selectedContentColor = HalloweenEventsWhiteStrong,
        unselectedContentColor = HalloweenEventsWhiteStrong.copy(alpha = 0.5f),
        icon = {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp),
                painter = painterResource(id = iconId),
                contentDescription = null
            )
        },
        label = {
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = HalloweenEventsFontFamily
            )
        })
}

@Preview
@Composable
fun HalloweenEventsBottomAppBar2Preview() {
    BottomAppBar()
}

@Preview(backgroundColor = 0xFF301509, showBackground = true)
@Composable
fun HalloweenEventsCategoriesPreview() {
    Categories(categories = listOf("All", "Music", "Holiday", "Food", "Fine Art"))
}

@Preview
@Composable
fun HalloweenEventsListPreview() {
    EventsList {}
}

@Preview
@Composable
fun HalloweenEventsCellPreview() {
    EventsCell(
        dateNumber = "31",
        dateMonth = "OCT",
        title = "Halloween Party",
        subtitle = "by Taylor",
        imageId = R.drawable.halloweenevents_image1,
        background = Color(0xFF753519)
    ) {}
}

@Preview
@Composable
fun HalloweenEventsEventsTitlePreview() {
    Title(title = "Upcoming events")
}

@Preview
@Composable
fun HalloweenEventsSearchViewPreview() {
    SearchView()
}

@Preview
@Composable
fun HalloweenEventsHeaderPreview() {
    Header()
}

@Preview
@Composable
fun HalloweenEventsHomePreview() {
    HalloweenEventsHome {}
}
