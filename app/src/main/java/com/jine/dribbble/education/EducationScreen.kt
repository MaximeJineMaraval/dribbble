package com.jine.dribbble.education

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast
import java.math.BigDecimal

// TODO: Ajouter les petits détails d'image avec le fond

//region Resources

private val EducationMainColor = Color(0xFFFF8383)
private val EducationWhite = Color(0xFFFFFFFF)
private val EducationBlack = Color(0xFF000000)
private val EducationMainFontFamily = FontFamily(
    Font(R.font.metropolis_semibold, FontWeight.SemiBold),
    Font(R.font.metropolis_medium, FontWeight.Medium)
)
private val EducationBookFontFamily = FontFamily(
    Font(R.font.abhayalibre_regular, FontWeight.Normal)
)
val EducationGradient = Brush.linearGradient(colors = listOf(EducationMainColor, EducationWhite))

private fun <T> educationAnimationSpec() = tween<T>(durationMillis = 300)
private fun getBackgroundColor(currentPageIndex: Int): Color {
    return when (currentPageIndex) {
        0 -> Color(0xFFFFF4F3)
        1 -> Color(0xFFFEF4DF)
        else -> Color(0xFF10205A)
    }
}

//endregion

//region Screen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EducationScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (background, pageIndicator, imagePager, difficultyProgressBar, description, startButton, bottomBar) = createRefs()

        //region Pagers behavior
        val imagePagerState = rememberPagerState()
        val descriptionPagerState = rememberPagerState()
        val scrollingFollowingPair by remember {
            derivedStateOf {
                if (imagePagerState.isScrollInProgress) {
                    imagePagerState to descriptionPagerState
                } else if (descriptionPagerState.isScrollInProgress) {
                    descriptionPagerState to imagePagerState
                } else {
                    null
                }
            }
        }
        LaunchedEffect(key1 = scrollingFollowingPair, block = {
            val (scrollingState, followingState) = scrollingFollowingPair ?: return@LaunchedEffect
            snapshotFlow { scrollingState.currentPage + scrollingState.currentPageOffset }.collect { pagePart ->
                val divideAndRemainder =
                    BigDecimal.valueOf(pagePart.toDouble()).divideAndRemainder(BigDecimal.ONE)
                followingState.scrollToPage(
                    divideAndRemainder[0].toInt(),
                    divideAndRemainder[1].toFloat()
                )
            }
        })
        //endregion

        val systemUiController = rememberSystemUiController()
        val statusBarColor: Color by animateColorAsState(
            targetValue = getBackgroundColor(imagePagerState.currentPage),
            animationSpec = educationAnimationSpec()
        )
        systemUiController.setStatusBarColor(color = statusBarColor)

        EducationBackground(
            modifier = Modifier.constrainAs(background) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
            currentPageIndex = imagePagerState.currentPage,
        )
        EducationImagePager(modifier = Modifier.constrainAs(imagePager) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            width = Dimension.fillToConstraints
        }, pagerState = imagePagerState)
        EducationPageIndicator(
            modifier = Modifier.constrainAs(pageIndicator) {
                top.linkTo(parent.top, 48.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            totalPageNumber = imagePagerState.pageCount,
            currentPageIndex = imagePagerState.currentPage
        )
        DifficultyProgressBar(modifier = Modifier.constrainAs(difficultyProgressBar) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(imagePager.bottom, 12.dp)
        }, currentPageIndex = imagePagerState.currentPage)
        EducationDescription(modifier = Modifier.constrainAs(description) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(difficultyProgressBar.bottom, 18.dp)
            bottom.linkTo(startButton.top, 22.dp)
            height = Dimension.fillToConstraints
        }, pagerState = descriptionPagerState)
        EducationButton(modifier = Modifier.constrainAs(startButton) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(bottomBar.top, 40.dp)
        }, label = "START")
        EducationBottomBar(modifier = Modifier.constrainAs(bottomBar) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
        })
    }
}

//endregion

//region Components

//region Background

@Composable
private fun EducationBackground(modifier: Modifier = Modifier, currentPageIndex: Int) {
    val color: Color by animateColorAsState(
        targetValue = getBackgroundColor(currentPageIndex),
        animationSpec = educationAnimationSpec()
    )
    Box(modifier = modifier.background(color)) {
        val (enter, exit) = remember {
            arrayListOf(
                fadeIn(educationAnimationSpec()),
                fadeOut(educationAnimationSpec())
            )
        }
        AnimatedVisibility(visible = currentPageIndex == 1, enter = enter as EnterTransition, exit = exit as ExitTransition) {
            Image(
                painter = painterResource(id = R.drawable.education_backgroundimage1),
                contentDescription = null
            )
        }
        AnimatedVisibility(visible = currentPageIndex == 2, enter = enter, exit = exit) {
            Image(
                painter = painterResource(id = R.drawable.education_backgroundimage2),
                contentDescription = null
            )
        }
    }
}

//endregion

//region PageIndicator

@Composable
private fun EducationPageIndicator(
    modifier: Modifier = Modifier,
    totalPageNumber: Int,
    currentPageIndex: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        for (i in 0 until totalPageNumber) {
            EducationPageIndicatorView(
                isSelected = i == currentPageIndex,
                currentPageIndex = currentPageIndex
            )
        }
    }
}

