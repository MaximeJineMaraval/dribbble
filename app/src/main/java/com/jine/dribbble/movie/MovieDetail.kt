package com.jine.dribbble.movie

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast

@Composable
fun MovieDetail(onBackClicked: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MovieBackground)
    ) {
        val (imageHeader, scoreBar, movieContent) = createRefs()

        ImageHeader(
            modifier = Modifier.constrainAs(imageHeader) {
                top.linkTo(parent.top)
            },
            onBackClicked = onBackClicked
        )

        ScoreBar(modifier = Modifier.constrainAs(scoreBar) {
            end.linkTo(parent.end)
            top.linkTo(imageHeader.bottom)
            bottom.linkTo(imageHeader.bottom)
        })

        MovieContent(modifier = Modifier.constrainAs(movieContent) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(scoreBar.bottom, 48.movieDp)
        })
    }
}

@Composable
private fun ImageHeader(modifier: Modifier = Modifier, onBackClicked: () -> Unit) {
    Box(modifier = modifier.fillMaxWidth()) {
        // Image
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(352.movieDp)
                .clip(
                    RoundedCornerShape(
                        topStart = 0.movieDp,
                        topEnd = 0.movieDp,
                        bottomStart = 50.movieDp,
                        bottomEnd = 50.movieDp
                    )
                ),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.movie_poster_detail),
            contentDescription = null
        )
        // Back button
        Column() {
            Spacer(modifier = Modifier.height(20.movieDp))
            Row(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(20.movieDp))
                MovieTopBarIconButton(
                    iconId = R.drawable.ic_movie_back,
                    contentDescription = "back",
                    onClick = onBackClicked
                )
            }
        }
    }
}

@Composable
private fun ScoreBar(modifier: Modifier = Modifier) {
    // todo add shadow
    val context = LocalContext.current
    ConstraintLayout(
        modifier = modifier
            .shadow(50.movieDp)
            .background(
                MovieBackground, shape = RoundedCornerShape(
                    topStart = 50.movieDp,
                    topEnd = 0.movieDp,
                    bottomEnd = 0.movieDp,
                    bottomStart = 50.movieDp
                )
            )
            .padding(
                start = 62.movieDp,
                end = 38.movieDp
            )
    ) {
        val (ratingNoteIcon, ratingNoteValue, ratingNoteCount, rateIconButton, rateLabel, metaScoreIcon, metaScoreLabel, metaScoreCount) = createRefs()

        // Rating Note
        Image(
            modifier = Modifier
                .size(32.movieDp)
                .constrainAs(ratingNoteIcon) {
                    top.linkTo(parent.top, margin = 18.movieDp)
                    start.linkTo(ratingNoteValue.start)
                    end.linkTo(ratingNoteValue.end)
                },
            painter = painterResource(id = R.drawable.ic_movie_star_full),
            contentDescription = null
        )
        MovieText(
            modifier = Modifier.constrainAs(ratingNoteValue) {
                start.linkTo(parent.start)
                top.linkTo(ratingNoteIcon.bottom, margin = 4.movieDp)
            },
            text = "8.2/10",
            fontWeight = FontWeight.Medium,
            fontSize = 16.movieSp,
            color = MovieContentPrimary
        )
        MovieText(
            modifier = Modifier.constrainAs(ratingNoteCount) {
                start.linkTo(ratingNoteValue.start)
                end.linkTo(ratingNoteValue.end)
                top.linkTo(ratingNoteValue.bottom, margin = 2.movieDp)
                bottom.linkTo(parent.bottom, margin = 18.movieDp)
            },
            text = "150,212",
            fontWeight = FontWeight.Normal,
            fontSize = 12.movieSp,
            color = MovieContentTertiary
        )

        // Rate Action
        IconButton(modifier = Modifier
            .size(32.movieDp)
            .constrainAs(rateIconButton) {
                start.linkTo(rateLabel.start)
                end.linkTo(rateLabel.end)
                bottom.linkTo(rateLabel.top, margin = 4.movieDp)
            }, onClick = { showNotImplementedToast(context) }) {
            Icon(
                modifier = Modifier.size(32.movieDp),
                painter = painterResource(id = R.drawable.ic_movie_star_line),
                contentDescription = "Rate"
            )
        }
        MovieText(
            modifier = Modifier.constrainAs(rateLabel) {
                start.linkTo(ratingNoteValue.end, margin = 84.movieDp)
                top.linkTo(ratingNoteValue.top)
                bottom.linkTo(ratingNoteValue.bottom)
            },
            text = "Rate This",
            fontWeight = FontWeight.Medium,
            fontSize = 16.movieSp,
            color = MovieContentPrimary
        )

        // MetaScore
        Box(
            modifier = Modifier
                .width(28.movieDp)
                .height(24.movieDp)
                .background(MovieScoreColor, shape = RoundedCornerShape(2.movieDp))
                .constrainAs(metaScoreIcon) {
                    start.linkTo(metaScoreLabel.start)
                    end.linkTo(metaScoreLabel.end)
                    bottom.linkTo(metaScoreLabel.top, margin = 8.movieDp)
                }
        ) {
            MovieText(
                modifier = Modifier.align(Alignment.Center),
                text = "86",
                fontWeight = FontWeight.Medium,
                fontSize = 14.movieSp,
                color = Color.White
            )
        }
        MovieText(
            modifier = Modifier.constrainAs(metaScoreLabel) {
                top.linkTo(ratingNoteValue.top)
                bottom.linkTo(ratingNoteValue.bottom)
                start.linkTo(rateLabel.end, margin = 69.movieDp)
            },
            text = "Metascore",
            fontWeight = FontWeight.Medium,
            fontSize = 16.movieSp,
            color = MovieContentPrimary
        )
        MovieText(
            modifier = Modifier.constrainAs(metaScoreCount) {
                start.linkTo(metaScoreLabel.start)
                end.linkTo(metaScoreLabel.end)
                top.linkTo(metaScoreLabel.bottom, margin = 2.movieDp)
            },
            text = "62 critic reviews",
            fontWeight = FontWeight.Normal,
            fontSize = 12.movieSp,
            color = MovieContentTertiary
        )
    }
}

