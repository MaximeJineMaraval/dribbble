package com.jine.dribbble

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jine.dribbble.egarden.EGardenScreen
import com.jine.dribbble.eyecream.EyeCreamScreen
import com.jine.dribbble.financeconcept.FinanceConceptScreen
import com.jine.dribbble.halloweenevents.HalloweenEventsScreen
import com.jine.dribbble.meditation.MeditationScreen
import com.jine.dribbble.menu.MenuScreen
import com.jine.dribbble.ui.theme.DribbbleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DribbbleTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        navController = navController,
        startDestination = Destinations.Menu.title
    ) {
        composable(Destinations.Menu.title) { MenuScreen(onClick = { navController.navigate(it) }) }
        composable(Destinations.FinanceConcept.title) { FinanceConceptScreen() }
        composable(Destinations.EyeCream.title) { EyeCreamScreen() }
        composable(Destinations.HalloweenEvents.title) { HalloweenEventsScreen() }
        composable(Destinations.EGarden.title) { EGardenScreen() }
        composable(Destinations.Meditation.title) { MeditationScreen() }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DribbbleTheme {
        Greeting("Android")
    }
}


fun showNotImplementedToast(context: Context) {
    Toast.makeText(context, "Not implemented", Toast.LENGTH_SHORT).show()
}