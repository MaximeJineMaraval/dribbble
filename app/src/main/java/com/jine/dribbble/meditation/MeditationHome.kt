package com.jine.dribbble.meditation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

@Composable
fun MeditationHome(onMeditationClicked: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MeditationBackground)
    systemUiController.setNavigationBarColor(color = Color.White)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MeditationBackground)
    ) {
        val (content, bottomNavigation) = createRefs()

        BottomBar(modifier = Modifier.constrainAs(bottomNavigation) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        })
        LazyColumn(modifier = Modifier.constrainAs(content) {
            top.linkTo(parent.top)
            bottom.linkTo(bottomNavigation.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }) {
            item { Header() }
            item { RecommendSection() }
            item { ContinueSection() }
            item { ActivitiesSection(onMeditationClicked) }
        }
    }
}

@Composable
private fun BottomBar(modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    val context = LocalContext.current
    Box(
        modifier = modifier
    ) {
        BottomNavigation(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(88.dp)
                .shadow(20.dp, spotColor = Color(0xFF5EBDDA), shape = shape)
                .clip(shape)
                .background(Color.White),
            backgroundColor = Color.White,
            elevation = 0.dp
        ) {
            BottomBarItem(
                selected = true,
                onClick = { },
                iconId = R.drawable.ic_meditation_home,
                label = "Home"
            )
            BottomBarItem(
                onClick = { showNotImplementedToast(context) },
                iconId = R.drawable.ic_meditation_meditation,
                label = "Meditation"
            )
            BottomBarItem(
                onClick = { showNotImplementedToast(context) },
                iconId = R.drawable.ic_meditation_moon,
                label = "Sleep"
            )
            BottomBarItem(
                onClick = { showNotImplementedToast(context) },
                iconId = R.drawable.ic_meditation_profile,
                label = "Profile"
            )
        }
    }
}

@Composable
private fun RowScope.BottomBarItem(
    selected: Boolean = false,
    onClick: () -> Unit,
    iconId: Int,
    label: String
) {
    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(24.dp))
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = iconId),
                    contentDescription = null
                )
                MeditationText(
                    text = label,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.Unspecified
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        },
        selectedContentColor = MeditationPrimary1,
        unselectedContentColor = MeditationNeutral3
    )
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 43.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            MeditationText(
                text = "Good evening",
                fontWeight = FontWeight.Medium,
                fontSize = 32.sp,
                color = MeditationNeutral1
            )
            MeditationText(
                text = "Everyday is a good day",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = MeditationNeutral2
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Box() {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .border(width = 2.dp, color = MeditationPrimary1, shape = CircleShape),
                painter = painterResource(id = R.drawable.meditation_profilepic),
                contentDescription = "profile picture"
            )
        }
    }
}

@Composable
private fun SectionTitle(modifier: Modifier = Modifier, title: String) {
    MeditationText(
        modifier = modifier,
        text = title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        color = MeditationNeutral1
    )
}

@Composable
private fun RecommendSection(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier.padding(top = 24.dp, start = 20.dp, end = 20.dp)) {


        val (title, text, button, background, image) = createRefs()


        // Title
        SectionTitle(
            title = "Recommend",
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            })

        // Background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(176.dp)
                .background(color = MeditationPrimary1, shape = RoundedCornerShape(24.dp))
                .constrainAs(background) {
                    top.linkTo(title.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                }
        )

        // Text
        Column(modifier = Modifier.constrainAs(text) {
            top.linkTo(background.top, margin = 20.dp)
            start.linkTo(background.start, margin = 20.dp)
            end.linkTo(image.start)
            width = Dimension.fillToConstraints
        }) {
            MeditationText(
                text = "Daily 10-minute meditation",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MeditationNeutral4
            )
            MeditationText(
                text = "10 episodes",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = MeditationPrimary3
            )
        }

        // Image
        Image(
            painter = painterResource(id = R.drawable.meditation_recommendimage),
            contentDescription = null,
            modifier = Modifier
                .height(202.dp)
                .constrainAs(image) {
                    end.linkTo(background.end)
                    bottom.linkTo(background.bottom)
                }
        )

        // Button
        val context = LocalContext.current
        Button(
            modifier = Modifier.constrainAs(button) {
                top.linkTo(text.bottom, margin = 16.dp)
                start.linkTo(text.start)
            },
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 4.dp,
                hoveredElevation = 2.dp,
                focusedElevation = 2.dp
            ),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MeditationNeutral4,
                contentColor = MeditationPrimary1
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
            onClick = { showNotImplementedToast(context) }) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_meditation_play),
                contentDescription = null,
                tint = MeditationPrimary1
            )
            Spacer(modifier = Modifier.width(4.dp))
            MeditationText(
                text = "Play",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = MeditationPrimary1
            )
        }

    }
}