@Composable
private fun MovieContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        MovieContentHeader()
        Spacer(modifier = Modifier.height(40.movieDp))
        MovieContentPlotSummary()
        Spacer(modifier = Modifier.height(48.movieDp))
        MovieContentCastAndCrew()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MovieContentHeader() {
    ConstraintLayout(Modifier.fillMaxWidth()) {
        val (title, year, pg, duration, badges, actionButton) = createRefs()

        MovieText(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 32.movieDp)
            },
            text = "Ford v Ferrari",
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.movieSp,
            color = MovieContentPrimary
        )

        MovieText(
            modifier = Modifier.constrainAs(year) {
                top.linkTo(title.bottom, margin = 8.movieDp)
                start.linkTo(title.start)
            },
            text = "2019",
            fontWeight = FontWeight.Normal,
            fontSize = 16.movieSp,
            color = MovieContentTertiary
        )

        MovieText(
            modifier = Modifier.constrainAs(pg) {
                top.linkTo(year.top)
                start.linkTo(year.end, margin = 28.movieDp)
            },
            text = "PG-13",
            fontWeight = FontWeight.Normal,
            fontSize = 16.movieSp,
            color = MovieContentTertiary
        )

        MovieText(
            modifier = Modifier.constrainAs(duration) {
                top.linkTo(pg.top)
                start.linkTo(pg.end, margin = 24.movieDp)
            },
            text = "2h 32min",
            fontWeight = FontWeight.Normal,
            fontSize = 16.movieSp,
            color = MovieContentTertiary
        )

        val context = LocalContext.current
        Button(modifier = Modifier
            .constrainAs(actionButton) {
                top.linkTo(title.top)
                bottom.linkTo(year.bottom)
                end.linkTo(parent.end, margin = 32.movieDp)
            }
            .size(64.movieDp),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.movieDp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MoviePrimaryColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(20.movieDp),
            onClick = { showNotImplementedToast(context) }) {
            Icon(
                modifier = Modifier.size(28.movieDp),
                painter = painterResource(id = R.drawable.ic_movie_plus),
                contentDescription = "Add to favorite"
            )
        }

        LazyRow(modifier = Modifier.constrainAs(badges) {
            start.linkTo(year.start)
            top.linkTo(year.bottom, margin = 16.movieDp)
        }, horizontalArrangement = Arrangement.spacedBy(12.movieDp),
            content = {
                items(listOf("Action", "Biography", "Drama")) {
                    Chip(
                        onClick = { showNotImplementedToast(context) },
                        colors = ChipDefaults.chipColors(backgroundColor = Color.Transparent),
                        border = BorderStroke(
                            1.movieDp, MovieContentPrimary.copy(alpha = 0.15f)
                        ),
                        content = {
                            MovieText(
                                text = it,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.movieSp,
                                color = MovieContentSecondary
                            )
                        })
                }
            })
    }
}

