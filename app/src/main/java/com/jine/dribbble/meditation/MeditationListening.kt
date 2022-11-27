package com.jine.dribbble.meditation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.R
import com.jine.dribbble.showNotImplementedToast
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Float.max
import java.lang.Float.min

@Composable
fun MeditationListening(onReturnClicked: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = MeditationBackground)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MeditationBackground)
    ) {

        // TopAppBar
        ListeningTopAppBar(onReturnClicked = onReturnClicked)
        // Main Image
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.meditation_listeningimage),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
        // Labels
        Spacer(modifier = Modifier.height(20.dp))
        Labels()
        // Player section
        Spacer(modifier = Modifier.height(36.dp))
        val waves = mutableListOf<Dp>()
        repeat(44) {
            waves.add(arrayListOf(34, 30, 22, 18, 13, 9, 8).random().dp)
        }
        PlayerSection(waves)
        // BottomAppBar
        Spacer(modifier = Modifier.height(76.dp))
        ListeningBottomAppBar()
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun ListeningTopAppBar(onReturnClicked: () -> Unit) {
    val context = LocalContext.current
    val onDefaultClick = { showNotImplementedToast(context) }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        // Back
        IconButton(onClick = onReturnClicked) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_meditation_back),
                contentDescription = "Close the screen",
                tint = MeditationNeutral1
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        // Like
        IconButton(onClick = onDefaultClick) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_meditation_like),
                contentDescription = "Like this meditation",
                tint = MeditationNeutral1
            )
        }
        Spacer(modifier = Modifier.width(2.dp))
        // Share
        IconButton(onClick = onDefaultClick) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_meditation_share),
                contentDescription = "Share this meditation",
                tint = MeditationNeutral1
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
    }
}

@Composable
private fun Labels() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        MeditationText(
            text = "Finding Your Inner Power",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = MeditationNeutral1
        )
        Spacer(modifier = Modifier.height(8.dp))
        MeditationText(
            text = "by Alisa Byers",
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = MeditationNeutral2
        )
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
private fun PlayerSection(waves: List<Dp>) {
    val musicLength = 390f
    var isPlaying by remember { mutableStateOf(false) }
    val animatedTimeSpent = remember { Animatable(0f) }
    var changeIndex by remember { mutableStateOf(0) } // Just a hack to trigger the launchedEffect
    LaunchedEffect(isPlaying, changeIndex) {
        if (isPlaying) {
            animatedTimeSpent.animateTo(
                targetValue = musicLength,
                animationSpec = TweenSpec(
                    durationMillis = (musicLength.toInt() * 1000) - (animatedTimeSpent.value.toInt() * 1000),
                    easing = LinearEasing
                )
            )
        } else {
            animatedTimeSpent.stop()
        }
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val minutes = animatedTimeSpent.value.toInt() / 60
            val seconds = animatedTimeSpent.value.toInt() % 60
            MeditationText(
                text = "$minutes:${if (seconds < 10) "0" else ""}$seconds",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MeditationPrimary1
            )
            Spacer(modifier = Modifier.width(15.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val step = waves.size / musicLength
                waves.forEachIndexed { index, height ->
                    val color = if (animatedTimeSpent.value * step >= index + 1) {
                        MeditationPrimary1
                    } else {
                        MeditationNeutral3
                    }
                    Box(
                        modifier = Modifier
                            .height(height)
                            .width(3.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(color)
                    )
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
            MeditationText(
                text = "6:30",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MeditationNeutral2
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {
                GlobalScope.launch {
                    animatedTimeSpent.snapTo(max(animatedTimeSpent.value - 10, 0f))
                    changeIndex += 1
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_meditation_minus10),
                    contentDescription = "Go 10 seconds back",
                    modifier = Modifier.size(24.dp),
                    tint = MeditationPrimary1
                )
            }
            Spacer(modifier = Modifier.width(36.dp))
            FloatingActionButton(
                modifier = Modifier.size(60.dp),
                backgroundColor = MeditationPrimary1,
                onClick = {
                    if (animatedTimeSpent.value == musicLength) {
                        GlobalScope.launch {
                            animatedTimeSpent.snapTo(0f)
                            changeIndex += 1
                            isPlaying = true
                        }
                    }
                    isPlaying = isPlaying.not()
                }) {
                if (isPlaying && animatedTimeSpent.value < musicLength) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_meditation_pause),
                        contentDescription = "Pause",
                        tint = MeditationNeutral4,
                        modifier = Modifier.size(40.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_meditation_play),
                        contentDescription = "Play",
                        tint = MeditationNeutral4,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(start = 6.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(36.dp))
            IconButton(onClick = {
                GlobalScope.launch {
                    animatedTimeSpent.snapTo(min(animatedTimeSpent.value + 10, musicLength))
                    changeIndex += 1
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_meditation_plus10),
                    contentDescription = "Go 10 seconds forward",
                    modifier = Modifier.size(24.dp),
                    tint = MeditationPrimary1
                )
            }
        }
    }
}

@Composable
private fun ListeningBottomAppBar() {
    val context = LocalContext.current
    val onClick = { showNotImplementedToast(context) }
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(4.dp))
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_meditation_sound),
                contentDescription = "Sound",
                modifier = Modifier.size(24.dp),
                tint = MeditationNeutral2
            )
        }
        MeditationText(
            modifier = Modifier.weight(1f),
            text = "Daily Listening",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = MeditationPrimary1,
            textAlign = TextAlign.Center
        )
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_meditation_clock),
                contentDescription = "Clock",
                modifier = Modifier.size(24.dp),
                tint = MeditationNeutral2
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
    }
}

// Previews

@Preview(showBackground = true)
@Composable
private fun BottomAppBarPreview() {
    ListeningBottomAppBar()
}

@Preview(showBackground = true)
@Composable
private fun PlayerSectionPreview() {
    val waves = mutableListOf<Dp>()
    repeat(44) {
        waves.add(arrayListOf(34, 30, 22, 18, 13, 9, 8).random().dp)
    }
    PlayerSection(waves)
}

@Preview(showBackground = true)
@Composable
private fun LabelsPreview() {
    Labels()
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarPreview() {
    ListeningTopAppBar {}
}

@Preview
@Composable
private fun MeditationListeningPreview() {
    MeditationListening {}
}