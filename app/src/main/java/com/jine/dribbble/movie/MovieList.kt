package com.jine.dribbble.movie

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast
import kotlin.math.absoluteValue

@Composable
fun MovieList(showDetail: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MovieBackground)
    ) {
        Spacer(modifier = Modifier.height(20.movieDp))
        Header()
        Spacer(modifier = Modifier.height(32.movieDp))
        Tabs()
        Spacer(modifier = Modifier.height(48.movieDp))
        Chips()
        Spacer(modifier = Modifier.height(72.movieDp))
        MoviePager(showDetail)
    }
}

@Composable
private fun Header() {
    Row(Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(20.movieDp))
        MovieTopBarIconButton(iconId = R.drawable.ic_movie_menu, contentDescription = "menu")
        Spacer(modifier = Modifier.weight(1f))
        MovieTopBarIconButton(iconId = R.drawable.ic_movie_search, contentDescription = "search")
        Spacer(modifier = Modifier.width(20.movieDp))
    }
}

@Composable
private fun Tabs() {
    val context = LocalContext.current
    var selectedTabIndex by remember { mutableStateOf(0) }
    ScrollableTabRow(
        modifier = Modifier.fillMaxWidth(),
        edgePadding = 32.movieDp,
        selectedTabIndex = selectedTabIndex,
        backgroundColor = MovieBackground,
        indicator = {
            val currentTabPosition = it[selectedTabIndex]
            TabRowDefaults.Indicator(color = Color.Unspecified, modifier =
            Modifier.composed(
                inspectorInfo = debugInspectorInfo {
                    name = "tabIndicatorOffset"
                    value = currentTabPosition
                }
            ) {
                val indicatorOffset by animateDpAsState(
                    targetValue = currentTabPosition.left,
                    animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
                )
                fillMaxWidth()
                    .wrapContentSize(Alignment.BottomStart)
                    .offset(x = indicatorOffset)
                    .width(40.movieDp)
                    .height(6.movieDp)
                    .background(MoviePrimaryColor, shape = RoundedCornerShape(6.movieDp))
            }
            )
        },
        divider = {}
    ) {
        MovieTab(isSelected = selectedTabIndex == 0, label = "In Theater") { selectedTabIndex = 0 }
        MovieTab(isSelected = selectedTabIndex == 1, label = "Box Office") {
            selectedTabIndex = 1
            showNotImplementedToast(context)
        }
        MovieTab(isSelected = selectedTabIndex == 2, label = "Coming Soon") {
            selectedTabIndex = 2
            showNotImplementedToast(context)
        }
    }
}

@Composable
private fun MovieTab(isSelected: Boolean, label: String, onClick: () -> Unit) {
    Tab(
        selected = isSelected,
        onClick = onClick,
        selectedContentColor = MovieContentPrimary,
        unselectedContentColor = MovieContentPrimary.copy(alpha = 0.3f)
    ) {
        MovieText(
            modifier = Modifier.padding(end = 40.movieDp),
            text = label,
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.movieSp,
            color = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(16.movieDp))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Chips() {
    val context = LocalContext.current
    LazyRow(
        contentPadding = PaddingValues(horizontal = 32.movieDp),
        horizontalArrangement = Arrangement.spacedBy(20.movieDp)
    ) {
        val items = listOf("Action", "Crime", "Comedy", "Drama")
        items(items = items) {
            Chip(
                colors = ChipDefaults.chipColors(backgroundColor = Color.Transparent),
                border = BorderStroke(2.movieDp, color = MovieContentPrimary.copy(alpha = 0.15f)),
                onClick = { showNotImplementedToast(context) }) {
                MovieText(
                    modifier = Modifier.padding(vertical = 8.movieDp, horizontal = 12.movieDp),
                    text = it,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.movieSp,
                    color = MovieContentSecondary
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MoviePager(onMovieClick: () -> Unit) {
    val items = listOf(
        Triple(R.drawable.movie_poster_1, "Downhill", 7.9),
        Triple(R.drawable.movie_poster_2, "Ford v Ferrari", 8.2),
        Triple(R.drawable.movie_poster_3, "The Call Of The Wild", 8.6)
    )
    HorizontalPager(
        modifier = Modifier.fillMaxWidth(),
        count = items.size,
        contentPadding = PaddingValues(horizontal = 65.movieDp),
    ) { page ->
        val item = items[page]
        MovieItem(
            modifier = Modifier.graphicsLayer {
                // Calculate the absolute offset for the current page from the
                // scroll position. We use the absolute value which allows us to mirror
                // any effects for both directions
                val absolutePageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                val pageOffset = calculateCurrentOffsetForPage(page)//.absoluteValue

                // We animate the opacity
                lerp(
                    start = 0.4f,
                    stop = 1f,
                    fraction = 1f - absolutePageOffset.coerceIn(0f, 1f)
                ).also { opacity ->
                    alpha = opacity
                }

                // We animate the angle
                lerp(
                    start = -7f,
                    stop = 7f,
                    fraction = 0.5f - pageOffset.coerceIn(-1f, 1f)
                ).also { rotation ->
                    rotationZ = rotation
                }

                // We animate the scale
                lerp(
                    start = 0.9f,
                    stop = 1f,
                    fraction = 1f - absolutePageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }

            },
            posterId = item.first,
            movieName = item.second,
            movieRating = item.third,
            onClick = onMovieClick
        )
    }
}

@Composable
private fun MovieItem(
    modifier: Modifier = Modifier,
    posterId: Int,
    movieName: String,
    movieRating: Double,
    onClick: () -> Unit
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center) {
            Column() {
                Spacer(modifier = Modifier.height(54.movieDp))
                Image(
                    modifier = Modifier
                        .width(244.movieDp)
                        .height(410.movieDp)
                        .blur(radius = 30.movieDp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                        .alpha(0.54f),
                    painter = painterResource(id = posterId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Image(
                modifier = Modifier
                    .width(310.movieDp)
                    .height(460.movieDp)
                    .clip(RoundedCornerShape(50.movieDp))
                    .clickable(onClick = onClick),
                painter = painterResource(id = posterId),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(40.movieDp))
        MovieText(
            text = movieName,
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.movieSp,
            color = MovieContentPrimary
        )
        Spacer(modifier = Modifier.height(8.movieDp))
        Row {
            Icon(
                modifier = Modifier.size(20.movieDp),
                painter = painterResource(id = R.drawable.ic_movie_star_full),
                contentDescription = "Rating",
                tint = MovieRateColor
            )
            Spacer(modifier = Modifier.width(8.movieDp))
            MovieText(
                text = movieRating.toString(),
                fontWeight = FontWeight.Medium,
                fontSize = 18.movieSp,
                color = MovieContentSecondary
            )
        }
    }
}

// Previews

@Preview(showBackground = true)
@Composable
private fun MovieItemPreview() {
    MovieItem(
        posterId = R.drawable.movie_poster_2,
        movieName = "Ford v Ferrari",
        movieRating = 8.2
    ) {}
}

@Preview(showBackground = true)
@Composable
private fun MoviePagerPreview() {
    MoviePager {}
}

@Preview(showBackground = true)
@Composable
private fun ChipsPreview() {
    Chips()
}

@Preview
@Composable
private fun TabsPreview() {
    Tabs()
}

@Preview
@Composable
private fun HeaderPreview() {
    Header()
}

@Preview
@Composable
private fun MovieListPreview() {
    MovieList {}
}
