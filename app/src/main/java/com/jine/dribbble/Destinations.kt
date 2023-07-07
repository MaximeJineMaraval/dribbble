package com.jine.dribbble

import androidx.compose.ui.graphics.Brush
import com.jine.dribbble.education.EducationGradient
import com.jine.dribbble.egarden.EGardenGradient
import com.jine.dribbble.eyecream.EyeCreamGradiant
import com.jine.dribbble.financeconcept.FinanceConceptGradiant
import com.jine.dribbble.foodee.FoodeeGradient
import com.jine.dribbble.halloweenevents.HalloweenEventsGradiant
import com.jine.dribbble.meditation.MeditationGradient
import com.jine.dribbble.movie.MovieGradient
import com.jine.dribbble.paypal.PaypalGradient
import com.jine.dribbble.skeuomorphism.SkeuomorphismGradient
import com.jine.dribbble.snowboard.SnowboardGradient
import com.jine.dribbble.tesla.TeslaGradient

sealed class Destinations(val title: String, val background: Brush?) {
    object Menu : Destinations(
        title = "Menu",
        background = null
    )

    object FinanceConcept : Destinations(
        title = "Finance Concept",
        background = FinanceConceptGradiant
    )

    object EyeCream : Destinations(
        title = "Eye Cream",
        background = EyeCreamGradiant
    )

    object HalloweenEvents : Destinations(
        title = "Halloween Events",
        background = HalloweenEventsGradiant
    )

    object EGarden : Destinations(
        title = "E-Garden",
        background = EGardenGradient
    )

    object Meditation : Destinations(
        title = "Meditation",
        background = MeditationGradient
    )

    object Tesla : Destinations(
        title = "Tesla",
        background = TeslaGradient
    )

    object Movie : Destinations(
        title = "Movie",
        background = MovieGradient
    )

    object Snowboard : Destinations(
        title = "Snowboard",
        background = SnowboardGradient
    )

    object Education : Destinations(
        title = "Education",
        background = EducationGradient
    )

    object Skeuomorphism : Destinations(
        title = "Skeuomorphism",
        background = SkeuomorphismGradient
    )
    object Foodee : Destinations(
        title = "Foodee",
        background = FoodeeGradient
    )

    object Paypal : Destinations(
        title = "Paypal",
        background = PaypalGradient
    )
}