@Composable
private fun EducationPageIndicatorView(isSelected: Boolean, currentPageIndex: Int) {
    val size: Dp by animateDpAsState(
        targetValue = if (isSelected) 12.dp else 8.dp,
        animationSpec = educationAnimationSpec()
    )
    val color: Color by animateColorAsState(
        targetValue = if (currentPageIndex == 2) EducationWhite else EducationBlack,
        animationSpec = educationAnimationSpec()
    )
    Canvas(modifier = Modifier.size(size = size)) {
        drawCircle(
            color = color,
            radius = size.toPx() / 2
        )
    }
}

//endregion

//region ImagePager

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun EducationImagePager(modifier: Modifier = Modifier, pagerState: PagerState) {
    HorizontalPager(
        modifier = modifier
            .padding(top = 48.dp)
            .wrapContentHeight(align = Alignment.Top),
        state = pagerState,
        count = 3
    ) { currentPageIndex ->
        val imageId = when (currentPageIndex) {
            0 -> R.drawable.education_image1
            1 -> R.drawable.education_image2
            else -> R.drawable.education_image3
        }
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier.height(450.dp),
            contentScale = ContentScale.FillHeight
        )
    }
}

//endregion

//region DifficultyProgressBar

@Composable
private fun DifficultyProgressBar(modifier: Modifier = Modifier, currentPageIndex: Int) {
    val color: Color by animateColorAsState(
        targetValue = if (currentPageIndex == 2) EducationWhite else EducationBlack,
        animationSpec = educationAnimationSpec()
    )
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Difficulty",
            color = color,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            fontFamily = EducationMainFontFamily
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box {
            Box(
                modifier = Modifier
                    .width(92.dp)
                    .height(6.dp)
                    .border(width = 1.dp, color = color, shape = RoundedCornerShape(6.dp))
            )
            val width: Dp by animateDpAsState(
                targetValue = when (currentPageIndex) {
                    0 -> 52.dp
                    1 -> 27.dp
                    else -> 71.dp
                },
                animationSpec = educationAnimationSpec()
            )
            Box(
                modifier = Modifier
                    .width(width)
                    .height(6.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color)
            )
        }
    }
}

//endregion

//region DescriptionPager

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun EducationDescription(modifier: Modifier = Modifier, pagerState: PagerState) {
    HorizontalPager(
        modifier = modifier,
        count = 3,
        state = pagerState
    ) { currentPageIndex ->
        val color: Color by animateColorAsState(
            targetValue = if (currentPageIndex == 2) EducationWhite else EducationBlack,
            animationSpec = educationAnimationSpec()
        )
        val text = when (currentPageIndex) {
            0 -> "Explore the exciting natural world around us.\n" +
                    "This is your very own nature scrapbook, packed with fascinating facts and brilliant activities. Doodle, draw and colour and find out how plants grow as well as the different parts of plants, seeds, and flowers."
            1 -> "Diagrams, cross sections, and illustrations get kids up close and personal with glossy red peppers, plump orange pumpkins, little peas, and dozens of other vegetables; Learn about  color, climatic region in which the plants grow, and their uses."
            else -> "Discover the amazing world of outer space as you scratch pictures of planets, comets, and spacecraft to reveal glittery, swirly, and even glow-in-the-dark colors beneath. Ask questions, seek answers and explore the natural world."
        }

        Text(
            modifier = Modifier.padding(horizontal = 45.dp),
            text = text,
            fontFamily = EducationBookFontFamily,
            color = color,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

//endregion

//region Button

@Composable
private fun EducationButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = EducationMainColor),
        contentPadding = PaddingValues(horizontal = 52.dp, vertical = 13.dp),
        onClick = onClick ?: { showNotImplementedToast(context) }) {
        Text(
            text = label,
            color = EducationBlack,
            fontFamily = EducationMainFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
    }
}

//endregion

//region BottomBar

@Composable
private fun EducationBottomBar(modifier: Modifier = Modifier) {
    BottomNavigation(
        modifier = modifier
            .height(90.dp)
            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
        backgroundColor = EducationWhite,
        elevation = 16.dp
    ) {
        EducationBottomBarItem(
            icon = R.drawable.ic_education_mybooks,
            label = "My books",
            isSelected = true
        )
        EducationBottomBarItem(
            icon = R.drawable.ic_education_discover,
            label = "Discover",
            isSelected = false
        )
        EducationBottomBarItem(
            icon = R.drawable.ic_education_profile,
            label = "Profile",
            isSelected = false
        )
    }
}

@Composable
private fun RowScope.EducationBottomBarItem(icon: Int, label: String, isSelected: Boolean) {
    val context = LocalContext.current
    BottomNavigationItem(
        modifier = Modifier.height(75.dp),
        selectedContentColor = Color.Unspecified,
        unselectedContentColor = Color.Unspecified,
        selected = isSelected,
        onClick = { if (isSelected.not()) showNotImplementedToast(context) },
        icon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = label
            )
        },
        label = {
            Text(
                text = label,
                fontFamily = EducationMainFontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = EducationBlack
            )
        })
}

//endregion

//endregion

//region Previews

@Preview
@Composable
private fun DifficultyProgressBarPreview() {
    DifficultyProgressBar(currentPageIndex = 0)
}

@Preview
@Composable
private fun EducationButtonPreview() {
    EducationButton(label = "Button")
}

@Preview
@Composable
private fun EducationBottomBarPreview() {
    EducationBottomBar()
}

@Preview
@Composable
private fun EducationScreenPreview() {
    EducationScreen()
}

//endregion