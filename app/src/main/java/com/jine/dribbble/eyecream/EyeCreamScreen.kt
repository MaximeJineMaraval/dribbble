package com.jine.dribbble.eyecream

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jine.dribbble.R
import com.jine.dribbble.financeconcept.FinanceConceptWhite

val EyeCreamWhite = Color(0xFFFFFFFF)
val EyeCreamBlack = Color(0xFF0E0E0E)
val EyeCreamBlackDescription = Color(0xFF494545)
val EyeCreamBlackButton = Color(0xFF282828)
val EyeCreamGrey = Color(0xFFBBBBBB)
val EyeCreamPurpleStrong = Color(0xFF706EB7)
val EyeCreamPurpleLight = Color(0xFFDFE6FF)
val EyeCreamPurpleSecond = Color(0xFFC7C1ED)
val EyeCreamGradiant =
    Brush.linearGradient(colors = listOf(EyeCreamPurpleLight, EyeCreamPurpleStrong))
val EyeCreamFontFamily = FontFamily(
    Font(R.font.generalsans_bold, weight = FontWeight.Bold),
    Font(R.font.generalsans_bolditalic, weight = FontWeight.Bold, FontStyle.Italic),
    Font(R.font.generalsans_extralight, weight = FontWeight.ExtraLight),
    Font(R.font.generalsans_extralightitalic, weight = FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.generalsans_italic, weight = FontWeight.Normal, FontStyle.Italic),
    Font(R.font.generalsans_light, weight = FontWeight.Light),
    Font(R.font.generalsans_lightitalic, weight = FontWeight.Light, FontStyle.Italic),
    Font(R.font.generalsans_medium, weight = FontWeight.Medium),
    Font(R.font.generalsans_mediumitalic, weight = FontWeight.Medium, FontStyle.Italic),
    Font(R.font.generalsans_regular, weight = FontWeight.Normal),
    Font(R.font.generalsans_semibold, weight = FontWeight.SemiBold),
    Font(R.font.generalsans_semibolditalic, weight = FontWeight.SemiBold, FontStyle.Italic)
)

@Composable
fun EyeCreamScreen() {
    var showDetail by remember { mutableStateOf(false) }
    if (showDetail) {
        EyeCreamDetail {
            showDetail = false
        }
    } else {
        EyeCreamHome {
            showDetail = true
        }
    }
}

@Composable
fun EyeCreamButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues(horizontal = 40.dp, vertical = 10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = EyeCreamBlackButton),
        onClick = onClick
    ) {
        Text(
            text = text,
            color = FinanceConceptWhite,
            fontFamily = EyeCreamFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}