@Composable
private fun ContinueSection(modifier: Modifier = Modifier) {
    var isPlaying by remember { mutableStateOf(false) }

    val animatedTimeSpent = remember { Animatable(0.4f) }
    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            animatedTimeSpent.animateTo(
                targetValue = 1f,
                animationSpec = TweenSpec(
                    durationMillis = (50000 - (animatedTimeSpent.value * 50000)).toInt(),
                    easing = LinearEasing
                )
            )
        } else {
            animatedTimeSpent.stop()
        }
    }

    Column(modifier = modifier.padding(top = 28.dp, start = 20.dp, end = 20.dp)) {
        SectionTitle(title = "Continue")
        Spacer(modifier = Modifier.height(20.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {

            // Image
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(20.dp)),
                painter = painterResource(id = R.drawable.ic_meditation_continueimage),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Text
            Column {
                MeditationText(
                    text = "Stress relaxing",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = MeditationNeutral1
                )
                Spacer(modifier = Modifier.height(4.dp))
                MeditationText(
                    text = "10 minutes",
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = MeditationNeutral2
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            // FAB
            FloatingActionButton(
                modifier = Modifier.size(40.dp),
                backgroundColor = MeditationPrimary1,
                contentColor = MeditationNeutral4,
                onClick = {
                    isPlaying = isPlaying.not()
                }) {
                Icon(
                    modifier = Modifier
                        .size(28.dp)
                        .padding(start = if (isPlaying) 0.dp else 4.dp),
                    painter = painterResource(id = if (isPlaying) R.drawable.ic_meditation_pause else R.drawable.ic_meditation_play),
                    contentDescription = "play"
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        // TimeLine
        Box(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth(animatedTimeSpent.value)
                .clip(RoundedCornerShape(2.dp))
                .background(MeditationPrimary1)
        )
    }
}

@Composable
private fun ActivitiesSection(onMeditationClicked: () -> Unit) {
    Column(modifier = Modifier.padding(vertical = 28.dp)) {
        SectionTitle(
            modifier = Modifier.padding(start = 20.dp, bottom = 20.dp),
            title = "Activities"
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(
                listOf(
                    Triple(
                        MeditationPrimary1,
                        "Power",
                        R.drawable.meditation_power
                    ),
                    Triple(
                        MeditationSecondary1,
                        "Relaxing",
                        R.drawable.meditation_relaxing
                    ),
                    Triple(
                        MeditationPrimary1,
                        "Healing",
                        R.drawable.meditation_healing
                    ),
                )
            ) {
                ActivitiesItem(
                    backgroundColor = it.first,
                    label = it.second,
                    imageId = it.third,
                    onClick = if (it.second == "Power") onMeditationClicked else null
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ActivitiesItem(
    backgroundColor: Color,
    label: String,
    imageId: Int,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .height(158.dp)
            .width(140.dp),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = backgroundColor,
        elevation = 0.dp,
        onClick = onClick ?: { showNotImplementedToast(context) }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(10.dp))
            MeditationText(
                text = label,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MeditationNeutral4
            )
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = imageId),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
        }
    }
}

// Previews

@Preview
@Composable
private fun ActivitiesItemPreview() {
    ActivitiesItem(MeditationPrimary1, "Healing", R.drawable.meditation_healing)
}

@Preview(showBackground = true)
@Composable
private fun ActivitiesSectionPreview() {
    ActivitiesSection {}
}

@Preview(showBackground = true)
@Composable
private fun ContinueSectionPreview() {
    ContinueSection()
}


@Preview(showBackground = true)
@Composable
private fun RecommendSectionPreview() {
    RecommendSection()
}

@Preview(showBackground = true)
@Composable
private fun SectionTitlePreview() {
    SectionTitle(title = "Section title")
}

@Preview(showBackground = true)
@Composable
private fun HeaderPreview() {
    Header()
}

@Preview
@Composable
private fun BottomBarPreview() {
    BottomBar()
}

@Preview
@Composable
private fun MeditationHomePreview() {
    MeditationHome {}
}