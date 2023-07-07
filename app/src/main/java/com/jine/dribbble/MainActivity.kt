package com.jine.dribbble

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jine.dribbble.education.EducationScreen
import com.jine.dribbble.egarden.EGardenScreen
import com.jine.dribbble.eyecream.EyeCreamScreen
import com.jine.dribbble.financeconcept.FinanceConceptScreen
import com.jine.dribbble.foodee.FoodeeScreen
import com.jine.dribbble.halloweenevents.HalloweenEventsScreen
import com.jine.dribbble.meditation.MeditationScreen
import com.jine.dribbble.menu.MenuScreen
import com.jine.dribbble.movie.MovieScreen
import com.jine.dribbble.paypal.PaypalApp
import com.jine.dribbble.skeuomorphism.SkeuomorphismScreen
import com.jine.dribbble.snowboard.SnowboardScreen
import com.jine.dribbble.tesla.TeslaScreen
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
        composable(Destinations.Tesla.title) { TeslaScreen() }
        composable(Destinations.Movie.title) { MovieScreen() }
        composable(Destinations.Snowboard.title) { SnowboardScreen() }
        composable(Destinations.Education.title) { EducationScreen() }
        composable(Destinations.Skeuomorphism.title) { SkeuomorphismScreen() }
        composable(Destinations.Foodee.title) { FoodeeScreen() }
        composable(Destinations.Paypal.title) { PaypalApp() }
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

fun Modifier.customClick(
    onClick: () -> Unit,
    onPress: () -> Unit,
    onRelease: () -> Unit,
    onCancel: () -> Unit = onRelease
) = this.then(
    this.pointerInput(Unit) {
        detectTapGestures(onPress = {
            var isCorrectlyReleased = false
            try {
                onPress()
                isCorrectlyReleased = tryAwaitRelease()
            } finally {
                if (isCorrectlyReleased) {
                    onClick()
                    onRelease()
                } else {
                    onCancel()
                }
            }
        })
    }
)