@Composable
private fun MovieContentPlotSummary() {
    Column(Modifier.padding(horizontal = 32.movieDp)) {
        SectionTitle(title = "Plot Summary")
        Spacer(modifier = Modifier.height(16.movieDp))
        MovieText(
            text = "American car designer Carroll Shelby and driver Kn Miles battle corporate interference and the laws of physics to build a revolutionary race car for Ford in order.",
            fontWeight = FontWeight.Normal,
            fontSize = 16.movieSp,
            color = MovieContentTertiary
        )
    }
}

@Composable
private fun MovieContentCastAndCrew() {
    Column(Modifier.padding(horizontal = 32.movieDp)) {
        SectionTitle(title = "Cast & Crew")
        Spacer(modifier = Modifier.height(16.movieDp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(28.movieDp), content = {
            items(
                listOf(
                    Triple(R.drawable.movie_mangold, "James\nMangold", "Director"),
                    Triple(R.drawable.movie_damon, "Matt\nDamon", "Carroll"),
                    Triple(R.drawable.movie_bale, "Christian\nBale", "Ken Miles"),
                    Triple(R.drawable.movie_balfe, "Caitriona\nBalfe", "Molie")
                )
            ) {
                MovieContentCastAndCrewItem(picture = it.first, name = it.second, job = it.third)
            }
        })
    }
}

@Composable
private fun MovieContentCastAndCrewItem(picture: Int, name: String, job: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(80.movieDp),
            painter = painterResource(id = picture),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(12.movieDp))
        MovieText(
            text = name,
            fontWeight = FontWeight.Medium,
            fontSize = 16.movieSp,
            color = MovieContentPrimary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.movieDp))
        MovieText(
            text = job,
            fontWeight = FontWeight.Medium,
            fontSize = 16.movieSp,
            color = MovieContentTertiary
        )
    }
}

@Composable
private fun SectionTitle(title: String) {
    MovieText(
        text = title,
        fontWeight = FontWeight.Medium,
        fontSize = 24.movieSp,
        color = MovieContentPrimary
    )
}

// Previews

@Preview
@Composable
private fun MovieContentCastAndCrewItemPreview() {
    MovieContentCastAndCrewItem(
        picture = R.drawable.movie_bale,
        name = "Christian\nBale",
        job = "Ken Miles"
    )
}

@Preview
@Composable
private fun MovieContentHeaderPreview() {
    MovieContentHeader()
}

@Preview
@Composable
private fun MovieContentPlotSummaryPreview() {
    MovieContentPlotSummary()
}

@Preview
@Composable
private fun MovieContentCastAndCrewPreview() {
    MovieContentCastAndCrew()
}

@Preview
@Composable
private fun MovieContentPreview() {
    MovieContent()
}

@Preview
@Composable
private fun ScoreBarPreview() {
    ScoreBar()
}

@Preview
@Composable
private fun MovieDetailPreview() {
    MovieDetail {}
}

@Preview
@Composable
private fun ImageHeaderPreview() {
    ImageHeader {}
}
