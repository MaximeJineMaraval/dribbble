package com.jine.dribbble.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
    val destinations = listOf(Destinations.FinanceConcept)
    LazyVerticalGrid(
        modifier = Modifier.background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Fixed(2),
        content = {
            items(destinations) { destination ->
                MenuCell(
                    destination = destination.title,
                    background = destination.background!!,
                    onClick = { onClick(destination.title) })
            }
        })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MenuCell(destination: String, background: Brush, onClick: () -> Unit) {
    Card(modifier = Modifier.size(120.dp), elevation = 2.dp, onClick = onClick) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background),
            contentAlignment = Alignment.Center
        ) {
            Text(text = destination, fontWeight = FontWeight.Bold)
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
