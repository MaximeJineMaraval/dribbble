package com.jine.dribbble

import android.os.Bundle
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
import com.jine.dribbble.eyecream.EyeCreamScreen
import com.jine.dribbble.financeconcept.FinanceConceptScreen
import com.jine.dribbble.halloweenevents.HalloweenEventsScreen
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