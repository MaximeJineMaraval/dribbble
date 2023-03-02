package com.jine.dribbble.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jine.dribbble.Destinations
import com.jine.dribbble.ui.theme.DribbbleTheme

@Composable
fun MenuScreen(onClick: (destination: String) -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = MaterialTheme.colors.background)
    val destinations = listOf(
        Destinations.FinanceConcept,
        Destinations.EyeCream,
        Destinations.HalloweenEvents,
        Destinations.EGarden,
        Destinations.Meditation,
        Destinations.Tesla,
        Destinations.Movie,
        Destinations.Snowboard,
        Destinations.Education,
        Destinations.Skeuomorphism
    )
    LazyVerticalGrid(
        modifier = Modifier.background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(destinations) { destination ->
                MenuCell(
                    destination = destination.title,
                    background = destination.background!!,
                    whiteText = destination is Destinations.HalloweenEvents || destination is Destinations.Tesla,
                    onClick = { onClick(destination.title) })
            }
        })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MenuCell(
    destination: String,
    background: Brush,
    whiteText: Boolean = false,
    onClick: () -> Unit
) {
    Card(modifier = Modifier.size(120.dp), elevation = 2.dp, onClick = onClick) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = destination,
                fontWeight = FontWeight.Bold,
                color = if (whiteText) Color.White else Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuCellPreview() {
    DribbbleTheme {
        MenuCell(Destinations.FinanceConcept.title, Destinations.FinanceConcept.background!!) {}
    }
}
