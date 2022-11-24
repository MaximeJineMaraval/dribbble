package com.jine.dribbble.halloweenevents

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.jine.dribbble.R


val HalloweenEventsBrownStrong = Color(0xFF301509)
val HalloweenEventsBrownLight = Color(0xFF593217)
val HalloweenEventsBlack = Color(0xFF000000)
val HalloweenEventsWhiteStrong = Color(0xFFFFFFFF)
val HalloweenEventsWhiteLight = Color(0xFFFFFFFF).copy(alpha = 0.9f)
val HalloweenEventsGradiant =
    Brush.linearGradient(colors = listOf(HalloweenEventsBrownLight, HalloweenEventsBrownStrong))
val HalloweenEventsFontFamily = FontFamily(
    Font(R.font.clashdisplay_bold, weight = FontWeight.Bold),
    Font(R.font.clashdisplay_extralight, weight = FontWeight.ExtraLight),
    Font(R.font.clashdisplay_light, weight = FontWeight.Light),
    Font(R.font.clashdisplay_medium, weight = FontWeight.Medium),
    Font(R.font.clashdisplay_regular, weight = FontWeight.Normal),
    Font(R.font.clashdisplay_semibold, weight = FontWeight.SemiBold)
)

@Composable
fun HalloweenEventsScreen() {
    var showTickets by remember { mutableStateOf(false) }
    if (showTickets) {
        HalloweenEventsTickets {
            showTickets = false
        }
    } else {
        HalloweenEventsHome {
            showTickets = true
        }
    }
}